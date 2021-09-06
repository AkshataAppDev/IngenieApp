package com.example.ingenieapp.util

import android.view.View

import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.ingenieapp.R
import com.example.ingenieapp.model.CharacterModel
import com.example.ingenieapp.network.APIStatus
import com.example.ingenieapp.ui.Adapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<CharacterModel>?) {

    val adapter = recyclerView.adapter as Adapter
    if (data != null) {
        adapter.submitList(data)
    }
}

@BindingAdapter("urlToImage")
fun bindImage(imgView: ImageView, imgUrl: String?) {

    imgUrl?.let {
        val imageUri = imgUrl.toUri().buildUpon().scheme("https").build()

        Glide.with(imgView.context)
            .load(imageUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken)
            )
            .into(imgView)
    }
}

@BindingAdapter("apiStatus")
fun bindStatus(progressBar: ProgressBar, status: APIStatus?) {

    when (status) {
        APIStatus.LOADING -> {
            progressBar.visibility = View.VISIBLE
        }

        APIStatus.DONE -> {
            progressBar.visibility = View.GONE
        }

        APIStatus.ERROR -> {
            progressBar.visibility = View.GONE
        }
    }
}

