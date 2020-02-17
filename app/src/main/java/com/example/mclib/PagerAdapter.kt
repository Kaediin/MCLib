package com.example.mclib

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.mclib.Fragments.EndFragment
import com.example.mclib.Fragments.NetherFragment
import com.example.mclib.Fragments.OverworldFragment

class PagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                OverworldFragment()
            }
            1 -> NetherFragment()
            else -> {
                return EndFragment()
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