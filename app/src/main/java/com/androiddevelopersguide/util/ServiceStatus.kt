package com.androiddevelopersguide.util

import android.app.ActivityManager
import android.content.Context

fun isServiceRunning(context: Context, serviceName: String): Boolean {
    val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val runningServices = activityManager.getRunningServices(Integer.MAX_VALUE)
    for (service in runningServices) {
        if (service.service.className == serviceName) {
            return true
        }
    }
    return false
}