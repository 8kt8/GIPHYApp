package com.example.giphyapp.gifGrid.adapter

import com.example.giphyapp.core.model.Gif
import com.example.giphyapp.core.repository.SearchGiphyRepository
import com.example.giphyapp.core.repository.TrendingGiphyRepository
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Flowable
import org.junit.Test

internal class GetGifsItemsUseCaseTest {

    private val gridItemGif1: GridItemGif = mockk{
        every { title } returns "A gif"
    }
    private val gridItemGif2: GridItemGif = mockk{
        every { title } returns "B gif"
    }
    private val gridItemGif3: GridItemGif = mockk{
        every { title } returns "C gif"
    }
    private val gridItemGif4: GridItemGif = mockk{
        every { title } returns "D gif"
    }
    private val gridItemGif5: GridItemGif = mockk{
        every { title } returns "E gif"
    }
    private val gridItemGif6: GridItemGif = mockk{
        every { title } returns "F gif"
    }

    private val gif1: Gif = mockk()
    private val gif2: Gif = mockk()
    private val gif3: Gif = mockk()
    private val gif4: Gif = mockk()
    private val gif5: Gif = mockk()
    private val gif6: Gif = mockk()

    private val trendingGiphyRepository: TrendingGiphyRepository = mockk{
        every { get() } returns Flowable.just(listOf(gif1, gif3, gif2))
    }
    private val searchGiphyRepository: SearchGiphyRepository = mockk{
        every { get() } returns Flowable.just(listOf(gif6, gif4, gif5))
    }
    private val gridItemGifMapper: GridItemGifMapper = mockk{
        every { map(listOf(gif1, gif3, gif2)) } returns listOf(gridItemGif1, gridItemGif3, gridItemGif2)
        every { map(listOf(gif6, gif4, gif5)) } returns listOf(gridItemGif6, gridItemGif4, gridItemGif5)
    }

    private val sut = GetGifsItemsUseCase(
        trendingGiphyRepository = trendingGiphyRepository,
        searchGiphyRepository = searchGiphyRepository,
        gridItemGifMapper = gridItemGifMapper
    )

    @Test
    fun get() {
        sut.get()
            .test()
            .assertValues(listOf(gridItemGif1, gridItemGif2, gridItemGif3), listOf(gridItemGif4, gridItemGif5, gridItemGif6))
    }

    @Test
    fun getError() {
        val error = IndexOutOfBoundsException()
        every { trendingGiphyRepository.get() } returns Flowable.error(error)

        sut.get().test().assertError(error)
    }

    @Test
    fun getSortedDescendingError() {
        val error = IndexOutOfBoundsException()
        every { trendingGiphyRepository.get() } returns Flowable.error(error)

        sut.getSortedDescending().test().assertError(error)
    }
}