import 'dart:collection';
import 'dart:math';

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
          title: Text('Flutter'),
        ),
        body: MyHomePage(),
      ),
    );
  }
}

class InheritedProvider<T> extends InheritedWidget {
  const InheritedProvider({
    Key key,
    this.data,
    @required Widget child,
  })  : assert(child != null),
        super(key: key, child: child);

  final T data;

  @override
  bool updateShouldNotify(InheritedProvider<T> old) {
    return true;
  }
}

Type _typeOf<T>() => T;

class ChangeNotifierProvider<T extends ChangeNotifier> extends StatefulWidget {
  ChangeNotifierProvider({
    Key key,
    this.child,
    this.data,
  }) : super(key: key);

  final Widget child;
  final T data;

  static T of<T>(BuildContext context) {
    final type = _typeOf<InheritedProvider<T>>();
    final provider =
        context.inheritFromWidgetOfExactType(type) as InheritedProvider<T>;
    return provider.data;
  }

  @override
  State<StatefulWidget> createState() {
    return _ChangeNotifierProviderState();
  }
}

class _ChangeNotifierProviderState<T extends ChangeNotifier>
    extends State<ChangeNotifierProvider<T>> {
  void update() {
    setState(() {});
  }

  @override
  void initState() {
    super.initState();
    widget.data.addListener(update);
  }

  @override
  Widget build(BuildContext context) {
    return InheritedProvider<T>(
      data: widget.data,
      child: widget.child,
    );
  }

  @override
  void dispose() {
    super.dispose();
    widget.data.removeListener(update);
  }
}

class Item {
  Item({this.price, this.count});

  double price; //商品单价
  int count; // 商品份数
}

class CartModel extends ChangeNotifier {
  // 用于保存购物车中商品列表
  final List<Item> _items = [];

  // 禁止改变购物车里的商品信息
  UnmodifiableListView<Item> get items => UnmodifiableListView(_items);

  // 购物车中商品的总价
  double get totalPrice =>
      _items.fold(0, (value, item) => value + item.count * item.price);

  // 将 [item] 添加到购物车。这是唯一一种能从外部改变购物车的方法。
  void add(Item item) {
    _items.add(item);
    // 通知监听器（订阅者），重新构建InheritedProvider， 更新状态。
    notifyListeners();
  }
}

class MyHomePage extends StatefulWidget {
  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  @override
  Widget build(BuildContext context) {
    return Center(
      child: ChangeNotifierProvider<CartModel>(
        data: CartModel(),
        child: Builder(
          builder: (context) {
            return Column(
              children: <Widget>[
                Builder(builder: (context) {
                  var cart = ChangeNotifierProvider.of<CartModel>(context);
                  return Text('${cart.totalPrice}');
                }),
              ],
            );
          },
        ),
      ),
    );
  }
}
