package com.decagon.android.sq007.Retrofits

import com.decagon.android.sq007.utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val instance by lazy {
             Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

    }

    val apiservice: ApiService by lazy {instance.build().create(ApiService::class.java)}

}