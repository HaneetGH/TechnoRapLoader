package com.technorapper.technoraploader.ui

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import com.technorapper.technoraploader.R
import com.technorapper.technoraploader.base.BaseClass
import com.technorapper.technoraploader.constant.Task
import com.technorapper.technoraploader.data.model.UnSplashImageListModelItem
import com.technorapper.technoraploader.databinding.ActivityMainBinding
import com.technorapper.technoraploader.domain.DataState
import com.technorapper.technoraploader.ui.adapter.ListAdapter
import com.technorapper.technoraploader.utils.ListDiffCallback
import com.technorapper.technoraploaderlibs.core.TechnoRapLoader
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseClass() {
    @Inject
    lateinit var technoRapLoader: TechnoRapLoader
    private lateinit var binding: ActivityMainBinding
    lateinit var listAdapter: ListAdapter;
    private val viewModel by viewModels<MainActivityViewModel>()
    private val listOfImages: ArrayList<UnSplashImageListModelItem> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onResume() {
        super.onResume()

    }

    override fun setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setAdapter()
        viewModel.setStateEvent(MainStateEvent.FetchImages)
        viewModel.uiState.observe(this, { parse(it) })
        binding.fab.setOnClickListener { technoRapLoader.cancelAll() }
    }

    private fun setAdapter() {
        listAdapter = ListAdapter(listOfImages, this) { v, position ->
            when (v.id) {
                R.id.clear_text -> {
                    technoRapLoader.cancelTask(listOfImages[position].urls.thumb)

                }
            }


        }
        binding.adapter = listAdapter
    }

    private fun parse(it: DataState?) {

        if (it != null) {
            when (it) {
                is DataState.Success<*> -> {
                    Log.d("Api Response", "SUCCES");

                    if (it?.data != null) {
                        when (it.task) {
                            Task.FETCH -> {
                                try {
                                    binding.contentLoadingProgressBar.visibility= View.GONE
                                    val value = it.data as List<UnSplashImageListModelItem>
                                    if (value.isNotEmpty())
                                        setData(value)
                                    else
                                    // binding.isListHere = false
                                        Log.d("Api Response", value.toString())
                                } catch (e: Exception) {

                                }
                            }


                        }

                    }

                }
                is DataState.Error -> {
                    binding.contentLoadingProgressBar.visibility= View.VISIBLE
                    Log.d("Api Response", "ERROR ${it.exception.toString()}");
                }
                is DataState.Loading -> {
                    binding.contentLoadingProgressBar.visibility= View.VISIBLE
                    Log.d("Api Response", "LOADING $it");


                }
            }
        }


    }

    fun setData(locationTable: List<UnSplashImageListModelItem>) {
        val diffCallback = ListDiffCallback(listOfImages, locationTable)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        listOfImages.clear()
        listOfImages.addAll(locationTable)
        diffResult.dispatchUpdatesTo(listAdapter)
    }
}