package com.gney.androidroom.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import com.gney.androidroom.R
import com.gney.androidroom.base.BaseViewActivity
import com.gney.androidroom.databinding.ActivityMainBinding
import com.gney.androidroom.ui.info.InfoActivity
import com.gney.androidroom.util.status.Status
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : BaseViewActivity<ActivityMainBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_main


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding.vm = getViewModel()
        viewDataBinding.lifecycleOwner = this

        observeEvent()
    }


    private fun observeEvent() {
        viewDataBinding.vm?.observableEvent?.observe(this, Observer {
            if (it == Status.SAVE) {
                Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_SHORT).show()
            } else if (it == Status.OVERLAP) {
                Toast.makeText(this, "이미 존재하는 정보입니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            R.id.listInfo -> {
                startActivity(Intent(this, InfoActivity::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}