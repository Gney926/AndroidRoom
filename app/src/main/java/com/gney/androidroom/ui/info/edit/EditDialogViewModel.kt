package com.gney.androidroom.ui.info.edit

import androidx.databinding.ObservableField
import com.gney.androidroom.base.BaseViewModel
import com.gney.androidroom.data.dao.PersonDao
import com.gney.androidroom.util.event.SingleLiveEvent
import com.gney.androidroom.util.status.Status
import com.orhanobut.logger.Logger
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class EditDialogViewModel(
    private val dao: PersonDao
) : BaseViewModel() {

    var name = ObservableField<String>()
    var job = ObservableField<String>()

    val observableEvent = SingleLiveEvent<Status>()


    fun editInfo() {
        addDisposable(
            Observable.just(name, job)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    dao.updateInfo(name = name.get()!!, job = job.get()!!)
                    observableEvent.postValue(Status.EDIT_SUCCESS)
                }, {
                    Logger.e("실패: ${it.message}")
                })
        )
    }
}