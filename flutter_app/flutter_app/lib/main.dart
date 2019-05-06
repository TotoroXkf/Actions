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
				body: FirstPage(),
			),
		);
	}
}

class FirstPage extends StatelessWidget {
	jump(BuildContext context) async {
		var result =
				await Navigator.push(context, MaterialPageRoute(builder: (context) {
			return SecondPage();
		}));
		final snackBar = new SnackBar(content: new Text(result));
		Scaffold.of(context).showSnackBar(snackBar);
	}

	@override
	Widget build(BuildContext context) {
		return Center(
			child: RaisedButton(
				child: Text('点击跳转到下一个页面'),
				onPressed: () {
					jump(context);
				},
			),
		);
	}
}

class SecondPage extends StatelessWidget {
	@override
	Widget build(BuildContext context) {
		return Scaffold(
			appBar: AppBar(
				title: Text('Flutter'),
			),
			body: Center(
				child: Column(
					mainAxisAlignment: MainAxisAlignment.center,
					children: <Widget>[
						Padding(
							padding: EdgeInsets.all(8.0),
							child: RaisedButton(
								child: Text('Xkf'),
								onPressed: () {
									Navigator.pop(context, 'Xkf');
								},
							),
						),
						Padding(
							padding: EdgeInsets.all(8.0),
							child: RaisedButton(
								child: Text('Zs'),
								onPressed: () {
									Navigator.pop(context, 'Zs');
								},
							),
						),
					],
				),
			),
		);
	}
}
