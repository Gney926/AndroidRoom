package com.gney.androidroom.ui.main

import androidx.databinding.ObservableField
import com.gney.androidroom.base.BaseViewModel
import com.gney.androidroom.data.dao.PersonDao
import com.gney.androidroom.data.entity.Person
import com.gney.androidroom.util.event.SingleLiveEvent
import com.gney.androidroom.util.status.Status
import com.orhanobut.logger.Logger
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.*


class MainViewModel(
    private val personDao: PersonDao
) : BaseViewModel() {


    var name = ObservableField<String>()
    var job = ObservableField<String>()
    var socialNumber = ObservableField<String>()


    val observableEvent = SingleLiveEvent<Status>()


    fun saveInfo() {
        val person = Person(socialNumber.get()!!, name.get()!!, job.get()!!, Date())

        addDisposable(
            Observable.just(person)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    if (!personDao.isPerson(socialNumber.get()!!)) {
                        personDao.insert(person)
                        observableEvent.postValue(Status.SAVE)
                    } else {
                        observableEvent.postValue(Status.OVERLAP)
                    }
                }, {
                    Logger.e("실패: ${it.message}")
                })
        )
    }
}