import 'package:flutter/material.dart';
import 'package:flutter/painting.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Flutter',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: Scaffold(
        appBar: AppBar(
          title: Text('Flutter'),
        ),
        body: Center(
          child: Container(
            width: 300,
            height: 300,
            decoration: BoxDecoration(
              gradient: LinearGradient(
                begin: const FractionalOffset(0.5, 0.0),
                end: const FractionalOffset(1.0, 1.0),
                colors: <Color>[
                  Colors.red,
                  Colors.blue,
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }
}
