package com.gney.androidroom.ui.info

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.gney.androidroom.R
import com.gney.androidroom.base.BaseViewActivity
import com.gney.androidroom.databinding.ActivityInfoBinding
import com.gney.androidroom.ui.info.edit.EditDialogActivity
import com.gney.androidroom.util.status.Status
import org.koin.androidx.viewmodel.ext.android.getViewModel

class InfoActivity : BaseViewActivity<ActivityInfoBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_info


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding.vm = getViewModel()
        viewDataBinding.lifecycleOwner = this

        observeEvent()
    }

    private fun observeEvent() {
        viewDataBinding.vm?.observableEvent?.observe(this, Observer {
            if (it == Status.EDIT_START) {
                startActivity(Intent(this, EditDialogActivity::class.java))
            }
        })
    }
}
