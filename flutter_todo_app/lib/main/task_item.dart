import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_todo_app/model/task.dart';
import 'package:flutter_todo_app/notification/task_item_check_notification.dart';

// ignore: must_be_immutable
class TaskItem extends StatefulWidget {
  Task task;
  int index;

  // ignore: non_constant_identifier_names
  TaskItem({Key, key, this.task, this.index}) : super(key: key);

  @override
  _TaskItemState createState() => _TaskItemState();
}

class _TaskItemState extends State<TaskItem> {
  @override
  Widget build(BuildContext context) {
    if (widget.task == null) {
      return Container();
    }
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
                    getCheckBox(),
                    Expanded(
                      child: getNameText(),
                    ),
                    getDateText(),
                    SizedBox(
                      width: 6,
                    )
                  ],
                ),
              ),
            ),
          ),
        ),
      ],
    );
  }

  Widget getCheckBox() {
    return Checkbox(
      onChanged: (bool value) {
        setState(() {
          widget.task.checked = value;
          TaskItemCheckNotification(
            checked: widget.task.checked,
            index: widget.index,
          ).dispatch(context);
        });
      },
      value: widget.task.checked,
    );
  }

  Widget getNameText() {
    return Text(
      widget.task.name,
      style: TextStyle(
        color: widget.task.checked ? Colors.grey : Colors.black,
        decoration: widget.task.checked
            ? TextDecoration.lineThrough
            : TextDecoration.none,
      ),
    );
  }

  Widget getDateText() {
    return Text(
      widget.task.createDate.month.toString() +
          "/" +
          widget.task.createDate.day.toString(),
      style: TextStyle(
        fontSize: 12,
        color: Colors.grey[33],
      ),
    );
  }
}
