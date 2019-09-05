package com.gney.androidroom.ui.main

import android.text.TextUtils
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout


@BindingAdapter("setSaveError")
fun setSaveError(view: TextInputLayout, msg: String?) {
    if (TextUtils.isEmpty(msg)) {
        view.isErrorEnabled = true
        view.error = "유효하지 않은 값입니다. 값을 입력해주세요."
    } else {
        view.isErrorEnabled = false
    }
}