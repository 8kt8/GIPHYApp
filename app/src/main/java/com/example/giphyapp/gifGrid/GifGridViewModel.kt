package com.example.giphyapp.gifGrid

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams.fromPublisher
import com.example.giphyapp.core.common.BaseViewModel
import com.example.giphyapp.core.repository.useCase.RefreshTrendingGifsUseCase
import com.example.giphyapp.core.repository.useCase.SearchGifUseCase
import com.example.giphyapp.gifGrid.adapter.GetGifsItemsUseCase
import com.example.giphyapp.gifGrid.adapter.GridItemGif
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GifGridViewModel @Inject constructor(
    private val refreshTrendingGifsUseCase: RefreshTrendingGifsUseCase,
    getGifsItemsUseCase: GetGifsItemsUseCase,
    private val searchGifUseCase: SearchGifUseCase
) : BaseViewModel() {


    fun refreshTrendingGifs() {
        setLoading(true)
        refreshTrendingGifsUseCase.refresh()
            .doFinally { setLoading(false) }
            .subscribe({
                Logger.d("refreshTrendingGifs success")
            }, {
                Logger.e("refreshTrendingGifs error ${it.localizedMessage}")
            }).remember()
    }

    fun searchGifs(query: String) {
        if(query.isBlank()){
            refreshTrendingGifs()
        } else {
            setLoading(true)
           searchGifUseCase.search(query)
                .doFinally { setLoading(false) }
                .subscribe({
                    Logger.d("searchGifUseCase success")

                }, {
                    Logger.e("searchGifUseCase error ${it.localizedMessage}")
                }).remember()
        }
    }

    val gifItems: LiveData<List<GridItemGif>> = fromPublisher(getGifsItemsUseCase.get())
}