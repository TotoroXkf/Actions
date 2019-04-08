import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Flutter',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: Scaffold(
        appBar: AppBar(
          title: Text("Flutter"),
        ),
        drawer: Drawer(
          child: ListView(
            children: <Widget>[
              UserAccountsDrawerHeader(
                accountName: Text('大龙猫'),
                accountEmail: Text('xkf123456789@gmail.com'),
              ),
              ListTile(title: Text('item1')),
              ListTile(title: Text('item2')),
              ListTile(title: Text('item3')),
            ],
          ),
        ),
      ),
    );
  }
}
