package com.example.heimaplayer.presenter.impl

import com.example.heimaplayer.HomeItemBean
import com.example.heimaplayer.presenter.interf.HomePresenter
import com.example.heimaplayer.util.ThreadUtil
import com.example.heimaplayer.util.URLProviderUtils
import com.example.heimaplayer.view.HomeView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.*
import java.io.IOException

class HomePresenterImpl(var homeView: HomeView): HomePresenter{
    /**
     * 初始化数据或刷新数据
     */
    override fun loadDatas() {
        val path = URLProviderUtils.getHomeUrl(0, 20)
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(path)
            .get()
            .build()
        client.newCall(request).enqueue(object : Callback {
            /**
             * 子线程调用
             */
            override fun onFailure(call: Call, e: IOException?) {
                ThreadUtil.runOnMainThread(object : Runnable {
                    override fun run() {
                        //回调到view层处理
//                        refreshLayout.isRefreshing = false
                        homeView.onError(e?.message)
                    }
                })
                println("获取数据失败：" + Thread.currentThread().name)
                println("获取数据失败：" + e?.stackTrace)
                println("获取数据失败：" + e?.message)
            }

            override fun onResponse(call: Call, response: Response) {
                val result = response?.body()?.string()
                val gson = Gson()
                val list = gson.fromJson<List<HomeItemBean>>(result, object :
                    TypeToken<List<HomeItemBean>>() {}.type)
                println("获取数据成功：" + list)
                //刷新列表
                ThreadUtil.runOnMainThread(object : Runnable {
                    override fun run() {
                        homeView.loadSuccess(list)
                    }
                })
            }

        })
    }

    override fun loadMore(offset: Int) {
        val path = URLProviderUtils.getHomeUrl(offset, 20)
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(path)
            .get()
            .build()
        client.newCall(request).enqueue(object : Callback {
            /**
             * 子线程调用
             */
            override fun onFailure(call: Call, e: IOException?) {
                ThreadUtil.runOnMainThread(object : Runnable {
                    override fun run() {
                        //隐藏刷新控件
                        homeView.onError(e?.message)
                    }
                })
                println("获取数据失败：" + Thread.currentThread().name)
                println("获取数据失败：" + e?.stackTrace)
                println("获取数据失败：" + e?.message)
            }

            override fun onResponse(call: Call, response: Response) {
                val result = response?.body()?.string()
                val gson = Gson()
                val list = gson.fromJson<List<HomeItemBean>>(result, object :
                    TypeToken<List<HomeItemBean>>() {}.type)
                println("获取数据成功：" + list)
                //刷新列表
                ThreadUtil.runOnMainThread(object : Runnable {
                    override fun run() {
                        homeView.loadMore(list)
                    }
                })
            }

        })
    }
}