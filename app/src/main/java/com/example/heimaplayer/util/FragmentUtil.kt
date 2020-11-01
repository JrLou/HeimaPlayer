package com.example.heimaplayer.util

import com.example.heimaplayer.R
import com.example.heimaplayer.base.BaseFragment
import com.example.heimaplayer.ui.fragment.HomeFragment
import com.example.heimaplayer.ui.fragment.MvFragment
import com.example.heimaplayer.ui.fragment.VBangFragment
import com.example.heimaplayer.ui.fragment.YueDanFragment

class FragmentUtil private constructor(){//私有化构造方法
    val homeFragment by lazy { HomeFragment()}
    val mvFragment by lazy { MvFragment()}
    val vBangFragment by lazy { VBangFragment()}
    val yueDanFragment by lazy { YueDanFragment()}

    companion object {
        val fragmentUtil by lazy { FragmentUtil()}
    }

    /**
     * 根据tabid获取对应的fragment
     */
    fun getFragment(tabId:Int):BaseFragment?{
        when(tabId){
            R.id.tab_home->return homeFragment
            R.id.tab_mv->return mvFragment
            R.id.tab_vbang->return vBangFragment
            R.id.tab_yuedan->return yueDanFragment
        }

        return null
    }
}