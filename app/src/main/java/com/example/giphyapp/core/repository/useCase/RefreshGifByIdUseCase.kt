package com.example.giphyapp.core.repository.useCase

import com.example.giphyapp.core.repository.GetGifByIdRepository
import javax.inject.Inject

class RefreshGifByIdUseCase @Inject constructor(
    private val getGifByIdRepository: GetGifByIdRepository
) {

    fun refresh(id: String) = getGifByIdRepository.refreshById(id)
}