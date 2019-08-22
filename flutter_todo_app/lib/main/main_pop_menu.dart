import 'package:flutter/material.dart';
import 'package:flutter_todo_app/data/constants.dart';
import 'package:flutter_todo_app/notification/menu_item_tap_notification.dart';

class MainPopMenu extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return PopupMenuButton(

      onSelected: (String value) {
        MainPopMenuItemTapNotification(value).dispatch(context);
      },
      itemBuilder: (BuildContext context) {
        List<PopupMenuItem<String>> result = [];
        for (String value in mainPopMenuNames) {
          PopupMenuItem<String> item = PopupMenuItem(
            value: value,
            child: Text(value),
          );
          result.add(item);
        }
        return result;
      },
    );
  }
}
