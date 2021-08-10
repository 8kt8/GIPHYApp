package com.example.giphyapp.core.repository

import com.example.giphyapp.core.model.Gif
import com.example.giphyapp.core.model.TrendingGifMapper
import com.example.giphyapp.core.schedulers.CoreSchedulers
import com.example.giphyapp.core.schedulers.TestSchedulers
import com.example.giphyapp.core.service.BackendConfig
import com.example.giphyapp.core.service.GiphyService
import com.example.giphyapp.core.service.model.GifsListResponse
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import org.junit.Test

class SearchGiphyRepositoryTest {

    private val giphyService: GiphyService = mockk()
    private val backendConfig: BackendConfig = mockk()
    private val trendingGifMapper: TrendingGifMapper = mockk()
    private val coreSchedulers: CoreSchedulers = TestSchedulers()

    private val sut = SearchGiphyRepository(
        giphyService = giphyService,
        backendConfig = backendConfig,
        trendingGifMapper = trendingGifMapper,
        coreSchedulers = coreSchedulers
    )

    private val gif: Gif = mockk()

    @Test
    fun refreshAll() {
        val response: GifsListResponse = mockk()
        every { giphyService.search("test", "key") } returns Single.just(response)
        every { trendingGifMapper.map(response) } returns listOf(gif)
        every { backendConfig.apiKey } returns "key"

        sut.search("test").test()
            .assertComplete()
    }

    @Test
    fun `refreshAll when error`() {
        val error = RuntimeException()
        every { giphyService.search("test", "key") } returns Single.error(error)
        every { backendConfig.apiKey } returns "key"

        sut.search("test").test()
            .assertError(error)
    }

    @Test
    fun getAll() {
        val observe = sut.get().test()
        refreshAll()
        observe.assertValue(listOf(gif))
    }
}