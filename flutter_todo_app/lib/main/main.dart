import 'package:flutter/material.dart';
import 'package:flutter_todo_app/constants.dart';
import 'package:flutter_todo_app/main/main_drawer.dart';
import 'package:flutter_todo_app/notification/menu_item_tap_notification.dart';

class MainPage extends StatefulWidget {
  @override
  _MainPageState createState() => _MainPageState();
}

class _MainPageState extends State<MainPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Todo'),
      ),
      body: Center(
        child: Text('Main'),
      ),
      floatingActionButton: FloatingActionButton(
        child: Icon(Icons.add),
        onPressed: () {},
      ),
      drawer: NotificationListener<MenuItemTapNotification>(
        onNotification: (notification) {
          print(notification.index);
          return true;
        },
        child: MainDrawer(
          menuName: drawerMenuNames,
          icons: drawerMenuIcons,
        ),
      ),
    );
  }
}
