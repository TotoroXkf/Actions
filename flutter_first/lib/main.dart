import 'package:flutter/material.dart';
import 'package:english_words/english_words.dart';
import 'package:flutter_first/base_widget/Echo.dart';
import 'package:flutter_first/first_flutter_app/MyHomePage.dart';
import 'package:flutter_first/first_flutter_app/NewRouter.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      debugShowCheckedModeBanner: false,
      title: "Flutter",
      theme: new ThemeData(primarySwatch: Colors.blue),
      home: new Scaffold(
        appBar: AppBar(title:Text("Flutter")),
        body: Echo(text:"BigTotoro"),
      )
    );
  }
}




