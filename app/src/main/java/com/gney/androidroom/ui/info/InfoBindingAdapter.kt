package com.gney.androidroom.ui.info

import android.annotation.SuppressLint
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.gney.androidroom.data.entity.Person

@BindingAdapter(value = ["infos", "viewModel"])
fun setInfos(view: RecyclerView, items: PagedList<Person>?, vm: InfoViewModel) {
    view.adapter?.run {
        if (this is InfoAdapter) this.submitList(items)
    } ?: run {
        InfoAdapter(vm).apply {
            view.adapter = this
            this.submitList(items)
        }
    }
}


@SuppressLint("SetTextI18n")
@BindingAdapter("loadName")
fun loadName(textView: AppCompatTextView, name: String) {
    textView.text = "이름: $name"
}


@SuppressLint("SetTextI18n")
@BindingAdapter("loadJob")
fun loadJob(textView: AppCompatTextView, job: String) {
    textView.text = "직업: $job"
}