package com.gney.androidroom.base

import android.app.Application
import com.gney.androidroom.BuildConfig
import com.gney.androidroom.di.databaseModule
import com.gney.androidroom.di.viewModelModule
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .methodCount(0)
            .methodOffset(7)
            .build()

        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })

        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)

            modules(listOf(viewModelModule, databaseModule))
        }
    }
}