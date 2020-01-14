import 'dart:convert';
import 'dart:ui';

double getScreenWidth() {
  return window.physicalSize.width;
}

double getScreenHeight() {
  return window.physicalSize.height;
}

Map<String,dynamic> base64ToObject(String base64String) {
  base64String = base64String.replaceAll("\n", "");
  String decode = utf8.decode(base64.decode(base64String));
  Map<String,dynamic> data = json.decode(decode);
  return data;
}
