package com.glsebastiany.heroessample.ui.core.recyclerview

import com.glsebastiany.bindingrecyclerview.BindingRecyclerView

abstract class MutableListBindingRecyclerViewAdapter<T : BindingRecyclerView.BindableViewHolder> : BindingRecyclerView<T>() {

    var viewModels = mutableListOf<T>()
        private set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun clear() {
        viewModels.clear()
        notifyDataSetChanged()
    }

    fun addViewModels(newViewModels: Collection<T>) {
        val currentSize = itemCount

        viewModels.addAll(newViewModels)
        notifyItemRangeInserted(currentSize, newViewModels.size)
    }

    override fun getItemCount(): Int {
        return viewModels.count()
    }

    override fun getViewModelForPosition(position: Int): T {
        return viewModels[position]
    }
}