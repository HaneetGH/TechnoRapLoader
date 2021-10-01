package com.technorapper.technoraploader.data.repository


import android.content.Context
import android.util.Log
import com.technorapper.technoraploader.constant.Task
import com.technorapper.technoraploader.data.MyApi
import com.technorapper.technoraploader.data.model.UnSplashImageListModel
import com.technorapper.technoraploader.domain.DataState
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

import javax.inject.Inject


class MainActivityRepository @Inject constructor(
    @ApplicationContext context: Context, private val myApi: MyApi
) : BaseRepository() {
    private val appContext = context.applicationContext
    var API_KEY = "g5vdSATS8nW3HQCG4IR9Nes8vvLJFhcWc5QsexVeMpM"


    suspend fun fetchImages(): UnSplashImageListModel {
        return myApi.getImages(API_KEY)
    } // Use the IO thread for this Flow
}




