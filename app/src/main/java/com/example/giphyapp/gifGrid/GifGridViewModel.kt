package com.example.giphyapp.gifGrid

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams.fromPublisher
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.giphyapp.core.repository.useCase.RefreshTrendingGifsUseCase
import com.example.giphyapp.gifGrid.adapter.GetGifsItemsUseCase
import com.example.giphyapp.gifGrid.adapter.GridItemGif
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GifGridViewModel @Inject constructor(
    private val refreshTrendingGifsUseCase: RefreshTrendingGifsUseCase,
    getGifsItemsUseCase: GetGifsItemsUseCase
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
                Logger.e("refreshTrendingGifs error ${it.localizedMessage}")
            })
    }

    val trendingGifs: LiveData<List<GridItemGif>> = fromPublisher(getGifsItemsUseCase.get())
}