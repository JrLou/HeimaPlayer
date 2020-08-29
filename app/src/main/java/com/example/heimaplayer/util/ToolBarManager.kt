package com.example.heimaplayer.util

import android.content.Intent
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.example.heimaplayer.R
import com.example.heimaplayer.ui.activity.SettingActivity

/**
 * ClassName:ToolBarManager
 * Description:所有界面toolbar的管理界面
 */
interface ToolBarManager{
    val toolbar: Toolbar

    /**
     * 初始化主界面中的toolbar
     */
    fun initMainToolBar(){
        toolbar.setTitle("黑马影音")
        toolbar.inflateMenu(R.menu.main)
        toolbar.setOnMenuItemClickListener(object :Toolbar.OnMenuItemClickListener{
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                when(item?.itemId){
                    R.id.setting->{
                        //跳转到设置页面
                        toolbar.context.startActivity(Intent(toolbar.context,SettingActivity::class.java))
                    }
                }
                return true
            }
        })
    }
}