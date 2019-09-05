package com.gney.androidroom.ui.info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.gney.androidroom.R
import com.gney.androidroom.data.entity.Person

class InfoAdapter (
    val vm: InfoViewModel
) : PagedListAdapter<Person, InfoViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Person>() {
            override fun areItemsTheSame(oldItem: Person, newItem: Person) =
                oldItem.socialNumber == newItem.socialNumber

            override fun areContentsTheSame(oldItem: Person, newItem: Person) =
                oldItem == newItem
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        return InfoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_info,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        getItem(position)?.run {
            holder.viewDataBinding.item = this
            holder.viewDataBinding.vm = vm
        }
    }
}