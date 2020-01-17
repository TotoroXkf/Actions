import 'package:event_bus/event_bus.dart';
import 'package:flutter_todo/base/list_model.dart';

EventBus eventBus = EventBus();

// 删除事件
class DeleteTaskEvent {
  Task task;

  DeleteTaskEvent(this.task);
}

class FinishDeleteTaskEvent {}

class UpdateListUIEvent {}
