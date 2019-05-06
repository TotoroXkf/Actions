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
      home: ListPage(),
    );
  }
}

class Product {
  final String title;
  final String description;

  Product(this.title, this.description);
}

class ListPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    List<Product> products = List.generate(20, (i) => Product('$i', '这是一个商品'));
    return Scaffold(
      appBar: AppBar(
        title: Text('列表页'),
      ),
      body: ListView.builder(
        itemCount: products.length,
        itemBuilder: (context, index) {
          return ListTile(
            title: Text('商品$index'),
            onTap: () {
              Navigator.push(context, MaterialPageRoute(builder: (context) {
                return ListDetail(
                  product: products[index],
                );
              }));
            },
          );
        },
      ),
    );
  }
}

class ListDetail extends StatelessWidget {
  final Product product;

  ListDetail({Key key, @required this.product}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('商品${product.title}'),
      ),
      body: Center(
        child: Text('${product.description}'),
      ),
    );
  }
}
