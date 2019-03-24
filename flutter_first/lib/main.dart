import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      debugShowCheckedModeBanner: false,
      title: "Flutter",
      home: new Scaffold(
        appBar: AppBar(
          title: new Text("Flutter"),
        ),
        body: new MyWidget(),
      ),
      theme: new ThemeData(
        primaryColor: Colors.lightBlue[800],
      ),
    );
  }
}

class MyWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return MyWidgetState();
  }
}

class MyWidgetState extends State<MyWidget> {
  GlobalKey<FormState> globalKey = new GlobalKey<FormState>();
  String userName;
  String password;

  void login() {
    var loginForm = globalKey.currentState;
    if (loginForm.validate()) {
      loginForm.save();
      print(userName);
      print("-----");
      print(password);
    }
  }

  @override
  Widget build(BuildContext context) {
    return new Column(
      children: <Widget>[
        new Container(
          padding: EdgeInsets.all(16.0),
          child: new Form(
            key: globalKey,
            child: new Column(
              children: <Widget>[
                new TextFormField(
                  decoration: new InputDecoration(
                    hintText: "请输入用户名",
                  ),
                  onSaved: (value) {
                    userName = value;
                  },
                  onFieldSubmitted: (value) {},
                ),
                new TextFormField(
                  decoration: new InputDecoration(
                    hintText: "请输入密码",
                  ),
                  onSaved: (value) {
                    password = value;
                  },
                  obscureText: true,
                  validator: (value) {
                    return value.length < 6 ? "密码长度小于6位" : null;
                  },
                ),
              ],
            ),
          ),
        ),
        new SizedBox(
          width: 340,
          height: 42.0,
          child: new RaisedButton(
            onPressed: login,
            child: new Text(
              "登录",
              style: new TextStyle(fontSize: 18.0),
            ),
          ),
        ),
      ],
    );
  }
}
