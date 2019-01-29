import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
	@override
	Widget build(BuildContext context) {
		var title = "Flutter";
		var theme = new ThemeData(
			primarySwatch: Colors.blue,
		);
		
		return MaterialApp(
			title: title,
			theme: theme,
			home: Scaffold(
				appBar: AppBar(title: Text("Flutter"),),
				body: Center(
					child: CustomPaint(
						size: Size(300, 300),
					),
				),
			),
		);
	}
}

class MyPainter extends CustomPainter {
	@override
	void paint(Canvas canvas, Size size) {
	
	}
	
	@override
	bool shouldRepaint(CustomPainter oldDelegate) {
		return null;
	}
	
}