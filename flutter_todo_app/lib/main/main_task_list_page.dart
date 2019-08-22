import 'package:flutter/material.dart';
import 'package:flutter_todo_app/data/mock.dart';
import 'package:flutter_todo_app/main/task_item.dart';
import 'package:flutter_todo_app/model/task.dart';
import 'package:flutter_todo_app/notification/task_item_check_notification.dart';

class MainTaskList extends StatefulWidget {
  @override
  _MainTaskListState createState() => _MainTaskListState();
}

class _MainTaskListState extends State<MainTaskList> {
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
      child: ReorderableListView(
        children: _todayTask.map((item) {
          return TaskItem(
            key: ObjectKey(item),
            task: item,
            index: _todayTask.indexOf(item),
          );
        }).toList(),
        onReorder: _onReorder,
      ),
    );
  }

  _onReorder(int oldIndex, int newIndex) {
    setState(() {
      if (newIndex == _todayTask.length) {
        newIndex = _todayTask.length - 1;
      }
      Task item = _todayTask.removeAt(oldIndex);
      _todayTask.insert(newIndex, item);
    });
  }
}
