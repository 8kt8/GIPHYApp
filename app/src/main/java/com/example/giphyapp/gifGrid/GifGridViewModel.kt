package com.example.giphyapp.gifGrid

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams.fromPublisher
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
) : ViewModel() {

    private val _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading

    fun refreshTrendingGifs() {
        _loading.postValue(true)
        refreshTrendingGifsUseCase.refresh()
            .subscribe({
                Logger.d("refreshTrendingGifs success")
                _loading.postValue(false)
            }, {
                _loading.postValue(false)
                Logger.e("refreshTrendingGifs error ${it.localizedMessage}")
            })
    }

    fun searchGifs(query: String) {
        if(query.isBlank()){
            refreshTrendingGifs()
        } else {
            _loading.postValue(true)
            searchGifUseCase.search(query)
                .subscribe({
                    Logger.d("searchGifUseCase success")
                    _loading.postValue(false)
                }, {
                    _loading.postValue(false)
                    Logger.e("searchGifUseCase error ${it.localizedMessage}")
                })
        }
    }

    val gifItems: LiveData<List<GridItemGif>> = fromPublisher(getGifsItemsUseCase.get())
}