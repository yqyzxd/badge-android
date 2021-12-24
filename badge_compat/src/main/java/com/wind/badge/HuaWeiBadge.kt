package com.wind.badge

import android.content.Context
import android.net.Uri
import android.os.Bundle

/**
 * created by wind on 2020/9/18:5:47 PM
 */
class HuaWeiBadge:AppBadgeable {
    override fun setBadge(context: Context, num: Int) {
        try {
            val bunlde = Bundle()
            bunlde.putString("package", context.packageName) // com.test.badge is your package name
            bunlde.putString("class", getLauncherClassName(context)) // com.test. badge.MainActivity is your apk main activity
            bunlde.putInt("badgenumber", num)
            context.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", null, bunlde)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}