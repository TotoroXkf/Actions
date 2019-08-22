import 'package:flutter/material.dart';

class TaskItemCheckNotification extends Notification {
  int index;
  bool checked;

  TaskItemCheckNotification({this.index, this.checked});
}
