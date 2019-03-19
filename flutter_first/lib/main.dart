import 'package:flutter/material.dart';
import 'package:flutter_first/base_widget/ButtonTest.dart';
import 'package:flutter_first/base_widget/ImageTest.dart';
import 'package:flutter_first/base_widget/TextTest.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
        debugShowCheckedModeBanner: false,
        title: "Flutter",
        theme: new ThemeData(primarySwatch: Colors.blue),
        home: new Scaffold(
          appBar: AppBar(title: Text("Flutter")),
          body: ImageTest(),
        ));
  }
}
