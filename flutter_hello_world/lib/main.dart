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
      home: MyWidget(),
    );
  }
}

class MyWidget extends StatefulWidget {
  @override
  _MyWidgetState createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  ScrollController controller = ScrollController();
  bool showButton = false;

  @override
  void initState() {
    controller.addListener(() {
      if (controller.offset < 1000 && showButton) {
        setState(() {
          showButton = false;
        });
      } else if (controller.offset >= 1000 && !showButton) {
        setState(() {
          showButton = true;
        });
      }
    });
  }

  @override
  void dispose() {
    controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Flutter'),
      ),
      body: Scrollbar(
        child: ListView.builder(
          itemBuilder: (BuildContext context, int index) {
            return ListTile(
              title: Text('$index'),
            );
          },
          itemCount: 100,
          itemExtent: 50,
          controller: controller,
        ),
      ),
      floatingActionButton: !showButton
          ? null
          : FloatingActionButton(
              child: Icon(Icons.arrow_upward),
              onPressed: () {
                //控制滑动到顶部
                controller.animateTo(
                  0,
                  duration: Duration(milliseconds: 1000),
                  curve: Curves.ease,
                );
              },
            ),
    );
  }
}
