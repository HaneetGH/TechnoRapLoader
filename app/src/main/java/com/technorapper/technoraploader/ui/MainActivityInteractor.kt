package com.technorapper.technoraploader.ui

import android.content.Context
import com.technorapper.technoraploader.constant.Task
import com.technorapper.technoraploader.data.MyApi
import com.technorapper.technoraploader.data.repository.MainActivityRepository
import com.technorapper.technoraploader.domain.DataState
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainActivityInteractor @Inject constructor(
    @ApplicationContext context: Context, private val mainRepo: MainActivityRepository
) {
    suspend fun fetchImages(): Flow<DataState> {
        return flow {
            emit(DataState.Loading("Fetching Images", Task.FETCH))
            // var response: VehicleCategoriesList = null
            try {
                var response = mainRepo.fetchImages()
                emit(DataState.Success(response, Task.FETCH))
            } catch (e: Exception) {
                //  emit(DataState.ErrorString(e.toString(), Task.VEHICLE_LIST))
            }


        }.catch {  emit(DataState.ErrorString(it.toString(), Task.FETCH)) } // Use the IO thread for this Flow
    }
}