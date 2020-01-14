class DataCenter {
  DataCenter _instance;

  DataCenter() {}

  DataCenter getInstance(){
    if(_instance==null){
      _instance = DataCenter();
    }
    return _instance;
  }
}
