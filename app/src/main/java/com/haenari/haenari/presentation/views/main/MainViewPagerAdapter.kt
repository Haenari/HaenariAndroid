package com.haenari.haenari.presentation.views.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    companion object{
        const val INDEX_HOME = 0
        const val INDEX_WEATHER = 1
        const val INDEX_WEARING = 2
        const val INDEX_SETTINGS = 3
        const val TAB_COUNT = 4
    }
    override fun getItemCount(): Int = TAB_COUNT

    override fun createFragment(position: Int): Fragment {
//        return when(position){
//            INDEX_HOME -> {
//                HomeFragment.newInstance()
//            }
//            INDEX_WEATHER_DETAILS -> {
//                WeatherDetailsFragment.newInstance()
//            }
//            INDEX_SETTINGS -> {
//                SettingsFragment.newInstance()
//            }
//            else -> {
//                Fragment()
//            }
//        }
        return Fragment()
    }
}