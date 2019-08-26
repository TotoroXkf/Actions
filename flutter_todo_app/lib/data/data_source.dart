import 'dart:math';

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
    return [];
  }

  static List<Task> getTodayTasks() {
    List<Task> result = [];
    int today = DateTime.now().day;
    for (Task task in _allTaskList) {
      if (task.createDate.day == today) {
        result.add(task);
      }
    }
    _sortByDate(result);
    return result;
  }

  static List<Task> getTomorrowTasks() {
    List<Task> result = [];
    DateTime dateTime = DateTime.now();
    dateTime = dateTime.add(Duration(days: 1));
    for (Task task in _allTaskList) {
      if (task.createDate.day == dateTime.day) {
        result.add(task);
      }
    }
    _sortByDate(result);
    return result;
  }

  static List<Task> getAfterWeekTasks() {
    List<Task> result = [];

    DateTime today = DateTime.now();
    for (Task task in _allTaskList) {
      DateTime taskDate = task.createDate;
      //从今天向后的7天，并且当天也要算在内
      if ((taskDate.difference(today).inDays <= 7) &&
          (taskDate.isAfter(today) || taskDate.day == today.day)) {
        result.add(task);
      }
    }
    _sortByDate(result);
    return result;
  }

  static List<Task> getThisMonthTasks() {
    List<Task> result = [];
    DateTime dateTime = DateTime.now();
    for (Task task in _allTaskList) {
      if (task.createDate.month == dateTime.month) {
        result.add(task);
      }
    }
    _sortByDate(result);
    return result;
  }

  static void _sortByDate(List<Task> list) {
    list.sort((Task t1, Task t2) {
      DateTime date1 = t1.createDate;
      DateTime date2 = t2.createDate;
      if (date1.isAfter(date2)) {
        return 1;
      } else if (date1.isAtSameMomentAs(date2)) {
        return 0;
      }
      return -1;
    });
  }

  static List<Task> getAllTasks() {
    _sortByDate(_allTaskList);
    return _allTaskList;
  }

  static void addTask(Task task) {
    _allTaskList.add(task);
  }

  static List<Task> mockDataByType(String type) {
    _allTaskList.clear();
    switch (type) {
      case TYPE_TODAY:
        return mockTodayList();
      case TYPE_TOMORROW:
        return mockTomorrowList();
      case TYPE_AFTER_WEEK:
        return mockWeekList();
      case TYPE_THIS_MONTH:
        return mockMonthList();
      case TYPE_ALL:
        return mockAllTaskList();
    }
    return [];
  }

  static List<Task> mockTodayList() {
    for (int i = 0; i < 20; i++) {
      Task task = new Task(
        name: "第 $i 个任务",
        checked: false,
      );
      addTask(task);
    }
    return getTodayTasks();
  }

  static List<Task> mockTomorrowList() {
    DateTime now = DateTime.now();
    for (int i = 0; i < 20; i++) {
      DateTime date = now.add(Duration(days: 1));
      Task task = new Task(
        name: "第 $i 个任务",
        checked: false,
        createDate: date,
      );
      addTask(task);
    }
    return getTomorrowTasks();
  }

  static List<Task> mockWeekList() {
    DateTime now = DateTime.now();
    for (int i = 0; i < 40; i++) {
      int append = Random().nextInt(8);
      DateTime date = now.add(Duration(days: append));
      Task task = new Task(
        name: "第 $i 个任务",
        checked: false,
        createDate: date,
      );
      addTask(task);
    }
    return getAfterWeekTasks();
  }

  static List<Task> mockMonthList() {
    DateTime now = DateTime.now();
    for (int i = 0; i < 40; i++) {
      int append = Random().nextInt(32);
      DateTime date = now.add(Duration(days: append));
      Task task = new Task(
        name: "第 $i 个任务",
        checked: false,
        createDate: date,
      );
      addTask(task);
    }
    return getThisMonthTasks();
  }

  static List<Task> mockAllTaskList() {
    mockTodayList();
    mockTomorrowList();
    mockWeekList();
    mockMonthList();
    return getAllTasks();
  }
}
