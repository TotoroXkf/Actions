import 'package:flutter/material.dart';

class ButtonTest extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Center(
      child: RaisedButton(
        onPressed: () {},
        textColor: Colors.white,
        color: Colors.blue,
        highlightColor: Colors.blue[700],
        splashColor: Colors.grey,
        shape:RoundedRectangleBorder(borderRadius: BorderRadius.circular(20.0)),
        child: Text("button"),
      ),
    );
  }
}
