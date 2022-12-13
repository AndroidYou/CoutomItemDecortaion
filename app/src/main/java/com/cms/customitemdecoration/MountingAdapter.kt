package com.cms.customitemdecoration


import com.cms.customitemdecoration.databinding.ListLayoutMountingBinding
/**
 * @author: Mr.You
 * @create: 2022-12-02 13:28
 * @description:
 **/
class MountingAdapter: BaseAdapter<MountingBean, ListLayoutMountingBinding>(){
    override fun getItemLayoutId(): Int = R.layout.list_layout_mounting

    override fun covert(binding: ListLayoutMountingBinding, t: MountingBean, position: Int) {
        binding.tvMounting.text = t.numberName
    }

    fun getGroupNameByPosition(position: Int):String{
        return mData[position].groupName
    }
    fun getViewIsFirst(position: Int):Boolean{
        return if (position==0){
            true
        }else getGroupNameByPosition(position) != getGroupNameByPosition(position-1)

    }


}