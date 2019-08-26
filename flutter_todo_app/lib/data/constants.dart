import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

const String TYPE_TODAY = "今天";
const String TYPE_TOMORROW = "明天";
const String TYPE_AFTER_WEEK = "未来7天";
const String TYPE_THIS_MONTH = "本月";
const String TYPE_ALL = "全部";

const List<String> drawerMenuNames = [
  TYPE_TODAY,
  TYPE_TOMORROW,
  TYPE_AFTER_WEEK,
  TYPE_THIS_MONTH,
  TYPE_ALL,
];

const List<IconData> drawerMenuIcons = [
  Icons.calendar_today,
  Icons.today,
  Icons.next_week,
  Icons.calendar_view_day,
  Icons.done_all,
];

const String ITEM_SORT = "按照时间排序";
const String ITEM_REFRESH = "刷新";

const List<String> mainPopMenuNames = [
  ITEM_SORT,
  ITEM_REFRESH,
];
