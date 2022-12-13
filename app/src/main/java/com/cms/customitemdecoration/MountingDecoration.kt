package com.cms.customitemdecoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.min

/**
 * @author: Mr.You
 * @create: 2022-12-02 11:50
 * @description:
 **/
class MountingDecoration( context: Context) : RecyclerView.ItemDecoration() {
    private var mPaint: Paint = Paint()

    private var mHeadPaint = Paint()

    private var mHeadHeight = dp2px(context, 60)

    private var mItemPaint = Paint()
    private var mItemHeight = dp2px(context, 5)

    private var mTextPaint = Paint()


    init {
        mPaint.color = Color.RED
        mHeadPaint.color = Color.BLUE
        mItemPaint.color = Color.GREEN
        mTextPaint.color = Color.WHITE
        mTextPaint.textSize = context.resources.displayMetrics.density*16


    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                                state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        if (parent.adapter is MountingAdapter) {
            val adapter = parent.adapter as MountingAdapter
            val position = parent.getChildAdapterPosition(view)
            if (adapter.getViewIsFirst(position)) {
                outRect.set(0, mHeadHeight, 0, 0)
            } else {
                outRect.set(0, mItemHeight, 0, 0)
            }
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

        if (parent.adapter is MountingAdapter) {
            val adapter = parent.adapter as MountingAdapter
            for (i in 0 until parent.childCount) {
                val view = parent.getChildAt(i)
                val position = parent.getChildAdapterPosition(view)
                if (adapter.getViewIsFirst(position)) {
                    val rect = Rect()
                    c.drawRect(
                        0f,
                        (view.top - mHeadHeight - parent.paddingTop).toFloat(),
                        (view.width + parent.paddingRight).toFloat(), (view.top + mHeadHeight).toFloat(), mHeadPaint
                    )

                    val groupName =  adapter.getGroupNameByPosition(position)

                    mTextPaint.getTextBounds(groupName, 0, groupName.length,rect)

                    c.drawText(groupName,dp2px(parent.context,20).toFloat(),
                        ((view.top-mHeadHeight/2)+rect.height()).toFloat(),mTextPaint)

                } else {
                    c.drawRect(
                        0f,
                        (view.top - mItemHeight - parent.paddingTop).toFloat(),
                        (view.width - parent.paddingLeft).toFloat(),
                        (view.top + mItemHeight).toFloat(),
                        mItemPaint
                    )
                }
            }


        }


    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        if (parent.adapter is MountingAdapter) {
            val adapter = parent.adapter as MountingAdapter

            val top = parent.paddingTop
            val left = parent.paddingLeft
            val right = parent.width-parent.paddingRight
            for (i in 0 until parent.childCount){
                val position = (parent.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                val view =  parent.findViewHolderForLayoutPosition(position)!!.itemView
               if (adapter.getViewIsFirst(position+1)){
                   val bottom = min(top+mHeadHeight,view.bottom)
                   c.drawRect(0f,0f, parent.width.toFloat(), (view.bottom).toFloat(),mPaint)
                   val rect = Rect()
                   val groupName =  adapter.getGroupNameByPosition(position)
                   mTextPaint.getTextBounds(groupName, 0, groupName.length,rect)

                   c.clipRect(left,top,right,bottom)

                   c.drawText(groupName,dp2px(parent.context,20).toFloat(),
                       rect.height()/2+view.bottom-mHeadHeight/2.toFloat(),mTextPaint)

               }else{
                   c.drawRect(0f,0f, parent.width.toFloat(), mHeadHeight.toFloat(),mPaint)
                   val rect = Rect()
                   val groupName =  adapter.getGroupNameByPosition(position)
                   mTextPaint.getTextBounds(groupName, 0, groupName.length,rect)
                   c.drawText(groupName,dp2px(parent.context,20).toFloat(),
                      rect.height()+mHeadHeight/2.toFloat(),mTextPaint)
               }


           }


        }
    }


}