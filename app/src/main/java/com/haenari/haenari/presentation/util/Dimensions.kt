package com.haenari.haenari.presentation.util

import android.content.Context
import android.util.DisplayMetrics

object Dimensions {

    fun dpToPx(context: Context, dp:Float): Float{
        return dp * context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT
    }

    fun dpToPx(context: Context, dp:Int): Int{
        return dp * context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT
    }

    fun pxToDp(context: Context, px:Float): Float{
        return px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    fun pxToDp(context: Context, px:Int): Float{
        return px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }
}