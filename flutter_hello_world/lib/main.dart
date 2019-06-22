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

//固定写法
class ShareDataWidget extends InheritedWidget {
  const ShareDataWidget({
    //data是自定义的
    @required this.data,
    Key key,
    @required Widget child,
  })  : assert(child != null),
        super(key: key, child: child);

  final int data;

  static ShareDataWidget of(BuildContext context) {
    return context.inheritFromWidgetOfExactType(ShareDataWidget)
        as ShareDataWidget;
  }

  @override
  bool updateShouldNotify(ShareDataWidget old) {
    return old.data != data;
  }
}

class DataWidget extends StatefulWidget {
  @override
  _DataWidgetState createState() => _DataWidgetState();
}

class _DataWidgetState extends State<DataWidget> {
  @override
  Widget build(BuildContext context) {
    //使用共享数据
    return Center(
      child: Text(ShareDataWidget.of(context).data.toString()),
    );
  }

  //共享数据发生改变时调用
  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    print('change');
  }
}

class MyWidget extends StatefulWidget {
  @override
  _MyWidgetState createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  int count = 0;

  @override
  Widget build(BuildContext context) {
    return Center(
      //这个组件下的所有组件都可以享用公共数据
      child: ShareDataWidget(
        data: count,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Padding(
              padding: EdgeInsets.all(16),
              child: DataWidget(),
            ),
            RaisedButton(
              onPressed: () {
                setState(() {
                  count++;
                });
              },
              child: Text('增加'),
            )
          ],
        ),
      ),
    );
  }
}
