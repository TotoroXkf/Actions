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
        body: Container(
          decoration: BoxDecoration(color: Colors.grey),
          child: Column(
            children: <Widget>[
              Expanded(
                child: Container(
                  width: 150,
                  height: 150,
                  decoration: BoxDecoration(
                    border: Border.all(width: 10, color: Colors.blueGrey),
                    borderRadius: BorderRadius.all(Radius.circular(8)),
                  ),
                  margin: EdgeInsets.all(4),
                  child: FlatButton(
                    onPressed: null,
                    child: Text('Button1'),
                  ),
                ),
              ),
              Expanded(
                child: Container(
                  width: 150,
                  height: 150,
                  decoration: BoxDecoration(
                    border: Border.all(width: 10, color: Colors.blueGrey),
                    borderRadius: BorderRadius.all(Radius.circular(8)),
                  ),
                  margin: EdgeInsets.all(4),
                  child: FlatButton(
                    onPressed: null,
                    child: Text('Button2'),
                  ),
                ),
              ),
              Expanded(
                child: Container(
                  width: 150,
                  height: 150,
                  decoration: BoxDecoration(
                    border: Border.all(width: 10, color: Colors.blueGrey),
                    borderRadius: BorderRadius.all(Radius.circular(8)),
                  ),
                  margin: EdgeInsets.all(4),
                  child: FlatButton(
                    onPressed: null,
                    child: Text('Button3'),
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
