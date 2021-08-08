package com.example.giphyapp.core.repository.useCase

import com.example.giphyapp.core.repository.GetGifByIdRepository
import com.example.giphyapp.gifDetails.model.GifDetailsUiModelMapper
import javax.inject.Inject

class GetGifDetailsByIdUseCase @Inject constructor(
    private val getGifByIdRepository: GetGifByIdRepository,
    private val gifDetailsUiModelMapper: GifDetailsUiModelMapper
) {

    fun get(id: String) = getGifByIdRepository.getById(id)
        .map(gifDetailsUiModelMapper::map)
}