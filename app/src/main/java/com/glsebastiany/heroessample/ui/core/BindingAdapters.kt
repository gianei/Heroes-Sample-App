package com.glsebastiany.heroessample.ui.core

import android.databinding.BindingAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.glsebastiany.heroessample.R
import com.glsebastiany.heroessample.repository.model.CharactersResponse


object BindingAdapters {

    @JvmStatic
    @BindingAdapter("linearAdapter")
    fun setRecyclerViewAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = adapter
    }

    @JvmStatic
    @BindingAdapter("glideSmallUrl")
    fun setSmallImageGlideUrl(imageView: ImageView, image: CharactersResponse.Data.CharacterResponse.Image?) {
        setSizedImageGlideUrl(imageView, image, "portrait_medium")
    }

    @JvmStatic
    @BindingAdapter("glideUrl")
    fun setBigImageGlideUrl(imageView: ImageView, image: CharactersResponse.Data.CharacterResponse.Image?) {
        setSizedImageGlideUrl(imageView, image, "landscape_incredible")
    }

    private fun setSizedImageGlideUrl(imageView: ImageView, image: CharactersResponse.Data.CharacterResponse.Image?, size: String?) {
        if (image?.path == null) return
        val url = "${image.path}/$size.${image.extension}"
        Glide.with(imageView.context)
                .load(url)
                .apply(RequestOptions().centerCrop().placeholder(R.mipmap.ic_launcher))
                .into(imageView)
    }

}