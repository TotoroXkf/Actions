import 'package:flutter/material.dart';

class CalenderWidget extends StatefulWidget {
  @override
  _CalenderWidgetState createState() => _CalenderWidgetState();
}

class _CalenderWidgetState extends State<CalenderWidget> {
  @override
  Widget build(BuildContext context) {
    return Container(
      child: Center(
        child: Text('Calender'),
      ),
    );
  }
}
