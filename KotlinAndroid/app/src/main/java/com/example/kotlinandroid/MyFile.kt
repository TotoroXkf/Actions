package com.example.kotlinandroid

class Model


fun ArrayList<Model>.addNewModel(model: Model) {
    add(model)
    // .......
}

val ArrayList<Model>.firstModel: Model
    get() = first()