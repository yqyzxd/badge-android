package com.wind.badge

import android.content.ComponentName
import android.content.Context
import android.graphics.drawable.Drawable

/**
 * created by wind on 2020/9/18:5:44 PM
 * https://blog.csdn.net/zyz18813049204/article/details/95938346
 */
interface AppBadgeable {

    fun setBadge(context: Context, num:Int)



    fun getLauncherClassName(context:Context):String{
        var launchComponent=getLauncherComponentName(context)
        if (launchComponent!=null){
            return launchComponent.className
        }
        return ""
    }

    fun getLauncherComponentName(context: Context):ComponentName?{
       var launchIntent= context.packageManager.getLaunchIntentForPackage(context.packageName)

        if (launchIntent!=null){
            return launchIntent.component
        }
        return null;
    }

    fun  getLauncherIcon(context: Context):Int{
       return context.packageManager.getApplicationInfo(context.packageName,0).icon
    }

}