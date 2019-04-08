import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class ItemModel {
  const ItemModel({this.title, this.icon});

  final String title;
  final IconData icon;
}

const List<ItemModel> items = [
  ItemModel(title: "自驾", icon: Icons.directions_car),
  ItemModel(title: "自行车", icon: Icons.directions_bike),
  ItemModel(title: "轮船", icon: Icons.directions_boat),
  ItemModel(title: "公交车", icon: Icons.directions_bus),
  ItemModel(title: "火车", icon: Icons.directions_railway),
  ItemModel(title: "步行", icon: Icons.directions_walk),
];

class SelectedView extends StatelessWidget {
  ItemModel model;

  @override
  Widget build(BuildContext context) {
    final textStyle = Theme.of(context).textTheme.display1;

    return Card(
      color: Colors.white,
      child: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Icon(
              model.icon,
              size: 128.0,
              color: textStyle.color,
            ),
            Text(
              model.title,
              style: textStyle,
            )
          ],
        ),
      ),
    );
  }

  SelectedView({Key key, this.model}) : super(key: key);
}

class MyApp extends StatelessWidget {
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Flutter',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: new DefaultTabController(
          length: items.length,
          child: Scaffold(
            appBar: AppBar(
                title: Text("Flutter"),
                bottom: TabBar(
                  isScrollable: true,
                  tabs: items.map((ItemModel model) {
                    return Tab(
                      text: model.title,
                      icon: Icon(model.icon),
                    );
                  }).toList(),
                )),
            body: TabBarView(
              children: items.map((ItemModel model) {
                return Padding(
                  padding: EdgeInsets.all(10),
                  child: SelectedView(model: model),
                );
              }).toList(),
            ),
          )),
    );
  }
}
