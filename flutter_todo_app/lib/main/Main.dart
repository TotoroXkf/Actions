import 'package:flutter/material.dart';
import 'package:flutter_todo_app/Constants.dart';
import 'package:flutter_todo_app/main/MainDrawer.dart';
import 'package:flutter_todo_app/main/MainTaskListPage.dart';
import 'package:flutter_todo_app/notification/MenuItemTapNotification.dart';

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
        child: MainTaskListPage(),
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
