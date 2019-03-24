import 'package:flutter/material.dart';
import 'package:url_launcher/url_launcher.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      debugShowCheckedModeBanner: false,
      title: "Flutter",
      home: new Scaffold(
        appBar: AppBar(
          title: new Text("第三方包的使用"),
        ),
        body: new Center(
          child: new RaisedButton(
            onPressed: () {
              const url = "https://www.baidu.com";
              launch(url);
            },
            child: new Text("打开百度"),
          ),
        ),
      ),
      theme: new ThemeData(
        primaryColor: Colors.lightBlue[800],
        accentColor: Colors.cyan[600],
        brightness: Brightness.dark,
      ),
    );
  }
}

class MyHomePage extends StatelessWidget {
  final String title;

  const MyHomePage({Key key, this.title}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(
        title: new Text(title),
      ),
      body: new Center(
        child: new Container(
          color: Theme.of(context).accentColor,
          child: new Text(
            '带背景颜色的文本组件',
            style: Theme.of(context).textTheme.title,
          ),
        ),
      ),
      floatingActionButton: new Theme(
        data: Theme.of(context).copyWith(accentColor: Colors.grey),
        child: new FloatingActionButton(
          onPressed: null,
          child: new Icon(
            Icons.computer,
          ),
        ),
      ),
    );
  }
}
