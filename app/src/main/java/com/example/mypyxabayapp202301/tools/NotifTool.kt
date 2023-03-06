package com.example.mypyxabayapp202301.tools

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import com.example.mypyxabayapp202301.DetailActivity
import android.annotation.SuppressLint
import android.app.Notification
import android.content.Context
import androidx.core.app.NotificationBuilderWithBuilderAccessor
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.mypyxabayapp202301.R
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * @param notificationManager
 * @param notificationChannel
 * @param context
 * @param notification Builder
 */
class NotifTool {

    var notificationManager: NotificationManager ?= null
    var notificationChannel: NotificationChannel ?= null
    var context: Context?= null
    var notificationBuilder: NotificationCompat.Builder ?= null

    constructor(
        notificationManager: NotificationManager?,
        notificationChannel: NotificationChannel?,
        context: Context?,
        notificationBuilder: NotificationCompat.Builder?
    ) {
        this.notificationManager = notificationManager
        this.notificationChannel = notificationChannel
        this.context = context
        this.notificationBuilder = notificationBuilder
    }


    private fun createNotifChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name: CharSequence = "My notification"
            val desc = "My notification channel desc"

            notificationManager!!.createNotificationChannel(notificationChannel!!)
        }
    }

    @SuppressLint("MissingPermission")
    private fun showNotification(title: String, content: String){
        createNotifChannel()

        //let's set the identification of the notification
        val date = Date()
        val  notifID = SimpleDateFormat("ddHHmmss", Locale.FRANCE).format(date).toInt()
        val notifManagerCompat = NotificationManagerCompat.from(context!!)
        notifManagerCompat.notify(notifID, notificationBuilder!!.build())
    }

}