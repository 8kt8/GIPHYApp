package com.example.giphyapp.gifGrid

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.giphyapp.R
import com.example.giphyapp.common.viewBinding
import com.example.giphyapp.databinding.FragmentGifGridBinding
import com.example.giphyapp.gifGrid.adapter.GiphyGridAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GifGridFragment: Fragment(R.layout.fragment_gif_grid){

   private val viewModel: GifGridViewModel by viewModels()

    private val binding: FragmentGifGridBinding by viewBinding(FragmentGifGridBinding::bind)

    private val adapter: GiphyGridAdapter by lazy { GiphyGridAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.adapter = adapter
        binding.viewModel = viewModel
        viewModel.refreshTrendingGifs()
        viewModel.trendingGifs.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }
}

