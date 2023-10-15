package com.example.caseapplication.viewholder

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.caseapplication.constants.AppConstants.IMAGE_URL
import com.example.caseapplication.databinding.ItemPostBinding
import com.example.caseapplication.extensions.loadImage
import com.example.caseapplication.recyclerview.DisplayItem
import com.example.caseapplication.recyclerview.ViewHolder
import com.example.caseapplication.recyclerview.ViewHolderBinder
import com.example.caseapplication.recyclerview.ViewHolderFactory


import javax.inject.Inject

class PostViewHolder(private val binding: ItemPostBinding): ViewHolder<PostDTO>(binding) {

    override fun bind(item: PostDTO) {
        with(receiver = binding) {
            tvTitle.text = item.title
            tvBody.text = item.body.toString().replaceFirstChar {
                it.uppercase()
            }
            ivPostLogo.loadImage(url = "${IMAGE_URL}${item.id}")
            rootItem.setOnClickListener {
                itemClickListener?.invoke(item, adapterPosition)
            }
        }
    }

    class HolderFactory @Inject constructor() : ViewHolderFactory {
        override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            PostViewHolder(
                ItemPostBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }

    class BinderFactory @Inject constructor() : ViewHolderBinder {
        override fun bind(holder: RecyclerView.ViewHolder, item: DisplayItem) {
            (holder as PostViewHolder).bind(item as PostDTO)
        }
    }
}