package com.example.mclib

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.mclib.Fragments.FirstFragment
import com.example.mclib.Fragments.SecondFragment
import com.example.mclib.Fragments.ThirdFragment

class PagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                FirstFragment()
            }
            1 -> SecondFragment()
            else -> {
                return ThirdFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position){
            0 -> "Overworld"
            1 -> "Nether"
            else -> "End"
        }
    }
}