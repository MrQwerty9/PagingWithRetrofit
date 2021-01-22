package com.sstudio.pagingwithretrofit

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource


class MovieDataSourceFactory : DataSource.Factory<Long, Movie.Result>() {
    var movieDataSource: MovieDataSource? = null
    var mutableLiveData: MutableLiveData<MovieDataSource> = MutableLiveData()

    override fun create(): DataSource<Long, Movie.Result> {
        movieDataSource = MovieDataSource()
        mutableLiveData.postValue(movieDataSource)
        return movieDataSource as MovieDataSource
    }

}