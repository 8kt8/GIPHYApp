package com.example.giphyapp.gifDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.giphyapp.R
import com.example.giphyapp.common.viewBinding
import com.example.giphyapp.databinding.FragmentGifDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GifDetailsFragment: Fragment(R.layout.fragment_gif_details){

    private val args: GifDetailsFragmentArgs by navArgs()

    private val gifDetailsViewModel: GifDetailsViewModel by viewModels()

    private val binding: FragmentGifDetailsBinding by viewBinding(FragmentGifDetailsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            lifecycleOwner = viewLifecycleOwner
            viewModel = gifDetailsViewModel
        }
        gifDetailsViewModel.refreshById(args.id)
    }
}