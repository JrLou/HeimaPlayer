package com.example.heimaplayer.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.heimaplayer.HomeItemBean
import com.example.heimaplayer.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_home.view.*

class HomeItemView:RelativeLayout {
    constructor(context: Context?):super(context)
    constructor(context: Context?, attrs: AttributeSet?):super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    /***
     * 初始化方法
     */
    init {
        View.inflate(context, R.layout.item_home,this)
    }

    fun setData(data: HomeItemBean){
        //歌曲名称
        title.setText(data.title)
        //简介
        desc.setText(data.description)
        //图片
        Picasso.with(context).load(data.posterPic).into(bg)
    }
}