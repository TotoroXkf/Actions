import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class TaskItem extends StatefulWidget {
  String taskName;
  bool checkedValue;

  // ignore: non_constant_identifier_names
  TaskItem({Key, key, this.taskName = "", this.checkedValue = false})
      : super(key: key);

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
            padding: EdgeInsets.symmetric(horizontal: 4),
            child: Card(
              elevation: 8,
              child: Container(
                height: 60,
                padding: EdgeInsets.fromLTRB(4, 20, 4, 20),
                child: Row(
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: <Widget>[
                    Checkbox(
                      onChanged: (bool value) {
                        setState(() {
                          widget.checkedValue = value;
                        });
                      },
                      value: widget.checkedValue,
                    ),
                    Text(widget.taskName),
                  ],
                ),
              ),
            ),
          ),
        ),
      ],
    );
  }
}
