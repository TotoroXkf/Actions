import 'package:flutter_todo_app/model/task.dart';

class DataSource {
  static List<Task> _allTaskList = [];

  static List<Task> getTodayTasks() {
    List<Task> result = [];
    int today = DateTime.now().day;
    for (Task task in _allTaskList) {
      if (task.createDate.day == today) {
        result.add(task);
      }
    }
    return result;
  }

  static void addTask(Task task) {
    _allTaskList.add(task);
  }

  static List<Task> getAllTasks() {
    return _allTaskList;
  }
}
