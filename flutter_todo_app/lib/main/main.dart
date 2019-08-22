import 'package:flutter/material.dart';
import 'package:flutter_todo_app/data/constants.dart';
import 'package:flutter_todo_app/main/main_drawer.dart';
import 'package:flutter_todo_app/main/main_pop_menu.dart';
import 'package:flutter_todo_app/main/main_task_list_page.dart';
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
        actions: <Widget>[
          NotificationListener<MainPopMenuItemTapNotification>(
            onNotification: (notification) {
              return true;
            },
            child: MainPopMenu(),
          ),
        ],
      ),
      body: MainTaskList(),
      floatingActionButton: FloatingActionButton(
        child: Icon(Icons.add),
        onPressed: () {},
      ),
      drawer: NotificationListener<DrawerMenuItemTapNotification>(
        onNotification: (notification) {
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
