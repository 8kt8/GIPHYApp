package com.example.giphyapp.gifGrid

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
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
        onBoundView()
        refreshTrendingGifsIfNoSearchInput()
        observeApiError()
    }

    private fun onBoundView() = with(binding){
        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            adapter = gridAdapter
            viewModel = gifGridViewModel
            sortButton.setOnClickListener { gifGridViewModel.sort() }
            onBoundSearchInput()
            onBoundRecyclerView()
        }
    }

    private fun onBoundSearchInput() = with(binding.searchInput){
            setText(gifGridViewModel.query)
            addTextChangedListener(afterTextChanged = {
                if(it.toString() != gifGridViewModel.query){
                    gifGridViewModel.searchGifs(it.toString())
                }
            })
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun onBoundRecyclerView() = with(binding.recyclerView) {
        doOnPreDraw {  startPostponedEnterTransition() }
        setOnTouchListener { _, _ ->
            hideKeyboard()
            false
        }
    }

    private fun refreshTrendingGifsIfNoSearchInput(){
        if(gifGridViewModel.query.isBlank()){
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

    private fun hideKeyboard() {
        val imm = requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.searchInput.windowToken, 0)
    }

}
