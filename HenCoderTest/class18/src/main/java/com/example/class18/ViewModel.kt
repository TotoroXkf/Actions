package com.example.class18

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers


class ViewModel {
    private lateinit var disposable: Disposable
    
    var textObserver: Consumer<String>? = null
    var ageObserver: Observer<Int> = object : Observer<Int> {
        override fun onComplete() {
        
        }
        
        override fun onSubscribe(d: Disposable) {
            disposable = d
        }
        
        override fun onNext(t: Int) {
        
        }
        
        override fun onError(e: Throwable) {
        
        }
        
    }
    
    fun getInfo() {
        Observable.just("1")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(textObserver)
        
        disposable.dispose()
    }
}