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


  @override
  Widget build(BuildContext context) {
    var devider1 = Divider(color: Colors.blue);
    var devider2 = Divider(color: Colors.green);

    return Scrollbar(
      child: ListView.separated(
          itemBuilder: (context, index) {
            return ListTile(title: Text('$index'));
          },
          separatorBuilder: (context, index) {
            return index % 2 == 0 ? devider1 : devider2;
          },
          itemCount: 100),
    );
  }
}
