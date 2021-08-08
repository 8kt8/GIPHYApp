package com.example.giphyapp.common.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.giphyapp.common.ui.ListItem
import com.example.giphyapp.common.utils.ifNotNullAs

object RecyclerViewBinding {

  @JvmStatic
  @BindingAdapter("adapter")
  fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter.apply {
      stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
    }
  }

  @JvmStatic
  @BindingAdapter("submitList")
  fun bindSubmitList(view: RecyclerView, itemList: List<ListItem>?) {
    view.adapter.ifNotNullAs<BindingListAdapter<ListItem, *>> { adapter ->
      adapter.submitList(itemList)
    }
  }

}
