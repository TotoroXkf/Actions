import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  final List<Tab> tabs = [
    Tab(
      text: '选项卡一',
    ),
    Tab(
      text: '选项卡二',
    ),
  ];

  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Flutter',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: DefaultTabController(
        length: tabs.length,
        child: Scaffold(
          appBar: AppBar(
            title: Text("Flutter"),
            bottom: TabBar(tabs: tabs),
          ),
          body: TabBarView(
            children: tabs.map((Tab tab) {
              return Center(child: Text(tab.text));
            }).toList(),
          ),
        ),
      ),
    );
  }
}
