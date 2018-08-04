package com.glsebastiany.heroessample.ui.core.recyclerview

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

abstract class RVBaseAdapter<T> : RecyclerView.Adapter<RVGenericViewHolder<T>>() {

    var viewModels = mutableListOf<T>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun clear() {
        viewModels.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVGenericViewHolder<T> {

        val binding = DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(parent.context),
                viewType,
                parent,
                false
        )

        return RVGenericViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RVGenericViewHolder<T>,
                                  position: Int) {
        val model = viewModels[position]
        holder.bindModel(model)

    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    override fun getItemCount(): Int {
        return viewModels.count()
    }

    protected abstract fun getLayoutIdForPosition(position: Int): Int

}