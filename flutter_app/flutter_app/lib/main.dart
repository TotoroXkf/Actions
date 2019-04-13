import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

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
        body: Stack(
          children: <Widget>[
            Align(
              alignment: FractionalOffset.topLeft,
              child: FlatButton(
                onPressed: null,
                child: Text('Button'),
              ),
            ),
            Align(
              alignment: FractionalOffset.topRight,
              child: FlatButton(
                color: Colors.blue,
                onPressed: null,
                child: Text('Button'),
              ),
            ),
            Align(
              alignment: FractionalOffset.center,
              child: FlatButton(
                onPressed: null,
                child: Text('Button'),
              ),
            ),
            Align(
              alignment: FractionalOffset.bottomLeft,
              child: FlatButton(
                onPressed: null,
                child: Text('Button'),
              ),
            ),
            Align(
              alignment: FractionalOffset.bottomRight,
              child: FlatButton(
                onPressed: null,
                child: Text('Button'),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
