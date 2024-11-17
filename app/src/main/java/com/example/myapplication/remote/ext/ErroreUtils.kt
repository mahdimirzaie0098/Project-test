package com.example.myapplication.remote.ext

import com.example.myapplication.remote.dataModel.ErrorModel
import com.google.gson.Gson
import retrofit2.Response

object ErrorUtils {

    fun getError(response: Response<*>): String {
        var error: String? = null
        val errorBodey = response.errorBody()
        if (errorBodey != null)
            error = Gson().fromJson(errorBodey.string(), ErrorModel::class.java).message
        return error?:"ارتباط با سرور مقدور نبود "
    }
}