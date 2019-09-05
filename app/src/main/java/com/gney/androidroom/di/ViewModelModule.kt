package com.gney.androidroom.di

import com.gney.androidroom.ui.info.InfoViewModel
import com.gney.androidroom.ui.info.edit.EditDialogViewModel
import com.gney.androidroom.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }

    viewModel {
        InfoViewModel(get())
    }

    viewModel {
        EditDialogViewModel(get())
    }
}