import 'package:flutter/material.dart';
import 'package:flutter_todo/base/Events.dart';
import 'package:flutter_todo/base/data_center.dart';
import 'package:flutter_todo/base/list_model.dart';

/// 清单页面主体
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
          onPressed: _onAdd,
          child: Icon(Icons.add),
        ),
      ),
    );
  }

  void _sortList() {}

  void _onAdd() {
    Task task = Task(
      name: "123",
      detail: "hahah",
      date: Date(year: 1, month: 2, day: 3),
      belong: "收集箱",
      isCompleted: false,
    );
    dataCenter.addTask(task);
  }
}

/// 清单主体
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

  Future<void> _refresh() async {
    await dataCenter.loadRemoteList();
    eventBus.fire(UpdateListUIEvent());
  }
}

/// Task的Item
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
    int selectIndex = await _showMoreMenuDialog();
    switch (selectIndex) {
      case 1:
        _onDelete();
    }
  }

  void _onDelete() {
    dataCenter.deleteTask(widget._task);
  }

  /// 展示loading弹窗
  void _showLoadingDialog() {
    showDialog(
      context: context,
      barrierDismissible: false, //点击遮罩不关闭对话框
      builder: (context) {
        return AlertDialog(
          content: Column(
            mainAxisSize: MainAxisSize.min,
            children: <Widget>[
              CircularProgressIndicator(),
              Padding(
                padding: const EdgeInsets.only(top: 26.0),
                child: Text("正在删除，请稍后..."),
              )
            ],
          ),
        );
      },
    );
  }

  /// 点击item的更多按钮，展示的弹窗
  Future<int> _showMoreMenuDialog() {
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

  /// 点击完成
  void _onChecked(bool value) {
    setState(() {
      widget._task.isCompleted = value;
      dataCenter.putRemoteList();
    });
  }
}
