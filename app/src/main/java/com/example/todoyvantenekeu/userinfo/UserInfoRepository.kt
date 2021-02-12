package com.example.todoyvantenekeu.userinfo

import com.example.todoyvantenekeu.network.Api
import com.example.todoyvantenekeu.tasklist.UserInfo
import okhttp3.MultipartBody
import retrofit2.Response

class UserInfoRepository {

    private val userService = Api.userService

    suspend fun updateAvatar(avatar: MultipartBody.Part): Response<UserInfo>? {
        val response = userService.updateAvatar(avatar)
        return if (response.isSuccessful) response else null
    }

    suspend fun getInfo(): UserInfo? {
        val response = userService.getInfo()
        return if (response.isSuccessful) response.body() else null
    }

    suspend fun update(body: UserInfo): UserInfo? {
        val response = userService.update(body)
        return if (response.isSuccessful) response.body() else null
    }
}