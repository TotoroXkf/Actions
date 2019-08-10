import 'package:dio/dio.dart';
import 'package:flutter/material.dart';

class MyHomePage extends StatefulWidget {
  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  @override
  Widget build(BuildContext context) {
    return Container();
  }
}

Future downloadWithChunks(
  url,
  savePath, {
  ProgressCallback onReceiveProgress,
}) async {
  const firstChunk = 102;
  const maxChunk = 3;
  
  int total = 0;
  Dio dio = Dio();
  var progress =<int>[];

  createCallbakc(int no){
    return (int received,_){
      progress[no] = received;
      if(onReceiveProgress!=null && total >0){
        onReceiveProgress(progress.reduce((a,b)=>a+b),total);
      }
    };
  }

  Future<Response> downloadChunk(url,sart,end,no){
    progress.add(0);
    --end;

    return dio.download(url, savePath);
  }
}
