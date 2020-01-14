class DataCenter {
  static DataCenter _instance;

  static DataCenter getInstance(){
    if(_instance==null){
      _instance = DataCenter();
    }
    return _instance;
  }

  int getListNum(){
    return 0;
  }

  List<String> getListName(){

  }
}
