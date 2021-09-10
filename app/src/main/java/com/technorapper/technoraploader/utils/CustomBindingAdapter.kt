package com.technorapper.technoraploader.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.technorapper.technoraploader.R
import com.technorapper.technoraploaderlibs.core.TechnoRapLoader
import java.text.DecimalFormat


object CustomBindingAdapter {
    lateinit var technoRapLoader: TechnoRapLoader

    @JvmStatic
    @BindingAdapter("android:loadFromUrl")
    fun loadFromUrl(view: ImageView, url: String) {
        if (url == null || url.equals("", ignoreCase = true)) {
            view.setImageResource(android.R.color.transparent);
            return
        }
        technoRapLoader.displayImage(url, view, R.drawable.loading)
    }

    operator fun invoke(technoRapLoader: TechnoRapLoader) {
        this.technoRapLoader = technoRapLoader;

    }

}