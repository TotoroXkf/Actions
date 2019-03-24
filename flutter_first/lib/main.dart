import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      debugShowCheckedModeBanner: false,
      title: "Flutter",
      theme: new ThemeData(
        primaryColor: Colors.blue,
      ),
      home: Scaffold(
        appBar: appBar,
        floatingActionButton: FloatingActionButton(
          onPressed: () {},
          child: Icon(Icons.add),
        ),
      ),
    );
  }
}

Widget appBar = AppBar(
  title: Text('AppBar'),
  actions: <Widget>[
    IconButton(
      onPressed: () {},
      icon: Icon(Icons.search),
      tooltip: '搜索',
    ),
    IconButton(
      onPressed: () {},
      icon: Icon(Icons.add),
      tooltip: '更多',
    )
  ],
);
