package com.example.movientf.ui.presentation.views.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movientf.domain.model.ConstantGeneral.Companion.ONE
import com.example.movientf.domain.model.ConstantGeneral.Companion.THREE
import com.example.movientf.domain.model.ConstantGeneral.Companion.TWO
import com.example.movientf.domain.model.ConstantGeneral.Companion.VIEW_PAGER_NUM
import com.example.movientf.ui.presentation.views.VPFourFragment
import com.example.movientf.ui.presentation.views.VPOneFragment
import com.example.movientf.ui.presentation.views.VPThreeFragment
import com.example.movientf.ui.presentation.views.VPTwoFragment

class ViewPagerAdapter (fa : FragmentActivity) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int  = VIEW_PAGER_NUM

    override fun createFragment(position: Int): Fragment {

        return when(position){
            ONE -> { VPTwoFragment() }
            TWO -> { VPThreeFragment() }
            THREE -> { VPFourFragment() }
            else -> VPOneFragment()
        }

    }
}