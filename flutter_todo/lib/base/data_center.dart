import 'package:dio/dio.dart';
import 'package:flutter_todo/base/list_model.dart';
import 'package:flutter_todo/base/utils.dart';

const LIST_DATA_URL =
    "https://api.github.com/repos/TotoroXkf/FlutterTodoData/contents/ListData.json?ref=master";

class DataCenter {
  bool _isLoading = false;
  Dio _dio = Dio();
  ListModel _listModel;

  DataCenter._internal();

  static DataCenter _instance = new DataCenter._internal();

  factory DataCenter() => _instance;

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

  ListModel getListModel() {
    return _listModel;
  }

  Future<void> deleteTask(Task task) async {
    _listModel.lists[0].tasks.remove(task);
  }
}

DataCenter dataCenter = DataCenter();
