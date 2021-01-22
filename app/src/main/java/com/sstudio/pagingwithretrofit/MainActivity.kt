package com.sstudio.pagingwithretrofit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        rv_photos.layoutManager = GridLayoutManager(this, 3)

        mainActivityViewModel.pagedListLiveData?.observe(
            this, { movies ->
                val photosAdapter = MoviesAdapter()
                photosAdapter.submitList(movies)
                rv_photos.adapter = photosAdapter
            })
    }
}