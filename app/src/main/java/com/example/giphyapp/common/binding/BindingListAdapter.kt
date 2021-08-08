package com.example.giphyapp.common.binding

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.giphyapp.common.ui.ListItem

abstract class BindingListAdapter<Item: ListItem, Binding : ViewDataBinding>(
    val onItemClickListener: (Item) -> Unit = {},
    diffUtil: DiffUtil.ItemCallback<Item> = DefaultDiffCallback()
) : ListAdapter<Item, BindingViewHolder<Item, Binding>>(diffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingViewHolder<Item, Binding> = onCreateBindingViewHolder(parent, viewType)

    @LayoutRes
    abstract fun getLayoutId(viewType: Int): Int

    private fun onCreateBindingViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<Item, Binding> =
        BindingViewHolder(createBinding(parent, viewType))

    override fun onBindViewHolder(holder: BindingViewHolder<Item, Binding>, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        onBoundView(item, holder.binding, position)
    }

    open fun onBoundView(item: Item, binding: Binding, position: Int) {
        binding.root.setOnClickListener{ onItemClickListener(item) }
    }

    override fun getItemViewType(position: Int) = getItem(position).type

    private fun createBinding(parent: ViewGroup, viewType: Int): Binding = DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),
        getLayoutId(viewType),
        parent,
        false
    )

    private class DefaultDiffCallback<Item: ListItem> : DiffUtil.ItemCallback<Item>() {

        override fun areItemsTheSame(oldItem: Item, newItem: Item) =
            oldItem.id == newItem.id

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Item, newItem: Item) =
            oldItem == newItem
    }
}