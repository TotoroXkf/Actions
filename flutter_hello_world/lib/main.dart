import 'package:flutter/cupertino.dart';
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
        body: Center(
          child: CustomPaint(
            size: Size(300, 300),
            painter: MyPainter(),
          ),
        ),
      ),
    );
  }
}

class MyPainter extends CustomPainter {
  @override
  void paint(Canvas canvas, Size size) {
    double eWidth = size.width / 15;
    double eHeight = size.height / 15; 
    Paint paint = Paint();
    paint.isAntiAlias = true;
    paint.style = PaintingStyle.fill;
    paint.color = Color(0x77cdb175);

    Rect rect = new Rect.fromLTRB(0, 0, size.width, size.height);
    canvas.drawRect(rect, paint);
    //..........
  }

  @override
  bool shouldRepaint(CustomPainter oldDelegate) {
    return true;
  }
}
