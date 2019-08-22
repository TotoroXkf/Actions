class Task {
  bool checked;
  String name;
  DateTime createDate;

  Task({
    this.checked = false,
    this.name = "",
    this.createDate,
  }) {
    if (createDate == null) {
      createDate = DateTime.now();
    }
  }
}
