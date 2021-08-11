package com.example.giphyapp.gifGrid

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams.fromPublisher
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
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
    private val getGifsItemsUseCase: GetGifsItemsUseCase,
    private val searchGifUseCase: SearchGifUseCase
) : BaseViewModel() {

    private var _query = ""
    val query
        get() = _query

    private val itemAreSortedAscending: MutableLiveData<Boolean> = MutableLiveData(true)

    fun refreshTrendingGifs() {
        _query = ""
        setLoading(true)
        refreshTrendingGifsUseCase.refresh()
            .doFinally { setLoading(false) }
            .subscribe({
                Logger.d("refreshTrendingGifs success")
            }, {
                Logger.e("refreshTrendingGifs error ${it.localizedMessage}")
                triggerError(it)
            }).remember()
    }

    fun searchGifs(query: String) {
        _query = query
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
                    triggerError(it)
                }).remember()
        }
    }

    fun sort(){
        itemAreSortedAscending.value = itemAreSortedAscending.value?.not() ?: false
    }

    private val itemsSortedAscending = fromPublisher(getGifsItemsUseCase.get())
    private val itemsSortedDescending = fromPublisher(getGifsItemsUseCase.getSortedDescending())

    val gifItems: LiveData<List<GridItemGif>> = itemAreSortedAscending.switchMap {
        when {
            it -> itemsSortedAscending
            else -> itemsSortedDescending
        }
    }
}