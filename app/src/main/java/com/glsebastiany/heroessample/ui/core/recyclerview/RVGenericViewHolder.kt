package com.glsebastiany.heroessample.ui.core.recyclerview

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import com.glsebastiany.heroessample.BR

class RVGenericViewHolder<in T>(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindModel(model: T) {
        binding.setVariable(BR.viewModel, model)
        binding.executePendingBindings()
    }
}