package com.wind.badge

import android.content.Context
import android.content.Intent

/**
 * created by wind on 2020/9/22:11:57 AM
 */
class VivoBadge:AppBadgeable {

    override fun setBadge(context: Context, num: Int) {
        val intent = Intent("launcher.action.CHANGE_APPLICATION_NOTIFICATION_NUM")
        intent.putExtra("packageName", context.packageName)
        intent.putExtra("className", getLauncherComponentName(context))
        intent.putExtra("notificationNum", num)
        context.sendBroadcast(intent)
    }
}