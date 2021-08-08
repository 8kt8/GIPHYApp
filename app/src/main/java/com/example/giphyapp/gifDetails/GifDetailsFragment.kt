package com.example.giphyapp.gifDetails

import android.net.Uri
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.app.ShareCompat
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementReturnTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            lifecycleOwner = viewLifecycleOwner
            viewModel = gifDetailsViewModel
            image.transitionName = args.id
            goToGiphyButton.setOnClickListener { openGiphyGoogleTab() }
            shareButton.setOnClickListener { shareGifUrl() }
        }
        gifDetailsViewModel.refreshById(args.id)
    }

    private fun openGiphyGoogleTab(){
        getUrl()?.let {
            val uri = Uri.parse(it)
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(requireContext(), uri)
        }
    }

    private fun shareGifUrl(){
        getUrl()?.let { url ->
            ShareCompat.IntentBuilder(requireContext())
                .setType("text/plain")
                .setChooserTitle("Share GIPHY URL")
                .setText(url)
                .startChooser()
        }
    }

    private fun getUrl() = gifDetailsViewModel.gifDetailsUiModel.value?.gifUrl
}