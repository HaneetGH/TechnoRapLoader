package com.technorapper.technoraploaderlibs.core
import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.technorapper.technoraploaderlibs.cache.CacheRepository
import com.technorapper.technoraploaderlibs.cache.utils.Config
import com.technorapper.technoraploaderlibs.data.DownloadImageTask
import com.technorapper.technoraploaderlibs.data.DownloadTask

import java.util.concurrent.Executors
import java.util.concurrent.Future
class TechnoRapLoader  private constructor(context: Context, cacheSize: Int) {
    private val cache = CacheRepository(context, cacheSize)
    private val executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
    private val mRunningDownloadList:HashMap<String,Future<Bitmap?>> = hashMapOf()

    fun displayImage(url: String, imageview: ImageView, placeholder:
    Int) {
        var bitmap = cache.get(url)
        bitmap?.let {
            imageview.setImageBitmap(it)
            return
        }
            ?: run {
                imageview.tag = url
                if (placeholder != null)
                    imageview.setImageResource(placeholder)
                addDownloadImageTask( url, DownloadImageTask(url , imageview , cache)) }

    }

    fun addDownloadImageTask(url: String,downloadTask: DownloadTask<Bitmap?>) {

        mRunningDownloadList.put(url,executorService.submit(downloadTask))
    }

    fun clearcache() {
        cache.clear()
    }

    fun cancelTask(url: String){
        synchronized(this){
            mRunningDownloadList.forEach {
                if (it.key == url && !it.value.isDone)
                    it.value.cancel(true)
            }
        }
    }

    fun cancelAll() {
        synchronized (this) {
            mRunningDownloadList.forEach{
                if ( !it.value.isDone)
                    it.value.cancel(true)
            }
            mRunningDownloadList.clear()
        }
    }

    companion object {
        private val INSTANCE: TechnoRapLoader? = null
        @Synchronized
        fun getInstance(context: Context, cacheSize: Int = Config.defaultCacheSize): TechnoRapLoader {
            return INSTANCE?.let { return INSTANCE }
                ?: run {
                    return TechnoRapLoader(context, cacheSize)
                }
        }
    }
}