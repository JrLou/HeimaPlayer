package com.example.heimaplayer.ui.fragment

import android.graphics.Color
import android.view.Gravity
import android.view.TextureView
import android.view.View
import android.widget.TextView
import com.example.heimaplayer.base.BaseFragment

class YueDanFragment :BaseFragment(){
    override fun initView(): View? {
        val tv = TextView(context)
        tv.gravity = Gravity.CENTER
        tv.setTextColor(Color.RED)
        tv.text = javaClass.simpleName
        return  tv
    }
}