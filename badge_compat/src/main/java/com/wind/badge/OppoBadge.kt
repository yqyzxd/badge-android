package com.wind.badge

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import android.os.Bundle

/**
 * created by wind on 2020/9/22:11:41 AM
 */
class OppoBadge:AppBadgeable {

    override fun setBadge(context: Context, num: Int) {


        var success=setBadgeInternal(context,num)
        println("OppoBadge setBadge:"+success)
    }

    /*fun setBadge1(context: Context, num: Int):Boolean{

        try {
            var args= Bundle()
            args.putInt("app_badge_count", num)
            context.getContentResolver().call(Uri.parse("content://com.android.badge/badge"),
                    "setAppBadgeCount", num.toString(), args)
            return true
        }catch (e: Exception){
            return false
        }

    }*/

    fun setBadgeInternal(context: Context, num: Int):Boolean{

        try {
            var intent= Intent("com.oppo.unsettledevent")
            intent.putExtra("packageName", context.packageName)
            intent.putExtra("number", num)
            intent.putExtra("upgradeNumber", num)
            val packageManager: PackageManager = context.packageManager
            val receivers: List<ResolveInfo> = packageManager.queryBroadcastReceivers(intent, 0)
            if (receivers != null && receivers.size > 0) {
                context.sendBroadcast(intent)
            } else {
                val extras = Bundle()
                extras.putInt("app_badge_count", num)
                context.contentResolver.call(Uri.parse("content://com.android.badge/badge"),
                        "setAppBadgeCount", null, extras)
            }
            return true
        }catch (e: Exception){
            e.printStackTrace()
            return false
        }
    }

}