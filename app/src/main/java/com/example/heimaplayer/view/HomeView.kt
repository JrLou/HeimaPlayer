package com.example.heimaplayer.view

import com.example.heimaplayer.HomeItemBean

/**
 * home界面和presenter交互
 */
interface HomeView{
     fun onError(message: String?)
     fun loadMore(list: List<HomeItemBean>?)
     fun loadSuccess(list: List<HomeItemBean>?)

}