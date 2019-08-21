import 'package:flutter/material.dart';


// ignore: must_be_immutable
class MainDrawer extends StatefulWidget {
  List<String> menuName;
  List<IconData> icons;

  MainDrawer({Key key, this.menuName, this.icons}) : super(key: key);

  @override
  _MainDrawerState createState() => _MainDrawerState();
}

class _MainDrawerState extends State<MainDrawer> {
  @override
  Widget build(BuildContext context) {
    return Drawer(
      child: Column(
        children: <Widget>[
          UserAccountsDrawerHeader(
            accountName: Text('大龙猫'),
            accountEmail: Text('xkf123456789@gmail.com'),
            decoration: BoxDecoration(
              gradient: LinearGradient(
                  colors: <Color>[Color(0xFF5EFCE8), Color(0xFF736EFE)]),
            ),
            currentAccountPicture: ClipOval(
              child: Image.asset('images/avatar.png'),
            ),
          ),
          Expanded(
            child: ListView.builder(
              itemCount: widget.menuName.length,
              itemBuilder: (context, index) {
                return Container(
                  margin: EdgeInsets.only(bottom: 20),
                  child: Row(
                    children: <Widget>[
                      SizedBox(
                        width: 10,
                      ),
                      Icon(Icons.settings),
                      SizedBox(
                        width: 16,
                      ),
                      Text('xkf')
                    ],
                  ),
                );
              },
            ),
          )
        ],
      ),
    );
  }
}
