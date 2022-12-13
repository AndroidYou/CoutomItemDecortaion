package com.cms.customitemdecoration

import android.content.Context


fun dp2px(context: Context, dp:Int):Int = (context.resources.displayMetrics.density*dp+0.5).toInt()
