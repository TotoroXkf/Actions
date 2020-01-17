import 'package:event_bus/event_bus.dart';
import 'package:flutter/material.dart';
import 'package:flutter_todo/base/Events.dart';
import 'package:flutter_todo/base/data_center.dart';
import 'package:flutter_todo/base/list_model.dart';
import 'package:flutter_todo/constants.dart';

// 清单页面主体
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
          actions: <Widget>[
            IconButton(
              icon: Icon(Icons.sort),
              onPressed: _sortList,
            ),
          ],
        ),
        body: _getTabContent(),
        floatingActionButton: FloatingActionButton(
          onPressed: () {},
          child: Icon(Icons.add),
        ),
      ),
    );
  }

  void _sortList() {}
}

// 清单主体
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
    return RefreshIndicator(
      onRefresh: _refresh,
      child: ListView.separated(
        itemCount: widget._taskTable.getTaskNum(),
        itemBuilder: (BuildContext context, int index) {
          Task task = widget._taskTable.tasks[index];
          return TodoListItem(task);
        },
        separatorBuilder: (BuildContext context, int index) {
          return Divider(
            color: Colors.blue,
            height: 1,
          );
        },
      ),
    );
  }

  Future<void> _refresh() async {}
}

// Task的Item
class TodoListItem extends StatefulWidget {
  final Task _task;

  TodoListItem(this._task, {Key key}) : super(key: key);

  @override
  _TodoListItemState createState() => _TodoListItemState();
}

class _TodoListItemState extends State<TodoListItem> {
  List<String> moreMenuName = [
    "移动到",
    "删除",
  ];

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.white,
      height: 60,
      child: Flex(
        direction: Axis.horizontal,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: <Widget>[
          SizedBox(
            width: 12,
          ),
          Checkbox(
            onChanged: (bool value) {
              _onChecked(value);
            },
            value: widget._task.isFinished(),
          ),
          SizedBox(
            width: 12,
          ),
          Expanded(
            flex: 1,
            child: Text(
              widget._task.name,
              style: TextStyle(
                color: widget._task.isCompleted ? Colors.black26 : Colors.black,
                fontSize: 16,
                decoration: widget._task.isFinished()
                    ? TextDecoration.lineThrough
                    : TextDecoration.none,
              ),
            ),
          ),
          IconButton(
            icon: Icon(Icons.more_vert),
            onPressed: _onClickMore,
          ),
          SizedBox(
            width: 12,
          )
        ],
      ),
    );
  }

  void _onClickMore() async {
    int selectIndex = await _showDialog();
    switch (selectIndex) {
      case 1:
        _onDelete();
    }
  }

  void _onDelete() async{
    await dataCenter.deleteTask(widget._task);

  }

  Future<int> _showDialog() {
    return showDialog<int>(
      context: context,
      builder: (BuildContext context) {
        List<SimpleDialogOption> dialogItems = [];
        for (int i = 0; i < moreMenuName.length; i++) {
          dialogItems.add(
            SimpleDialogOption(
              child: Padding(
                padding: EdgeInsets.symmetric(vertical: 6),
                child: Text(moreMenuName[i]),
              ),
              onPressed: () {
                Navigator.pop(context, i);
              },
            ),
          );
        }
        return SimpleDialog(
          title: Text(widget._task.name),
          children: dialogItems,
        );
      },
    );
  }

  void _onChecked(bool value) {
    setState(() {
      widget._task.isCompleted = value;
    });
  }
}
