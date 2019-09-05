package com.gney.androidroom.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseViewActivity<T : ViewDataBinding> : AppCompatActivity() {

    abstract val layoutResourceId: Int

    lateinit var viewDataBinding: T
        private set


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, layoutResourceId)
    }
}