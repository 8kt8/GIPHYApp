package com.example.giphyapp.gifDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams.fromPublisher
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.example.giphyapp.core.common.BaseViewModel
import com.example.giphyapp.core.repository.useCase.GetGifDetailsByIdUseCase
import com.example.giphyapp.core.repository.useCase.RefreshGifByIdUseCase
import com.example.giphyapp.gifDetails.model.GifDetailsUiModel
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GifDetailsViewModel @Inject constructor(
    private val refreshGifByIdUseCase: RefreshGifByIdUseCase,
    private val getGifDetailsByIdUseCase: GetGifDetailsByIdUseCase
): BaseViewModel() {

    private val gifId: MutableLiveData<String> = MutableLiveData()

    val gifDetailsUiModel: LiveData<GifDetailsUiModel> =
        gifId.switchMap { fromPublisher(getGifDetailsByIdUseCase.get(it)) }

    fun refreshById(id: String) {
        gifId.value = id
        if(id.isNotBlank()){
            setLoading(true)
            refreshGifByIdUseCase.refresh(id)
                .doFinally { setLoading(false) }
                .subscribe({
                    Logger.d("refresh gif by id :$id success")
                }, {
                    Logger.e("refresh gif by id :$id ${it.localizedMessage}")
                }).remember()
        }
    }
}