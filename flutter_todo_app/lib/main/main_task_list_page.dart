import 'package:flutter/material.dart';
import 'package:flutter_todo_app/main/task_item.dart';

class MainTaskListPage extends StatefulWidget {
  @override
  _MainTaskListPageState createState() => _MainTaskListPageState();
}

class _MainTaskListPageState extends State<MainTaskListPage> {
  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      itemBuilder: (context, index) {
        return TaskItem();
      },
      itemCount: 20,
    );
  }
}
