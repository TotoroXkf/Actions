class ListModel {
  List<TaskTable> lists;

  ListModel({this.lists});

  ListModel.fromJson(Map<String, dynamic> json) {
    if (json['lists'] != null) {
      lists = new List<TaskTable>();
      json['lists'].forEach((v) {
        lists.add(new TaskTable.fromJson(v));
      });
    }
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    if (this.lists != null) {
      data['lists'] = this.lists.map((v) => v.toJson()).toList();
    }
    return data;
  }

  int getListNum(){
    return lists.length;
  }

  List<String> getListName() {
    List<String> result = [];
    for (int i = 0; i < lists.length; i++) {
      result.add(lists[i].name);
    }
    return result;
  }
}

class TaskTable {
  String name;
  bool isDefault;
  List<Task> tasks;
  int taskNum;

  TaskTable({this.name, this.isDefault, this.tasks, this.taskNum});

  TaskTable.fromJson(Map<String, dynamic> json) {
    name = json['name'];
    isDefault = json['isDefault'];
    if (json['tasks'] != null) {
      tasks = new List<Task>();
      json['tasks'].forEach((v) {
        tasks.add(new Task.fromJson(v));
      });
    }
    taskNum = json['taskNum'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['name'] = this.name;
    data['isDefault'] = this.isDefault;
    if (this.tasks != null) {
      data['tasks'] = this.tasks.map((v) => v.toJson()).toList();
    }
    data['taskNum'] = this.taskNum;
    return data;
  }

  int getTaskNum(){
    return tasks.length;
  }
}

class Task {
  String name;
  String detail;
  int createTime;
  Date date;
  Time time;
  bool isCompleted;
  List<SubTask> subTasks;

  Task(
      {this.name,
        this.detail,
        this.createTime,
        this.date,
        this.time,
        this.isCompleted,
        this.subTasks});

  Task.fromJson(Map<String, dynamic> json) {
    name = json['name'];
    detail = json['detail'];
    createTime = json['createTime'];
    date = json['date'] != null ? new Date.fromJson(json['date']) : null;
    time = json['time'] != null ? new Time.fromJson(json['time']) : null;
    isCompleted = json['isCompleted'];
    if (json['subTasks'] != null) {
      subTasks = new List<SubTask>();
      json['subTasks'].forEach((v) {
        subTasks.add(new SubTask.fromJson(v));
      });
    }
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['name'] = this.name;
    data['detail'] = this.detail;
    data['createTime'] = this.createTime;
    if (this.date != null) {
      data['date'] = this.date.toJson();
    }
    if (this.time != null) {
      data['time'] = this.time.toJson();
    }
    data['isCompleted'] = this.isCompleted;
    if (this.subTasks != null) {
      data['subTasks'] = this.subTasks.map((v) => v.toJson()).toList();
    }
    return data;
  }

  bool isFinished(){
    return isCompleted;
  }
}

class Date {
  int year;
  int month;
  int day;

  Date({this.year, this.month, this.day});

  Date.fromJson(Map<String, dynamic> json) {
    year = json['year'];
    month = json['month'];
    day = json['day'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['year'] = this.year;
    data['month'] = this.month;
    data['day'] = this.day;
    return data;
  }
}

class Time {
  int begin;
  int end;

  Time({this.begin, this.end});

  Time.fromJson(Map<String, dynamic> json) {
    begin = json['begin'];
    end = json['end'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['begin'] = this.begin;
    data['end'] = this.end;
    return data;
  }
}

class SubTask {
  String name;
  bool isCompleted;

  SubTask({this.name, this.isCompleted});

  SubTask.fromJson(Map<String, dynamic> json) {
    name = json['name'];
    isCompleted = json['isCompleted'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['name'] = this.name;
    data['isCompleted'] = this.isCompleted;
    return data;
  }
}
