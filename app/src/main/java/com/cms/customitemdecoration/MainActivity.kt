package com.cms.customitemdecoration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.cms.customitemdecoration.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       val mBinding =  DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        var list = arrayListOf<MountingBean>()
        for (i in 0 ..5){
            for (j in 0 ..5){
                list.add(MountingBean("北京第$i","丰城$j"))
            }
        }
        var mAdapter = MountingAdapter()
        mBinding.recycleViewMounting.run {
            addItemDecoration(MountingDecoration(this@MainActivity))
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mAdapter
        }
        mAdapter.setData(list)
    }
}