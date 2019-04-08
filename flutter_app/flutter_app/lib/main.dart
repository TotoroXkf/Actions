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
          title: Text("Flutter"),
        ),
        body: Center(
          child: FlatButton(
            onPressed: null,
            child: PopupMenuButton(
              itemBuilder: (BuildContext context) => <PopupMenuEntry<Code>>[
                    PopupMenuItem<Code>(
                      value: Code.Java,
                      child: Text('Java'),
                    ),
                    PopupMenuItem<Code>(
                      value: Code.C,
                      child: Text('C'),
                    ),
                    PopupMenuItem<Code>(
                      value: Code.Python,
                      child: Text('Python'),
                    ),
                    PopupMenuItem<Code>(
                      value: Code.Dart,
                      child: Text('Dart'),
                    ),
                    PopupMenuItem<Code>(
                      value: Code.Go,
                      child: Text('Go'),
                    ),
                  ],
            ),
          ),
        ),
      ),
    );
  }
}

enum Code { Java, C, Python, Dart, Go }
