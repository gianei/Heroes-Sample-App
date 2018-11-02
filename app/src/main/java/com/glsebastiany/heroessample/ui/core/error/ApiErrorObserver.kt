package com.glsebastiany.heroessample.ui.core.error

import android.arch.lifecycle.Observer
import android.content.Context
import android.widget.Toast
import com.glsebastiany.heroessample.R

class ApiErrorObserver(private val context: Context) : Observer<ApiError> {

    override fun onChanged(t: ApiError?) {

        t?.let {
            val message = when (it) {
                ApiError.NO_CONNECTIVITY -> context.getString(R.string.no_network_message)
                ApiError.UNKNOWN -> context.getString(R.string.generic_error)
            }

            Toast.makeText(context, message, Toast.LENGTH_LONG).show()

        }
    }

}