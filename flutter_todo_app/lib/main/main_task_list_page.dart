import 'package:flutter/material.dart';
import 'package:flutter_todo_app/data/mock.dart';
import 'package:flutter_todo_app/main/task_item.dart';
import 'package:flutter_todo_app/model/task.dart';
import 'package:flutter_todo_app/notification/task_item_check_notification.dart';

class MainTaskListPage extends StatefulWidget {
  @override
  _MainTaskListPageState createState() => _MainTaskListPageState();
}

class _MainTaskListPageState extends State<MainTaskListPage> {
  List<Task> _todayTask;

  @override
  void initState() {
    super.initState();
    _todayTask = fillTodayList();
  }

  @override
  Widget build(BuildContext context) {
    return NotificationListener<TaskItemCheckNotification>(
      onNotification: (notification) {
        _todayTask[notification.index].checked = notification.checked;
        return true;
      },
      child: ListView.builder(
        itemBuilder: (context, index) {
          return TaskItem(
            task: _todayTask[index],
            index: index,
          );
        },
        itemCount: _todayTask.length,
      ),
    );
  }
}
