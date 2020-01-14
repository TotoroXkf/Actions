import 'package:flutter/material.dart';
import 'package:flutter_todo/constants.dart';
import 'package:flutter_todo/main/main_page.dart';
import 'package:flutter_todo/splash.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      initialRoute: "/",
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      routes: {
        INIT_ROUTE: (context) => SplashPage(),
        MAIN_PAGE_ROUTE: (context) => MainPage()
      },
    );
  }
}
