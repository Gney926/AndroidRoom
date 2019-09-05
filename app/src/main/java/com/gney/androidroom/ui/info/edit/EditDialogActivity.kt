package com.gney.androidroom.ui.info.edit

import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.Observer
import com.gney.androidroom.R
import com.gney.androidroom.base.BaseViewActivity
import com.gney.androidroom.databinding.ActivityEditDialogBinding
import com.gney.androidroom.util.status.Status
import org.koin.androidx.viewmodel.ext.android.getViewModel


class EditDialogActivity : BaseViewActivity<ActivityEditDialogBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_edit_dialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding.vm = getViewModel()
        viewDataBinding.lifecycleOwner = this

        observeEvent()
    }


    private fun observeEvent() {
        viewDataBinding.vm?.observableEvent?.observe(this, Observer {
            if (it == Status.EDIT_SUCCESS) {
                Toast.makeText(this, "수정되었습니다.", Toast.LENGTH_SHORT).show()
                finish()
            }
        })
    }


    override fun onResume() {
        super.onResume()
        window.setLayout(900, WindowManager.LayoutParams.WRAP_CONTENT)
    }
}