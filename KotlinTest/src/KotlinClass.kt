fun ArrayList<Model>.addNewModel(model: Model) {
    add(model)
    // .......
}

fun main() {
    val list = ArrayList<Model>()
    list.addNewModel(Model())
}