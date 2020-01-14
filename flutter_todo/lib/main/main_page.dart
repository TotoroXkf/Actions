import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_todo/data_center.dart';
import 'package:flutter_todo/main/calender_widget.dart';
import 'package:flutter_todo/main/setting_widget.dart';

class MainPage extends StatefulWidget {
  @override
  _MainPageState createState() => _MainPageState();
}

class _MainPageState extends State<MainPage> with TickerProviderStateMixin {
  DataCenter _dataCenter = DataCenter.getInstance();

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

  List<Tab> tabs = [];
  TabController _tabController;

  @override
  void initState() {
    super.initState();

    // 显示状态栏
    SystemChrome.setEnabledSystemUIOverlays([SystemUiOverlay.top]);

    _tabController =
        TabController(length: _dataCenter.getListNum(), vsync: this);
    List<String> names = _dataCenter.getListName();
    for (int i = 0; i < _dataCenter.getListNum(); i++) {
      String name = names[i];
      tabs.add(Tab(text: name));
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Flutter Todo'),
        bottom: _getTabBar(),
      ),
      bottomNavigationBar: BottomNavigationBar(
        showUnselectedLabels: false,
        currentIndex: _currentIndex,
        items: _bottomItem,
        onTap: _onSelectBottomItem,
      ),
      body: _getBody(),
    );
  }

  Widget _getBody() {
    switch (_currentIndex) {
      case 0:
        if (_showTab()) {
          return TabBarView(
            controller: _tabController,
            children: [],
          );
        }
        break;
      case 1:
        return CalenderWidget();
      case 2:
        return SettingWidget();
    }

    return null;
  }

  Widget _getTabBar() {
    if (!_showTab()) {
      return null;
    }
    return TabBar(controller: _tabController, tabs: tabs);
  }

  bool _showTab() {
    if (_currentIndex == 0) {
      return true;
    }
    return false;
  }

  void _onSelectBottomItem(int index) {
    setState(() {
      _currentIndex = index;
    });
  }
}
