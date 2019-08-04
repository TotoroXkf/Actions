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
  TextEditingController _textEditingController1 = new TextEditingController();
  TextEditingController _textEditingController2 = new TextEditingController();
  GlobalKey _globalKey = new GlobalKey<FormState>();

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.all(16),
      child: Form(
        key: _globalKey,
        autovalidate: true,
        child: Column(
          children: <Widget>[
            TextFormField(
                autofocus: true,
                controller: _textEditingController1,
                decoration: InputDecoration(
                    labelText: "用户名",
                    hintText: "用户名或邮箱",
                    icon: Icon(Icons.person)),
                // 校验用户名
                validator: (v) {
                  return v.trim().length > 0 ? null : "用户名不能为空";
                }),
            TextFormField(
                controller: _textEditingController2,
                decoration: InputDecoration(
                    labelText: "密码",
                    hintText: "您的登录密码",
                    icon: Icon(Icons.lock)),
                obscureText: true,
                //校验密码
                validator: (v) {
                  return v.trim().length > 5 ? null : "密码不能少于6位";
                }),
            Padding(
              padding: EdgeInsets.fromLTRB(0, 28, 0, 0),
              child: Row(
                children: <Widget>[
                  Expanded(
                    child: RaisedButton(
                      child: Text('登陆'),
                      color: Theme.of(context).primaryColor,
                      textColor: Colors.white,
                      onPressed: () {
                        if ((_globalKey.currentState as FormState).validate()) {
                          print("pass");
                        } else {
                          print("not");
                        }
                      },
                    ),
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
