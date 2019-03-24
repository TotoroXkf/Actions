import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      debugShowCheckedModeBanner: false,
      title: "Flutter",
      theme: new ThemeData(
        primaryColor: Colors.lightBlue[800],
      ),
      routes: {
        '/first': (BuildContext context) => FirstPage(),
        '/second': (BuildContext context) => SecondPage(),
      },
      initialRoute: '/first',
      home: MyWidget(),
    );
  }
}

class MyWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return MyWidgetState();
  }
}

class MyWidgetState extends State<MyWidget> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('主页'),
      ),
      body: Text("主页"),
      floatingActionButton: FloatingActionButton(
        onPressed: null,
        child: Icon(Icons.add),
      ),
    );
  }
}

class FirstPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: AppBar(
        title: Text('这是第一个页面'),
      ),
      body: RaisedButton(
        onPressed: () {
          Navigator.pushNamed(context, '/second');
        },
        child: new Text("跳转到第二个页面"),
      ),
    );
  }
}

class SecondPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: AppBar(
        title: Text('这是第二个页面'),
      ),
      body: RaisedButton(
        onPressed: () {
          Navigator.pushNamed(context, '/first');
        },
        child: new Text("跳转到第一个页面"),
      ),
    );
  }
}
