import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class TaskItem extends StatefulWidget {
  @override
  _TaskItemState createState() => _TaskItemState();
}

class _TaskItemState extends State<TaskItem> {
  @override
  Widget build(BuildContext context) {
    return Row(
      children: <Widget>[
        Expanded(
          child: Padding(
            padding: EdgeInsets.symmetric(horizontal: 10),
            child: Card(
              elevation: 8,
              child: Text('Xkf'),
            ),
          ),
        ),
      ],
    );
  }
}
