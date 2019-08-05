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
  var dataList = ["**END**"];

  @override
  Widget build(BuildContext context) {
    return Scrollbar(
      child: ListView.separated(
          itemBuilder: (context, index) {
            if (dataList[index] == "**END**") {
              if (dataList.length >= 200) {
                return Container(
                    alignment: Alignment.center,
                    padding: EdgeInsets.all(16.0),
                    child: Text(
                      "没有更多了",
                      style: TextStyle(color: Colors.grey),
                    ));
              } else {
                loadData();
                return Container(
                  padding: const EdgeInsets.all(16.0),
                  alignment: Alignment.center,
                  child: SizedBox(
                      width: 24.0,
                      height: 24.0,
                      child: CircularProgressIndicator(strokeWidth: 2.0)),
                );
              }
            }
            return ListTile(title: Text(dataList[index]));
          },
          separatorBuilder: (context, index) {
            return Divider(
              color: Colors.red,
            );
          },
          itemCount: dataList.length),
    );
  }

  void loadData() {
    Future.delayed(Duration(seconds: 2)).then((e) {
      var newData = <String>[];
      for (int i = 0; i < 20; i++) {
        newData.add(Random.secure().nextInt(1000).toString());
      }
      dataList.insertAll(dataList.length - 1, newData);
      setState(() {});
    });
  }
}
