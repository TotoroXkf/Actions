package com.xkf.databingdingtest

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class User : BaseObservable() {
    @get:Bindable
    var firstName: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR._all)
        }

    @get:Bindable
    var lastName: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR._all)
        }
}