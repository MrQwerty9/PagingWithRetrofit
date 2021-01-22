package com.sstudio.pagingwithretrofit

import androidx.paging.PageKeyedDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieDataSource : PageKeyedDataSource<Long, Movie.Result>() {
    private var dataService: ApiService = ApiConfig.apiService

    override fun loadInitial(params: LoadInitialParams<Long?>, callback: LoadInitialCallback<Long?, Movie.Result>) {
        val data: Call<Movie> = dataService.getPopularMovies(BuildConfig.TMDB_API_KEY, "en-US", "1")
        data.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                val photosList = response.body()
                photosList?.let { callback.onResult(it.results, null, 2.toLong()) }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {

            }
        })
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Movie.Result>) {}
    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Movie.Result>) {

        val data: Call<Movie> = dataService.getPopularMovies(BuildConfig.TMDB_API_KEY, "en-US", params.key.toString())
        data.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                val photosList = response.body()
                photosList?.let { callback.onResult(it.results, (params.key.toLong() + 1)) }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable?) {}
        })
    }


}