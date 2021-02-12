package com.example.todoyvantenekeu.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

object Api {

    private const val BASE_URL = "https://android-tasks-api.herokuapp.com/api/"
    private const val TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjo0NzksImV4cCI6MTY0MzQ0Njg2NX0.SncAALmitwOquk5CfvnfNF-6kBroYk_kSsGN7ZN2nT8"

    private val jsonSerializer = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    private val converterFactory =
            jsonSerializer.asConverterFactory("application/json".toMediaType())


    // client HTTP
    private val okHttpClient by lazy {
        OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val newRequest = chain.request().newBuilder()
                            .addHeader("Authorization", "Bearer $TOKEN")
                            .build()
                    chain.proceed(newRequest)
                }
                .build()
    }

    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()

    val userService: UserService by lazy {
        retrofit.create(UserService::class.java)
    }

    val taskWebService : TaskWebService by lazy{
        retrofit.create(TaskWebService::class.java)
    }
}