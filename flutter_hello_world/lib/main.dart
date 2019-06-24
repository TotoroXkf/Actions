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
        body: MyWidget(),
      ),
    );
  }
}

class MyWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      alignment: Alignment.topCenter,
      child: InkWell(
        child: Hero(
          tag: 'avatar',
          child: Image.asset(
            'images/image.png',
            width: 100,
            height: 100,
          ),
        ),
        onTap: () {
          Navigator.push(
            context,
            PageRouteBuilder(
              transitionDuration: Duration(seconds: 2),
              pageBuilder: (BuildContext context, Animation animation,
                  Animation secondaryAnimation) {
                return FadeTransition(
                  opacity: animation,
                  child: MyPage(),
                );
              },
            ),
          );
        },
      ),
    );
  }
}

class MyPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Flutter'),
      ),
      body: Center(
        child: Hero(
          tag: 'avatar',
          child: Image.asset(
            'images/image.png',
            width: 200,
            height: 200,
          ),
        ),
      ),
    );
  }
}
