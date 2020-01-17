import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_todo/base/Events.dart';
import 'package:flutter_todo/base/data_center.dart';
import 'package:flutter_todo/main/calender_widget.dart';
import 'package:flutter_todo/main/setting_widget.dart';
import 'package:flutter_todo/main/todo_list_widget.dart';

class MainPage extends StatefulWidget {
  @override
  _MainPageState createState() => _MainPageState();
}

class _MainPageState extends State<MainPage> with TickerProviderStateMixin {
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
  int _bottomCurrentIndex = 0;

  @override
  void initState() {
    super.initState();

    // 显示状态栏
    SystemChrome.setEnabledSystemUIOverlays([SystemUiOverlay.top]);

    eventBus.on<UpdateListUIEvent>().listen((event) {
      setState(() {});
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      bottomNavigationBar: BottomNavigationBar(
        showUnselectedLabels: false,
        currentIndex: _bottomCurrentIndex,
        items: _bottomItem,
        onTap: _onSelectBottomItem,
      ),
      body: _getBody(),
    );
  }

  Widget _getBody() {
    switch (_bottomCurrentIndex) {
      case 0:
        return TodoListWidget(dataCenter.getListModel());
      case 1:
        return CalenderWidget();
      case 2:
        return SettingWidget();
    }

    return null;
  }

  void _onSelectBottomItem(int index) {
    setState(() {
      _bottomCurrentIndex = index;
    });
  }
}
