import 'dart:io';

import 'package:flutter/material.dart';

class SplashPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    startMainPageByDelay().then((e){
      print("start");
    });

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
                'Flutter-Todo',
                style: TextStyle(
                    color: Colors.white,
                    fontSize: 20,
                    decoration: TextDecoration.none),
              ),
            ),
          ),
        ],
      ),
    );
  }

    Future<void> startMainPageByDelay() async{
    sleep(Duration(seconds: 1));
  }
}
