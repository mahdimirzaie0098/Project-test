package com.example.myapplication.androidwrapper

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings

object DeviceInfo {

    private var androidID: String? = null

    @SuppressLint("HardwareIds")
    fun getAndroidId(context: Context): String {

        if (androidID == null)
            androidID = Settings.Secure.getString(
                context.contentResolver,
                Settings.Secure.ANDROID_ID
            )

        return androidID ?: ""
    }
}