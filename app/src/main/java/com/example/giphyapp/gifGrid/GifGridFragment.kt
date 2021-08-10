package com.example.giphyapp.gifGrid

import android.os.Bundle
import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.giphyapp.R
import com.example.giphyapp.common.utils.showToastLong
import com.example.giphyapp.common.viewBinding
import com.example.giphyapp.databinding.FragmentGifGridBinding
import com.example.giphyapp.databinding.ItemGifBinding
import com.example.giphyapp.gifGrid.adapter.GiphyGridAdapter
import com.example.giphyapp.gifGrid.adapter.GridItemGif
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GifGridFragment: Fragment(R.layout.fragment_gif_grid){

    private val gifGridViewModel: GifGridViewModel by viewModels()

    private val binding: FragmentGifGridBinding by viewBinding(FragmentGifGridBinding::bind)

    private val gridAdapter: GiphyGridAdapter by lazy { GiphyGridAdapter(::onItemClicked) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            adapter = gridAdapter
            viewModel = gifGridViewModel
            recyclerView.doOnPreDraw {  startPostponedEnterTransition() }
            searchInput.addTextChangedListener(
                afterTextChanged = { gifGridViewModel.searchGifs(it.toString()) }
            )
            sortButton.setOnClickListener { gridAdapter.sort() }
        }
        refreshTrendingGifsIfNoSearchInput()
        observeApiError()
    }

    private fun refreshTrendingGifsIfNoSearchInput(){
        if(binding.searchInput.text.isNullOrBlank()){
            gifGridViewModel.refreshTrendingGifs()
        }
    }

    private fun onItemClicked(gridItemGif: GridItemGif, binding: ItemGifBinding){
        val extras = FragmentNavigatorExtras(
            binding.image to gridItemGif.gifId
        )
        val direction = GifGridFragmentDirections.actionGifGridFragmentToGifDetailsFragment(gridItemGif.gifId)
        findNavController().navigate(direction, extras)
    }

    private fun observeApiError(){
        gifGridViewModel.errorEvent.observe(viewLifecycleOwner){
            if(gridAdapter.itemCount == 0){
                showToastLong(it.localizedMessage ?: "Api error")
            }
        }
    }

}
