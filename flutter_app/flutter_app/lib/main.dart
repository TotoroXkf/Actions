import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  Widget build(BuildContext context) {
    final TextEditingController controller = TextEditingController();
    controller.addListener(() {
      print("control " + controller.text);
    });
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Flutter',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: Scaffold(
        appBar: AppBar(
          title: Text("Flutter"),
        ),
        body: Center(
          child: Padding(
            padding: EdgeInsets.all(20),
            child: TextField(
              controller: controller,
              maxLength: 30,
              maxLines: 1,
              autocorrect: true,
              autofocus: true,
              obscureText: false,
              textAlign: TextAlign.start,
              style: TextStyle(
                fontSize: 26,
                color: Colors.green,
              ),
              onChanged: (String text) {
                print("onChang " + text);
              },
              onSubmitted: (String text) {},
              enabled: true,
              decoration: InputDecoration(
                fillColor: Colors.green.shade200,
                filled: true,
                helperText: "用户名",
                prefixIcon: Icon(Icons.person),
                suffixText: '用户名',
              ),
            ),
          ),
        ),
      ),
    );
  }
}
