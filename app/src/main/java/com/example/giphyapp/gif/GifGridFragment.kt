package com.example.giphyapp.gif

import androidx.fragment.app.Fragment
import com.example.giphyapp.R
import com.example.giphyapp.common.viewBinding
import com.example.giphyapp.databinding.FragmentGifGridBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GifGridFragment: Fragment(R.layout.fragment_gif_grid){

    private val binding: FragmentGifGridBinding by viewBinding(FragmentGifGridBinding::bind)

}

