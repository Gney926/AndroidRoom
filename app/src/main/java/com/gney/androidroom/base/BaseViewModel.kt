package com.gney.androidroom.base

import androidx.lifecycle.ViewModel
import com.gney.androidroom.data.PersonDB.Companion.destroyInstance
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {


    private val compositeDisposable = CompositeDisposable()


    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }


    override fun onCleared() {
        compositeDisposable.clear()
        destroyInstance()
        super.onCleared()
    }
}