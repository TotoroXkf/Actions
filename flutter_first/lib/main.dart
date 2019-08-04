import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: Scaffold(
        appBar: AppBar(
          title: Text("Flutter"),
        ),
        body: MyHomePage(),
      ),
    );
  }
}

class MyHomePage extends StatefulWidget {
  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  GlobalKey _globalKey = new GlobalKey<FormState>();

  @override
  Widget build(BuildContext context) {
    return Container(
      width: 100,
      height: 100,
      decoration: BoxDecoration(
        gradient:
            LinearGradient(colors: [Colors.lightGreenAccent, Colors.blue]),
        borderRadius: BorderRadius.circular(4),
      ),
      padding: EdgeInsets.all(24),
      child: Text('Xkf'),
      margin: EdgeInsets.all(32),
      alignment: Alignment.center,
      transform: Matrix4.rotationZ(.2),
    );
  }
}
