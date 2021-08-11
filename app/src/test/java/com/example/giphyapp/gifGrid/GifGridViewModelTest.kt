package com.example.giphyapp.gifGrid

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.giphyapp.core.repository.useCase.RefreshTrendingGifsUseCase
import com.example.giphyapp.core.repository.useCase.SearchGifUseCase
import com.example.giphyapp.gifGrid.adapter.GetGifsItemsUseCase
import com.example.giphyapp.gifGrid.adapter.GridItemGif
import com.jraska.livedata.test
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import org.junit.Rule
import org.junit.Test

internal class GifGridViewModelTest{

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val gridItemGif1: GridItemGif = mockk()
    private val gridItemGif2: GridItemGif = mockk()
    private val items = listOf(gridItemGif1, gridItemGif2)
    private val itemsSortedDescending = listOf(gridItemGif2, gridItemGif1)

    private val refreshTrendingGifsUseCase: RefreshTrendingGifsUseCase = mockk()
    private val getGifsItemsUseCase: GetGifsItemsUseCase = mockk{
        every { get() } returns Flowable.just(items)
        every { getSortedDescending() } returns Flowable.just(itemsSortedDescending)
    }
    private val searchGifUseCase: SearchGifUseCase = mockk()

    private val sut by lazy {
        GifGridViewModel(
            refreshTrendingGifsUseCase = refreshTrendingGifsUseCase,
            getGifsItemsUseCase = getGifsItemsUseCase,
            searchGifUseCase = searchGifUseCase
        )
    }

    @Test
    fun refreshTrendingGifs() {
        every { refreshTrendingGifsUseCase.refresh() } returns Completable.complete()
        val observer = sut.loading.test()

        sut.refreshTrendingGifs()
        observer.assertValueHistory(false, true ,false)
        verify { refreshTrendingGifsUseCase.refresh() }
    }

    @Test
    fun refreshTrendingGifsError() {
        every { refreshTrendingGifsUseCase.refresh() } returns Completable.error(Exception())
        val observer = sut.loading.test()

        sut.refreshTrendingGifs()
        assert(sut.query == "")
        observer.assertValueHistory(false, true ,false)
    }

    @Test
    fun searchGifs() {
        every { searchGifUseCase.search("query") } returns Completable.error(Exception())
        val observer = sut.loading.test()

        sut.searchGifs("query")
        assert(sut.query == "query")
        observer.assertValueHistory(false, true ,false)
        verify { searchGifUseCase.search("query") }
    }

    @Test
    fun searchGifsError() {
        every { searchGifUseCase.search("query") } returns Completable.error(Exception())
        val observer = sut.loading.test()

        sut.searchGifs("query")
        observer.assertValueHistory(false, true ,false)
    }

    @Test
    fun getGifItems() {
        every { getGifsItemsUseCase.get() } returns Flowable.just(items)

        sut.gifItems.test().assertValue(items)
    }

    @Test
    fun sort() {
        val observer = sut.gifItems.test()
        sut.sort()
        observer.assertValueHistory(items, itemsSortedDescending)
    }
}