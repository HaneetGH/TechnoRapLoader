package com.technorapper.technoraploader.domain

import com.technorapper.technoraploader.constant.Task


sealed class DataState {

    data class Success<out T>(val data: T, val task: Task) : DataState()
    data class SuccessRes<out T>(val data: T) : DataState()
    data class Progress<out T>(val data: T, val task: Task) : DataState()
    data class Error(val exception: Exception, val task: Task) : DataState()
    data class ErrorThrowable(val exception: Throwable, val task: Task) : DataState()
    data class ErrorString(val exception: String, val task: Task) : DataState()
    data class Loading(val message: String,val task: Task) : DataState()

}
