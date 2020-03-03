void main() {
  // 这行会在执行到await的时候跳出来,执行这个函数后面的代码，等到awiat执行完毕在自动跳回到函数里面去
  longTimeFunc();
  // 上面函数执行到await的时候,会跳出来执行这个代码
  print(1);
}

longTimeFunc() async {
  // 本质上，是将await执行的future先给返回了
  // 所以async函数要求返回Future
  String value = await http();
  // 这里会等到上面的 awiat 完全执行完才会继续走下去
  // 相当于是把异步变成了同步的写法
  return value;
}

Future<String> http() {}
