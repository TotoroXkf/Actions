import 'package:flutter/material.dart';
import 'package:flutter_todo_app/data/mock.dart';
import 'package:flutter_todo_app/main/task_item.dart';
import 'package:flutter_todo_app/model/task.dart';
import 'package:flutter_todo_app/notification/task_item_check_notification.dart';

class MainTaskList extends StatefulWidget {
  List<Task> taskList;

  MainTaskList({Key key, this.taskList}) : super(key: key) {
    if (taskList == null) {
      taskList = [];
    }
  }

  @override
  _MainTaskListState createState() => _MainTaskListState();
}

class _MainTaskListState extends State<MainTaskList> {
  List<Task> taskList;

  @override
  void initState() {
    super.initState();
    this.taskList = widget.taskList;
  }

  @override
  Widget build(BuildContext context) {
    return NotificationListener<TaskItemCheckNotification>(
      onNotification: (notification) {
        taskList[notification.index].checked = notification.checked;
        return true;
      },
      child: ReorderableListView(
        children: taskList.map((item) {
          return TaskItem(
            key: ObjectKey(item),
            task: item,
            index: taskList.indexOf(item),
          );
        }).toList(),
        onReorder: _onReorder,
      ),
    );
  }

  _onReorder(int oldIndex, int newIndex) {
    setState(() {
      if (newIndex == taskList.length) {
        newIndex = taskList.length - 1;
      }
      Task item = taskList.removeAt(oldIndex);
      taskList.insert(newIndex, item);
    });
  }
}
