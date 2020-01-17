import 'dart:convert';

import 'package:dio/dio.dart';
import 'package:flutter_todo/base/Events.dart';
import 'package:flutter_todo/base/list_model.dart';

const LIST_DATA_URL =
    "https://api.github.com/repos/TotoroXkf/FlutterTodoData/contents/ListData.json";

class DataCenter {
  bool _isLoading = false;

  Dio _dio = Dio();

  ListModel _listModel;
  String listDataSha = "";

  DataCenter._internal();

  static DataCenter _instance = new DataCenter._internal();

  factory DataCenter() => _instance;

  bool isLoading() {
    return _isLoading;
  }

  /// 加载远端的数据到本地内存
  Future loadRemoteList() async {
    _isLoading = true;
    try {
      Response response = await _dio.get(LIST_DATA_URL);
      String encode = response.data['content'];
      listDataSha = response.data['sha'];
      Map<String, dynamic> data = base64ToObject(encode);
      _listModel = ListModel.fromJson(data);
    } catch (e) {}
    _isLoading = false;
  }

  /// 把内存中的数据push到远端
  Future putRemoteList() async {
    Map<String, dynamic> data = _listModel.toJson();
    String base64String = objectToBase64(data);
    try {
      await _dio.put(
        LIST_DATA_URL,
        options: Options(
          headers: {
            "Authorization": "Basic MTEzMTIyNTEzM0BxcS5jb206OTUwNjI4eGtmKg==",
          },
        ),
        data: {
          "message": "Coding API",
          "content": base64String,
          "sha": listDataSha,
        },
      );
    } catch (e) {}
  }

  ListModel getListModel() {
    return _listModel;
  }

  /// 添加新的Task
  void addTask(Task task) {
    for (int i = 0; i < _listModel.getListNum(); i++) {
      if (_listModel.lists[i].name == task.getBelongListName()) {
        _listModel.lists[i].tasks.add(task);
      }
    }
    eventBus.fire(UpdateListUIEvent());
    putRemoteList();
  }

  /// 删除任务
  void deleteTask(Task task) async {
    for (int i = 0; i < _listModel.getListNum(); i++) {
      if (_listModel.lists[i].name == task.getBelongListName()) {
        _listModel.lists[i].tasks.remove(task);
      }
    }
    eventBus.fire(UpdateListUIEvent());
    putRemoteList();
  }

  /// 把base64转换为Object
  Map<String, dynamic> base64ToObject(String base64String) {
    base64String = base64String.replaceAll("\n", "");
    String decode = utf8.decode(base64.decode(base64String));
    Map<String, dynamic> data = json.decode(decode);
    return data;
  }

  /// 把object转换为base64
  String objectToBase64(Map<String, dynamic> data) {
    List<int> bytes = utf8.encode(json.encode(data));
    String base64String = base64.encode(bytes);
    return base64String;
  }
}

DataCenter dataCenter = DataCenter();
