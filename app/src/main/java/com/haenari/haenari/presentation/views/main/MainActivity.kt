package com.haenari.haenari.presentation.views.main

import androidx.activity.viewModels
import com.haenari.haenari.R
import com.haenari.haenari.databinding.ActivityMainBinding
import com.haenari.haenari.presentation.base.activity.MVIActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :
    MVIActivity<ActivityMainBinding, MainEvent, MainState>(R.layout.activity_main) {
    override val viewModel: MainViewModel by viewModels()

    private val mainViewPagerAdapter = MainViewPagerAdapter(this)

    override fun render(state: MainState) {
        when {
            state.isBtnClicked -> {
                // todo need to remove
                viewModel.test()
            }

            else -> {

            }
        }
    }

    override fun initView() {
        bind {
            viewModel = this@MainActivity.viewModel

            vpMain.run {
                adapter = mainViewPagerAdapter
                isUserInputEnabled = false
            }

            bnvMain.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.btnHome -> {
                        vpMain.currentItem = MainViewPagerAdapter.INDEX_HOME
                    }

                    R.id.btnWeather -> {
                        vpMain.currentItem = MainViewPagerAdapter.INDEX_WEATHER
                    }

                    R.id.btnWearing -> {
                        vpMain.currentItem = MainViewPagerAdapter.INDEX_WEARING
                    }

                    R.id.btnSettings -> {
                        vpMain.currentItem = MainViewPagerAdapter.INDEX_SETTINGS
                    }
                }
                true
            }
        }
    }
}