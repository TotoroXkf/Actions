import 'package:flutter/material.dart';
import 'package:english_words/english_words.dart';
import 'package:flutter_first/MyHomePage.dart';
import 'package:flutter_first/NewRouter.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      debugShowCheckedModeBanner: false,
      title: "Flutter",
      theme: new ThemeData(primarySwatch: Colors.blue),
      home: new MyHomePage(title: "Flutter Demo Home Page"),
      routes: {"new_page": (context) => NewRouter()},
    );
  }
}




