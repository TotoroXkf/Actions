import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
        debugShowCheckedModeBanner: false,
        title: "Flutter",
        theme: new ThemeData(primarySwatch: Colors.blue),
        home: new Scaffold(
          backgroundColor: Colors.grey[100],
          appBar: AppBar(title: Text("Flutter")),

        ));
  }
}

