import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class MainPage extends StatefulWidget {
  @override
  _MainPageState createState() => _MainPageState();
}

class _MainPageState extends State<MainPage> {
  List<BottomNavigationBarItem> _bottomItem = [
    BottomNavigationBarItem(
      icon: Icon(Icons.list),
      title: Text('清单'),
    ),
    BottomNavigationBarItem(
      icon: Icon(Icons.calendar_today),
      title: Text('日程'),
    ),
    BottomNavigationBarItem(
      icon: Icon(Icons.settings),
      title: Text('设置'),
    ),
  ];

  int _currentIndex = 0;

  @override
  void initState() {
    super.initState();

    // 显示状态栏
    SystemChrome.setEnabledSystemUIOverlays([SystemUiOverlay.top]);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Flutter Todo'),
        bottom: TabBar(
          tabs: <Widget>[],
        ),
      ),
      bottomNavigationBar: BottomNavigationBar(
        showUnselectedLabels: false,
        currentIndex: _currentIndex,
        items: _bottomItem,
        onTap: _onSelectBottomItem,
      ),
    );
  }

  void _onSelectBottomItem(int index) {
    setState(() {
      _currentIndex = index;
    });
  }
}
