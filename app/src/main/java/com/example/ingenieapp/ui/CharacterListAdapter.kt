package com.example.ingenieapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ingenieapp.databinding.CharacterItemFragmentDataBinding
import com.example.ingenieapp.model.CharacterModel


class ItemClickListener(val clickListener: (charItem: CharacterModel) -> Unit) {
    fun onClick(charItem: CharacterModel) = clickListener(charItem)
}

class Adapter(private val clickListener: ItemClickListener) :
    ListAdapter<CharacterModel, Adapter.ItemViewHolder>(
        DiffCallback
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            CharacterItemFragmentDataBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val charItem = getItem(position)
        holder.bind(charItem, clickListener)
    }

    class ItemViewHolder(private var binding: CharacterItemFragmentDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            charItem: CharacterModel,
            clickListener: ItemClickListener
        ) {
            binding.charItem = charItem
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    //Use of DiffUtil to manage differences in the old and new list when data changes
    companion object DiffCallback : DiffUtil.ItemCallback<CharacterModel>() {
        override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem.name == newItem.name
        }
    }
}