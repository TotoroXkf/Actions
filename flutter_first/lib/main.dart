import 'package:flutter/material.dart';
import 'package:url_launcher/url_launcher.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'dart:io';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  void getWeatherData() async {
    try {
      HttpClient httpClient = new HttpClient();
      HttpClientRequest request = await httpClient.getUrl(
          Uri.parse("http://t.weather.sojson.com/api/weather/city/101030100"));
      HttpClientResponse response = await request.close();
      var result = await response.transform(utf8.decoder).join();
      print(result);
      httpClient.close();
    } catch (e) {
      print(e);
    }
  }

  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      debugShowCheckedModeBanner: false,
      title: "Flutter",
      home: new Scaffold(
        appBar: AppBar(
          title: new Text("第三方包的使用"),
        ),
        body: new Center(
          child: new RaisedButton(
            onPressed: getWeatherData,
            child: new Text("http请求示例"),
          ),
        ),
      ),
      theme: new ThemeData(
        primaryColor: Colors.lightBlue[800],
        accentColor: Colors.cyan[600],
        brightness: Brightness.dark,
      ),
    );
  }
}
