package com.example.kotlinweather.data.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kotlinweather.ui.view.fragments.DaysFragment
import com.example.kotlinweather.ui.view.fragments.HoursFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity, private val list: List<Fragment>): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return list[position]
    }
}