import 'package:flutter/material.dart';

class DrawerMenuItemTapNotification extends Notification {
  int index;

  DrawerMenuItemTapNotification(this.index);
}

class MainPopMenuItemTapNotification extends Notification {
  String value;

  MainPopMenuItemTapNotification(this.value);
}
