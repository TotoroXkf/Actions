import 'package:flutter/material.dart';

class TextTest extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return DefaultTextStyle(
      style: TextStyle(
        color: Colors.red,
        fontSize: 20,
      ),
      textAlign: TextAlign.start,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Text("hello world"),
          Text("I am Totoro"),
          Text(
            "I am Totoro",
            style: TextStyle(color: Colors.blue, fontSize: 18, inherit: false),
          )
        ],
      ),
    );
  }
}
