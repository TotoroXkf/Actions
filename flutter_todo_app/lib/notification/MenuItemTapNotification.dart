import 'package:flutter/material.dart';

class MenuItemTapNotification extends Notification {
  MenuItemTapNotification(this.index);

  final int index;
}
