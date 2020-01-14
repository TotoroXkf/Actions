class ListModel {
  List<Lists> lists;
  int listNum;

  ListModel({this.lists, this.listNum});

  ListModel.fromJson(Map<String, dynamic> json) {
    if (json['lists'] != null) {
      lists = new List<Lists>();
      json['lists'].forEach((v) {
        lists.add(new Lists.fromJson(v));
      });
    }
    listNum = json['listNum'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    if (this.lists != null) {
      data['lists'] = this.lists.map((v) => v.toJson()).toList();
    }
    data['listNum'] = this.listNum;
    return data;
  }
}

class Lists {
  String name;
  bool isDefault;
  int taskNum;
  List<Tasks> tasks;

  Lists({this.name, this.isDefault, this.taskNum, this.tasks});

  Lists.fromJson(Map<String, dynamic> json) {
    name = json['name'];
    isDefault = json['isDefault'];
    taskNum = json['taskNum'];
    if (json['tasks'] != null) {
      tasks = new List<Tasks>();
      json['tasks'].forEach((v) {
        tasks.add(new Tasks.fromJson(v));
      });
    }
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['name'] = this.name;
    data['isDefault'] = this.isDefault;
    data['taskNum'] = this.taskNum;
    if (this.tasks != null) {
      data['tasks'] = this.tasks.map((v) => v.toJson()).toList();
    }
    return data;
  }
}

class Tasks {
  String name;
  String detail;
  int createTime;
  Date date;
  Time time;

  Tasks({this.name, this.detail, this.createTime, this.date, this.time});

  Tasks.fromJson(Map<String, dynamic> json) {
    name = json['name'];
    detail = json['detail'];
    createTime = json['createTime'];
    date = json['date'] != null ? new Date.fromJson(json['date']) : null;
    time = json['time'] != null ? new Time.fromJson(json['time']) : null;
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
    return data;
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
