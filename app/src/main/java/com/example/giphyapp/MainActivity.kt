package com.example.giphyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.giphy.sdk.core.models.Media
import com.giphy.sdk.ui.Giphy
import com.giphy.sdk.ui.pagination.GPHContent
import com.giphy.sdk.ui.views.GiphyDialogFragment
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Giphy.configure(this, SDK_KEY)
        GiphyDialogFragment.newInstance().show(supportFragmentManager, "giphy_dialog")
        GPHContent.trendingGifs
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //if (requestCode == REQUEST_GIFS) {
            val media = data?.getParcelableExtra<Media>(GiphyDialogFragment.MEDIA_DELIVERY_KEY)
            val keyword = data?.getStringExtra(GiphyDialogFragment.SEARCH_TERM_KEY)
            //TODO: handle received data
       // }
        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        val SDK_KEY = "MCUiA1IDBXwzHytclLo2oKpOiv1pH4gl"
    }
}