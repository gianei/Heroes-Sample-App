package com.glsebastiany.heroessample.ui.core

import android.databinding.BindingAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView


object BindingAdapters {

    @JvmStatic
    @BindingAdapter("linearAdapter")
    fun setImageAppCompatVector(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = adapter
    }

}