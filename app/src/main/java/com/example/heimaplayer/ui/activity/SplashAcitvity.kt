package com.example.heimaplayer.ui.activity

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorListener
import com.example.heimaplayer.R
import com.example.heimaplayer.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashAcitvity: BaseActivity(), ViewPropertyAnimatorListener {
    override fun onAnimationEnd(view: View?) {
        //进入主界面
//        startActivity<MainActivity>()
//        finish()

        startActivityAndFinish<MainActivity>()
    }

    override fun onAnimationCancel(view: View?) {

    }

    override fun onAnimationStart(view: View?) {

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initData(){
        ViewCompat.animate(imageView).scaleX(1.0f).scaleY(1.0f).setListener(this).setDuration(2000)
    }
}