import 'package:flutter_todo_app/data/constants.dart';
import 'package:flutter_todo_app/model/task.dart';

class DataSource {
  static List<Task> _allTaskList = [];

  static List<Task> getListByType(String type) {
    switch (type) {
      case TYPE_TODAY:
        return getTodayTasks();
      case TYPE_TOMORROW:
        return getTomorrowTasks();
      case TYPE_AFTER_WEEK:
        return getAfterWeekTasks();
      case TYPE_THIS_MONTH:
        return getThisMonthTasks();
      case TYPE_ALL:
        return getAllTasks();
    }
  }

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

  static List<Task> getTomorrowTasks() {
    List<Task> result = [];
    DateTime dateTime = new DateTime(DateTime.now().year);
    dateTime.add(Duration(days: 1));
    for (Task task in _allTaskList) {
      if (task.createDate.day == dateTime.day) {
        result.add(task);
      }
    }
    return result;
  }

  static List<Task> getAfterWeekTasks() {
    List<Task> result = [];
//    DateTime dateTime = new DateTime(DateTime.now().year);
//    dateTime.add(Duration(days: 7));

//    int today = DateTime.now().day;
//    for (Task task in _allTaskList) {
//      if (task.createDate.day == today) {
//        result.add(task);
//      }
//    }
    return result;
  }

  static List<Task> getThisMonthTasks() {
    List<Task> result = [];
    DateTime dateTime = new DateTime(DateTime.now().year);
    for (Task task in _allTaskList) {
      if (task.createDate.day == dateTime.month) {
        result.add(task);
      }
    }
    return result;
  }

  static List<Task> getAllTasks() {
    return _allTaskList;
  }

  static void addTask(Task task) {
    _allTaskList.add(task);
  }
}
