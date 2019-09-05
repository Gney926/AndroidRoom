package com.gney.androidroom.ui.info

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.gney.androidroom.base.BaseViewModel
import com.gney.androidroom.data.dao.PersonDao
import com.gney.androidroom.data.entity.Person
import com.gney.androidroom.util.event.SingleLiveEvent
import com.gney.androidroom.util.status.Status
import java.util.concurrent.Executors

class InfoViewModel(
    private val dao: PersonDao
) : BaseViewModel() {

    val items: LiveData<PagedList<Person>> = LivePagedListBuilder(dao.findAll(), 10).build()

    val observableEvent = SingleLiveEvent<Status>()

    fun delete(person: Person) = Executors.newSingleThreadExecutor().execute { dao.delete(person) }

    fun showEdit() {
        observableEvent.value = Status.EDIT_START
    }
}