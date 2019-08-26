import 'package:flutter/material.dart';
import 'package:flutter_todo_app/main/task_item.dart';
import 'package:flutter_todo_app/model/task.dart';
import 'package:flutter_todo_app/notification/task_item_check_notification.dart';

// ignore: must_be_immutable
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
  @override
  Widget build(BuildContext context) {
    return NotificationListener<TaskItemCheckNotification>(
      onNotification: (notification) {
        widget.taskList[notification.index].checked = notification.checked;
        return true;
      },
      child: ReorderableListView(
        children: widget.taskList.map((item) {
          return TaskItem(
            key: ObjectKey(item),
            task: item,
            index: widget.taskList.indexOf(item),
          );
        }).toList(),
        onReorder: _onReorder,
      ),
    );
  }

  _onReorder(int oldIndex, int newIndex) {
    setState(() {
      if (newIndex == widget.taskList.length) {
        newIndex = widget.taskList.length - 1;
      }
      Task item = widget.taskList.removeAt(oldIndex);
      widget.taskList.insert(newIndex, item);
    });
  }
}
