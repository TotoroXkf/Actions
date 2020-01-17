import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_todo/constants.dart';
import 'package:flutter_todo/base/data_center.dart';
import 'package:flutter_todo/base/utils.dart';

class SplashPage extends StatefulWidget {
  @override
  _SplashPageState createState() => _SplashPageState();
}

class _SplashPageState extends State<SplashPage>
    with SingleTickerProviderStateMixin {
  Animation<double> _animation;
  AnimationController _controller;

  @override
  void initState() {
    super.initState();

    SystemChrome.setEnabledSystemUIOverlays([]);

    _controller = new AnimationController(
      duration: Duration(seconds: 2),
      vsync: this,
    );
    _animation = new Tween(begin: 0.0, end: 1.0).animate(_controller);
    _controller.forward();
    _controller.addStatusListener(_onAnimationStatusChange);

    loadData();
  }

  void _onAnimationStatusChange(AnimationStatus newStatus) {
    if (newStatus == AnimationStatus.completed && !dataCenter.isLoading()) {
      goMainPage();
    }
  }

  void goMainPage() {
    Navigator.pushNamedAndRemoveUntil(
      context,
      MAIN_PAGE_ROUTE,
      (route) => route == null,
    );
  }

  Future loadData() async{
    await dataCenter.loadDetailedList();
    if(_controller.isCompleted && !dataCenter.isLoading()){
      goMainPage();
    }
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      constraints: BoxConstraints.expand(),
      color: Colors.white,
      child: Stack(
        children: <Widget>[
          Image.asset(
            'images/splash.jpg',
            fit: BoxFit.cover,
            height: getScreenHeight(),
          ),
          Align(
              alignment: Alignment.center,
              child: AlphaAnimatedWidget(
                animation: _animation,
              ))
        ],
      ),
    );
  }

  @override
  void dispose() {
    super.dispose();

    _controller.dispose();
  }
}

class AlphaAnimatedWidget extends AnimatedWidget {
  AlphaAnimatedWidget({Key key, Animation<double> animation})
      : super(key: key, listenable: animation);

  @override
  Widget build(BuildContext context) {
    final Animation<double> animation = listenable;
    return Opacity(
      opacity: animation.value,
      child: Column(
        children: <Widget>[
          FlutterLogo(
            size: 100,
          ),
          SizedBox(
            height: 30,
          ),
          Text(
            "Flutter Todo",
            style: TextStyle(
                color: Colors.white,
                fontSize: 20,
                decoration: TextDecoration.none),
          ),
        ],
        mainAxisAlignment: MainAxisAlignment.center,
      ),
    );
  }
}
