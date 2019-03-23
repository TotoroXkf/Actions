import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
        debugShowCheckedModeBanner: false,
        title: "Flutter",
        theme: new ThemeData(primarySwatch: Colors.blue),
        home: new Scaffold(
          backgroundColor: Colors.grey[100],
          appBar: AppBar(title: Text("Flutter")),
          body: MyWidget(),
        ));
  }
}

class MyWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return MyWidgetState();
  }
}

class MyWidgetState extends State<MyWidget> {
  bool switchSelected = true;
  bool checkboxSelected = true;

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Column(
        children: <Widget>[
          Switch(
            value: switchSelected,
            onChanged: (value) {
              setState(() {
                switchSelected = value;
              });
            },
          ),
          Checkbox(
            value: checkboxSelected,
            onChanged: (value) {
              setState(() {
                checkboxSelected = value;
              });
            },
          )
        ],
      ),
    );
  }
}
