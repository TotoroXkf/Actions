import 'package:flutter/material.dart';
import 'package:flutter_todo/base/list_model.dart';

class TodoListWidget extends StatefulWidget {
  final ListModel _listModel;

  TodoListWidget(this._listModel, {Key key}) : super(key: key);

  @override
  _TodoListWidgetState createState() => _TodoListWidgetState();
}

class _TodoListWidgetState extends State<TodoListWidget>
    with TickerProviderStateMixin {
  Widget _getTabContent() {
    List<Widget> contents = [];
    for (int i = 0; i < widget._listModel.getListNum(); i++) {
      TaskTable taskTable = widget._listModel.lists[i];
      contents.add(TodoList(taskTable));
    }
    return TabBarView(children: contents);
  }

  Widget _getTabBar() {
    List<String> names = widget._listModel.getListName();
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
      length: widget._listModel.getListNum(),
      child: Scaffold(
        appBar: AppBar(
          title: Text('Flutter Todo'),
          bottom: _getTabBar(),
        ),
        body: _getTabContent(),
        floatingActionButton: FloatingActionButton(
          onPressed: () {},
          child: Icon(Icons.add),
        ),
      ),
    );
  }
}

class TodoList extends StatefulWidget {
  final TaskTable _taskTable;

  TodoList(
    this._taskTable, {
    Key key,
  }) : super(key: key);

  @override
  _TodoListState createState() => _TodoListState();
}

class _TodoListState extends State<TodoList> {
  @override
  Widget build(BuildContext context) {
    return ListView.separated(
      itemCount: widget._taskTable.getTaskNum(),
      itemBuilder: (BuildContext context, int index) {
        return TodoListItem(widget._taskTable.tasks[index]);
      },
      separatorBuilder: (BuildContext context, int index) {
        return new Divider(
          color: Colors.black26,
          height: 1,
        );
      },
    );
  }
}

class TodoListItem extends StatefulWidget {
  final Task _task;

  TodoListItem(this._task, {Key key}) : super(key: key);

  @override
  _TodoListItemState createState() => _TodoListItemState();
}

class _TodoListItemState extends State<TodoListItem> {
  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.white,
      height: 56,
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.center,
        children: <Widget>[
          SizedBox(
            width: 12,
          ),
          Checkbox(
            onChanged: (bool value) {
              onChecked(value);
            },
            value: widget._task.isFinished(),
          ),
          SizedBox(
            width: 12,
          ),
          Text(
            widget._task.name,
            style: TextStyle(
              color: widget._task.isCompleted ? Colors.black26 : Colors.black,
              fontSize: 16,
              decoration: widget._task.isFinished()
                  ? TextDecoration.lineThrough
                  : TextDecoration.none,
            ),
          ),
        ],
      ),
    );
  }

  void onChecked(bool value) {
    setState(() {
      widget._task.isCompleted = value;
    });
  }
}
