import 'package:dio/dio.dart';
import 'package:flutter_todo/base/list_model.dart';
import 'package:flutter_todo/base/utils.dart';

const LIST_DATA_URL =
    "https://api.github.com/repos/TotoroXkf/FlutterTodoData/contents/ListData.json?ref=master";

class DataCenter {
  bool _isLoading = false;
  Dio _dio = Dio();
  ListModel _listModel;

  static DataCenter _instance;

  static DataCenter getInstance() {
    if (_instance == null) {
      _instance = DataCenter();
    }
    return _instance;
  }

  bool isLoading() {
    return _isLoading;
  }

  Future loadDetailedList() async {
    _isLoading = true;
    try {
      Response response = await _dio.get(LIST_DATA_URL);
      String encode = response.data['content'];
      Map<String, dynamic> data = base64ToObject(encode);
      _listModel = ListModel.fromJson(data);
    } catch (e) {}
    _isLoading = false;
  }

  int getListNum() {
    return _listModel.listNum;
  }

  List<String> getListName() {
    List<String> result = [];
    for (int i = 0; i < _listModel.lists.length; i++) {
      result.add(_listModel.lists[i].name);
    }
    return result;
  }
}