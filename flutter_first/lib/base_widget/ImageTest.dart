import 'package:flutter/material.dart';

class ImageTest extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    String icons = "";
    icons += "\uE914";
    icons += " \uE000";
    icons += " \uE90D";
    return Center(
        child: Row(
      mainAxisAlignment: MainAxisAlignment.center,
      children: <Widget>[
        Icon(Icons.accessible, color: Colors.green),
        Icon(Icons.error, color: Colors.green),
        Icon(Icons.fingerprint, color: Colors.green),
      ],
    ));
  }
}
