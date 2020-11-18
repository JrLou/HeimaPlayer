package com.example.heimaplayer.ui.fragment

import android.graphics.Color
import android.view.Gravity
import android.view.TextureView
import android.view.View
import android.widget.AbsListView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.heimaplayer.HomeItemBean
import com.example.heimaplayer.R
import com.example.heimaplayer.adapter.HomeAdapter
import com.example.heimaplayer.base.BaseFragment
import com.example.heimaplayer.presenter.impl.HomePresenterImpl
import com.example.heimaplayer.util.ThreadUtil
import com.example.heimaplayer.util.URLProviderUtils
import com.example.heimaplayer.view.HomeView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.*
import java.io.IOException

class HomeFragment : BaseFragment(), HomeView {
    //适配
    val adapter by lazy { HomeAdapter() }
    val presenter by lazy { HomePresenterImpl(this) }
    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_home, null)
    }

    override fun initListener() {
        //初始化recycleviw
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = adapter

        //初始化刷新控件
        refreshLayout.setColorSchemeColors(Color.RED, Color.YELLOW, Color.GREEN)
        //监听刷新
        refreshLayout.setOnRefreshListener {
            //刷新的监听
            loadDatas()
        }

        //监听列表滑动
        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                when(newState){
//                    RecyclerView.SCROLL_STATE_IDLE->{
//                        println("ide1")
//                    }
//                    RecyclerView.SCROLL_STATE_DRAGGING->{
//                        println("ide2")
//                    }
//                    RecyclerView.SCROLL_STATE_SETTLING->{
//                        println("ide3")
//                    }
//                }

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //是否最后一条已经显示
                    val layoutManager = recycler_view.layoutManager
                    if (layoutManager is LinearLayoutManager) {
                        val manager: LinearLayoutManager = layoutManager
                        val lastPosition = manager.findLastVisibleItemPosition()
                        if (lastPosition == adapter.itemCount - 1) {
                            //最后一条已经显示了
                            loadMore(adapter.itemCount - 1)
                        }
                    }
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                println("onscrolled dx=$dx dy=$dy")
            }
        })
    }

    override fun initData() {
        //初始化数据
//        loadDatas()
    }

    private fun loadDatas() {
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
            override fun onFailure(call: Call, e: IOException) {
                ThreadUtil.runOnMainThread(object : Runnable {
                    override fun run() {
                        //隐藏刷新控件
                        refreshLayout.isRefreshing = false
                        myToast("获取数据成功")
                    }
                })
                myToast("获取数据失败")
                println("获取数据失败：" + Thread.currentThread().name)
                println("获取数据失败：" + e.stackTrace)
                println("获取数据失败：" + e.message)
            }

            override fun onResponse(call: Call, response: Response) {
                ThreadUtil.runOnMainThread(object : Runnable {
                    override fun run() {
                        //隐藏刷新控件
                        refreshLayout.isRefreshing = false
                        myToast("获取数据成功")
                    }
                })

                val result = response?.body()?.string()
                val gson = Gson()
                val list = gson.fromJson<List<HomeItemBean>>(result, object :
                    TypeToken<List<HomeItemBean>>() {}.type)
                println("获取数据成功：" + list)
                //刷新列表
                ThreadUtil.runOnMainThread(object : Runnable {
                    override fun run() {
                        adapter.updateList(list)
                    }
                })
            }

        })
    }

    /**
     * 加载更多
     */
    private fun loadMore(offset: Int) {
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
            override fun onFailure(call: Call, e: IOException) {
                ThreadUtil.runOnMainThread(object : Runnable {
                    override fun run() {
                        //隐藏刷新控件
                        refreshLayout.isRefreshing = false
                        myToast("获取数据成功")
                    }
                })
                myToast("获取数据失败")
                println("获取数据失败：" + Thread.currentThread().name)
                println("获取数据失败：" + e.stackTrace)
                println("获取数据失败：" + e.message)
            }

            override fun onResponse(call: Call, response: Response) {
                ThreadUtil.runOnMainThread(object : Runnable {
                    override fun run() {
                        //隐藏刷新控件
                        refreshLayout.isRefreshing = false
                        myToast("获取数据成功")
                    }
                })

                val result = response?.body()?.string()
                val gson = Gson()
                val list = gson.fromJson<List<HomeItemBean>>(result, object :
                    TypeToken<List<HomeItemBean>>() {}.type)
                println("获取数据成功：" + list)
                //刷新列表
                ThreadUtil.runOnMainThread(object : Runnable {
                    override fun run() {
                        adapter.loadMore(list)
                    }
                })
            }

        })
    }
}