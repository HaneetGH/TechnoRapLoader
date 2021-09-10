package com.technorapper.technoraploader.data


import com.technorapper.technoraploader.data.model.UnSplashImageListModel
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {

    @GET("photos")
    suspend fun getImages(
        @Query("client_id") key: String,
        ): UnSplashImageListModel

}