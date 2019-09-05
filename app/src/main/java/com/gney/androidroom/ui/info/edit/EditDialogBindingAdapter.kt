package com.gney.androidroom.ui.info.edit

import android.text.TextUtils
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("setEditDialogError")
fun setEditDialogError(view: TextInputLayout, msg: String?) {
    if (TextUtils.isEmpty(msg)) {
        view.isErrorEnabled = true
        view.error = "수정할 값을 입력해주세요."
    } else {
        view.isErrorEnabled = false
    }
}