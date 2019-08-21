import 'dart:async';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class SplashPage extends StatefulWidget {
  @override
  _SplashPageState createState() => _SplashPageState();
}

class _SplashPageState extends State<SplashPage> {
  @override
  void initState() {
    super.initState();
    startMainPageByDelay(context);
    SystemChrome.setEnabledSystemUIOverlays([]);
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
        gradient: LinearGradient(
          begin: Alignment.topLeft,
          end: Alignment.bottomRight,
          colors: [Color(0xFFABDCFF), Color(0xAA0396FF)],
        ),
      ),
      child: Stack(
        children: <Widget>[
          Align(
            alignment: Alignment.center,
            child: FlutterLogo(
              size: 120,
            ),
          ),
          Align(
            alignment: Alignment.bottomCenter,
            child: Container(
              margin: EdgeInsets.all(28),
              child: Text(
                'Flutter Todo',
                style: TextStyle(
                    fontSize: 25,
                    color: Colors.white,
                    decoration: TextDecoration.none),
              ),
            ),
          ),
        ],
      ),
    );
  }

  void startMainPageByDelay(BuildContext context) async {
    Future.delayed(Duration(seconds: 1)).then((e) {
      Navigator.pushNamedAndRemoveUntil(
        context,
        "main",
        (route) => route == null,
      );
    });
  }
}
