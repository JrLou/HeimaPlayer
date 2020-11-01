package com.example.heimaplayer.ui.activity
import androidx.appcompat.widget.Toolbar
import com.example.heimaplayer.R
import com.example.heimaplayer.base.BaseActivity
import com.example.heimaplayer.util.FragmentUtil
import com.example.heimaplayer.util.ToolBarManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find

class MainActivity : BaseActivity(),ToolBarManager {
    //惰性加载
    override val toolbar by lazy {find<Toolbar>(R.id.toolbar)}

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        initMainToolBar()
    }

    override fun initListener() {
        //设置tab切换的监听
        bottom_bar.setOnTabSelectListener{
            //it 代表参宿tabId

            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, FragmentUtil.fragmentUtil.getFragment(it)!!,it.toString())
            transaction.commit()
        }
    }
}
