import 'dart:math';

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
      home: MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  bool _showButton = false;
  ScrollController _controller = ScrollController();

  @override
  void initState() {
    super.initState();

    _controller.addListener(() {
      if (_controller.offset >= 1000 && !_showButton) {
        _setShowState(true);
      } else if (_controller.offset < 1000 && _showButton) {
        _setShowState(false);
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Flutter'),
      ),
      body: ListView.builder(
        itemBuilder: (context, index) {
          return ListTile(
            title: Text('$index'),
          );
        },
        controller: _controller,
        itemCount: 1000,
        itemExtent: 50,
      ),
      floatingActionButton: _getFloatingActionButton(),
    );
  }

  void _setShowState(bool newState) {
    setState(() {
      _showButton = newState;
    });
  }

  FloatingActionButton _getFloatingActionButton() {
    if (!_showButton) {
      return null;
    }
    return FloatingActionButton(
      child: Icon(Icons.arrow_upward),
      onPressed: () {

        _controller.animateTo(0, duration: Duration(seconds: 1), curve: Curves.ease);
      },
    );
  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }
}
