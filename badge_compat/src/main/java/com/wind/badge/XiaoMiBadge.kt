package com.wind.badge

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

/**
 * created by wind on 2020/9/18:5:49 PM
 */
class XiaoMiBadge : AppBadgeable {

    companion object{
        var notificationId:Int=0
        val NOTIFICATION_CHANNEL = "com.marryu"
    }

    override fun setBadge(context: Context, num: Int) {
        try {
            /**
             * Field field = notification.getClass().getDeclaredField(“extraNotification”);
            Object extraNotification = field.get(notification);
            Method method = extraNotification.getClass().getDeclaredMethod(“setMessageCount”, int.class);
            method.invoke(extraNotification, mCount);
             */
            //todo 获取notification
            var notificationManager:NotificationManager= context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (notificationManager==null){
                return
            }
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                var channel=NotificationChannel(NOTIFICATION_CHANNEL, "badge", NotificationManager.IMPORTANCE_DEFAULT)
                channel.setShowBadge(true)
                notificationManager.createNotificationChannel(channel)

            }
            var intent =Intent(context, Class.forName(getLauncherClassName(context)))
            var pendingIntent=PendingIntent.getActivity(context, 0, intent, 0)
            var notification=NotificationCompat.Builder(context, "badge")
                    .setContentTitle("")
                    .setContentText("")
                    .setSmallIcon(getLauncherIcon(context))
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setChannelId("badge")
                    .setNumber(num)
                    .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL).build();

            setBadge(num, notification)
            notificationManager.notify(notificationId++, notification)

        } catch (e: Exception) {
            e.printStackTrace();
        }
    }

    private  fun setBadge(count: Int, notification: Notification) {
        try {
            var field = notification::class.java.getDeclaredField("extraNotification");
            var extraNotification = field.get(notification);
            var method = extraNotification::class.java.getDeclaredMethod("setMessageCount", Int::class.java)
            method.invoke(extraNotification, count);
        } catch (e: Exception) {
            e.printStackTrace();
        }
    }

}