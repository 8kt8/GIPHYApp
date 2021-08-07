package com.example.giphyapp.core.repository.useCase

import com.example.giphyapp.core.repository.SearchGiphyRepository
import javax.inject.Inject

class SearchGifUseCase @Inject constructor(
    private val searchGiphyRepository: SearchGiphyRepository
) {

    fun search(query: String) = searchGiphyRepository.search(query)
}