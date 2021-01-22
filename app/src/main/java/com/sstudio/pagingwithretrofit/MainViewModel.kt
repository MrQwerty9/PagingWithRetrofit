package com.sstudio.pagingwithretrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class MainViewModel : ViewModel() {
    //    var photoRepository: PhotoRepository? = null
    var totalPage = 20
    var pagedListLiveData: LiveData<PagedList<Movie.Result>>? = null
        get() {
            val movieDataSourceFactory: MovieDataSourceFactory = MovieDataSourceFactory()
//            val dataSourceMutableLiveData = photoDataSourceFactory.mutableLiveData
            val executor: Executor
            val config = PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(totalPage)
                .setPrefetchDistance(4)
                .build()
            executor = Executors.newFixedThreadPool(5)
            if (field == null){
                field = MutableLiveData()
                field = LivePagedListBuilder(movieDataSourceFactory, config)
                    .setFetchExecutor(executor)
                    .build()
            }
            return field
        }
        private set
}