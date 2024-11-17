package com.example.myapplication.remote.apiRepository

import com.example.myapplication.model.GetApiModel
import com.example.myapplication.remote.dataModel.DefaultModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginApiService {
    @FormUrlEncoded
    @POST("email/login")
    suspend fun sendRequest(
        @Field("email") email: String,
    ): retrofit2.Response<DefaultModel>

    @FormUrlEncoded
    @POST("email/login/verify")
    suspend fun verifyCode(
        @Header("app-device-uid") androidId:String,
        @Field("code") code: String,
        @Field("email") email: String,
    ): retrofit2.Response<GetApiModel>

    @FormUrlEncoded
    @POST("product/add")
    suspend fun addProduct(
        @Header("app-device-uid") androidId:String,
        @Field("app-api-key") api: String,
        @Field("id") id: String,
    ): retrofit2.Response<GetApiModel>
}