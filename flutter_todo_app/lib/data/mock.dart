import 'package:flutter_todo_app/data/data_source.dart';
import 'package:flutter_todo_app/model/task.dart';

List<Task> fillTodayList() {
  for (int i = 0; i < 20; i++) {
    Task task = new Task(name: "第 $i 个任务", checked: false);
    DataSource.addTask(task);
  }
  return DataSource.getTodayTasks();
}
