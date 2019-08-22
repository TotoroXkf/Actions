import 'package:flutter/material.dart';
import 'package:flutter_todo_app/data/mock.dart';
import 'package:flutter_todo_app/main/task_item.dart';
import 'package:flutter_todo_app/model/task.dart';

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
    return ListView.builder(
      itemBuilder: (context, index) {
        return TaskItem(
          taskName: _todayTask[index].name,
          checkedValue: _todayTask[index].checked,
        );
      },
      itemCount: _todayTask.length,
    );
  }
}
