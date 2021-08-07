package com.example.giphyapp.common.binding

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.giphyapp.BR

open class BindingViewHolder<Item, Binding : ViewDataBinding>(
    val binding: Binding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Item) {
        binding.setVariable(BR.item, item)
        binding.executePendingBindings()
    }
}