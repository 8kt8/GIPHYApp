package com.example.giphyapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.giphyapp.core.repository.GiphyRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var giphyRepository: GiphyRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}