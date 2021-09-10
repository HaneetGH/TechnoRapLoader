package com.technorapper.technoraploader.application

import android.app.Application
import com.technorapper.technoraploader.utils.CustomBindingAdapter
import com.technorapper.technoraploaderlibs.core.TechnoRapLoader

import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApp : Application() {
    @Inject
    lateinit var technoRapLoader: TechnoRapLoader
    override fun onCreate() {
        super.onCreate()

        CustomBindingAdapter(technoRapLoader)
    }


}