package com.example.giphyapp.common.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.giphyapp.common.LoadingDrawable

object ImageViewBinding {

  @JvmStatic
  @BindingAdapter("gifUrl")
  fun bindGifUrl(view: ImageView, gifUrl: String) {
    Glide.with(view.context)
      .asGif()
      .load(gifUrl)
      .placeholder(LoadingDrawable())
      .centerInside()
      .into(view)
  }
}
