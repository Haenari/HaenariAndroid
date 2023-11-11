package com.haenari.haenari.presentation.views.main

import androidx.activity.viewModels
import com.haenari.haenari.R
import com.haenari.haenari.databinding.ActivityMainBinding
import com.haenari.haenari.presentation.base.activity.MVIActivity
import com.haenari.haenari.presentation.views.sub.SubActivity

class MainActivity :
    MVIActivity<ActivityMainBinding, MainEvent, MainState>(R.layout.activity_main) {
    override val viewModel: MainViewModel by viewModels()

    override fun render(state: MainState) {
        when {
            state.isBtnClicked -> {
                startActivity(SubActivity::class.java, null)
            }
            else -> {

            }
        }
    }

    override fun initView() {
        bind {
            viewModel = this@MainActivity.viewModel
        }
    }
}