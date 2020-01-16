import 'package:flutter/material.dart';
import 'package:flutter_todo/base/list_model.dart';

class TodoListWidget extends StatefulWidget {
  final ListModel listModel;

  TodoListWidget(this.listModel, {Key key}) : super(key: key);

  @override
  _TodoListWidgetState createState() => _TodoListWidgetState();
}

class _TodoListWidgetState extends State<TodoListWidget>
    with TickerProviderStateMixin {
  Widget _getTabContent() {
    List<Widget> contents = [];
    for (int i = 0; i < widget.listModel.getListNum(); i++) {
      TaskTable taskTable = widget.listModel.lists[i];
      contents.add(TodoList(taskTable: taskTable));
    }
    return TabBarView(children: contents);
  }

  Widget _getTabBar() {
    List<String> names = widget.listModel.getListName();
    List<Tab> tabs = [];
    for (int i = 0; i < names.length; i++) {
      String name = names[i];
      tabs.add(Tab(text: name));
    }
    return TabBar(
      isScrollable: true,
      tabs: tabs,
    );
  }

  @override
  Widget build(BuildContext context) {
    return DefaultTabController(
      length: widget.listModel.getListNum(),
      child: Scaffold(
          appBar: AppBar(
            title: Text('Flutter Todo'),
            bottom: _getTabBar(),
          ),
          body: _getTabContent()),
    );
  }
}

class TodoList extends StatefulWidget {
  final TaskTable taskTable;

  TodoList({
    Key key,
    this.taskTable,
  }) : super(key: key);

  @override
  _TodoListState createState() => _TodoListState();
}

class _TodoListState extends State<TodoList> {
  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      itemCount: widget.taskTable.getTaskNum(),
      itemBuilder: (BuildContext context, int index) {

      },
    );
  }
}

class TodoListItem extends StatefulWidget {
  @override
  _TodoListItemState createState() => _TodoListItemState();
}

class _TodoListItemState extends State<TodoListItem> {
  @override
  Widget build(BuildContext context) {
    return Container();
  }
}

