import 'package:flutter/material.dart';
import 'package:flutter_todo_app/main/main_drawer.dart';

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
      drawer: NotificationListener(
        child: MainDrawer(

        ),
      ),
    );
  }
}
