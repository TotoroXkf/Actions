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
        body: Center(
          child: Padding(
            padding: EdgeInsets.all(20),
            child: Card(
              child: Column(
                children: <Widget>[
                  ListTile(
                    title: Text("Item1"),
                    subtitle: Text("SubItem1"),
                    leading: Icon(
                      Icons.home,
                      color: Colors.blue,
                    ),
                  ),
                  Divider(),
                  ListTile(
                    title: Text("Item2"),
                    subtitle: Text("SubItem2"),
                    leading: Icon(
                      Icons.school,
                      color: Colors.blue,
                    ),
                  ),
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }
}
