package com.cms.customitemdecoration

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T,D:ViewDataBinding>:RecyclerView.Adapter<BaseAdapter<T,D>.BaseViewHolder>() {
    protected var mData = mutableListOf<T>()
    private var listener:((data: T,position: Int)->Unit)?=null




    override fun getItemCount(): Int {
      return mData.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
       return BaseViewHolder(DataBindingUtil.inflate<D>(LayoutInflater.from(parent.context),getItemLayoutId(),parent,false))
    }
    abstract fun getItemLayoutId():Int
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
          covert(holder.binding,mData[position],position)
        holder.binding.root.setOnClickListener {
            listener?.invoke(mData[position],position)
        }
    }
    abstract fun covert(binding: D, t: T, position: Int)
    inner class BaseViewHolder( val binding:D) :RecyclerView.ViewHolder(binding.root){
    }
    fun setData(data:MutableList<T>){
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }
    fun addData(data:MutableList<T>){
        mData.addAll(data)
        notifyDataSetChanged()
    }
    fun setOnItemClickListener(mListener:(data: T,position: Int)->Unit){
       this.listener = mListener
    }
}