package com.example.myapplication.model

import android.content.Context
import com.example.myapplication.androidwrapper.DeviceInfo

class ModelMainActivity(private val context: Context) {

    fun getId() = DeviceInfo.getAndroidId(context)
}