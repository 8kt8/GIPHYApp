package com.example.giphyapp.common.binding

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

object ViewBinding {

  @JvmStatic
  @BindingAdapter("showIf")
  fun bindGifUrl(view: View, showIf: Boolean) {
      view.isVisible = showIf
  }
}
