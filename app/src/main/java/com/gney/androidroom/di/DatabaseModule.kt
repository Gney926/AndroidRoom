package com.gney.androidroom.di

import com.gney.androidroom.data.PersonDB
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        PersonDB.getInstance(androidApplication())
    }


    single(createdAtStart = false) {
        get<PersonDB>().personDao()
    }
}