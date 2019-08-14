import 'package:flutter/material.dart';
import 'package:flutter_todo_app/main/main.dart';
import 'package:flutter_todo_app/splash/splash.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      initialRoute: "/",
      title: 'Flutter Demo',
      routes: {
        "main":(context)=>MainPage(),
        "/":(context) =>SplashPage()
      },
      theme: ThemeData(
        primarySwatch: Colors.red,
      ),
      //home: SplashPage(),
    );
  }
}
