package com.example.downloadapp.features.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.downloadapp.R
import com.example.downloadapp.databinding.ApplicationCardItemBinding

class AppListAdapter(private val onClickListener: (String) -> Unit) : ListAdapter<DownloadAppListUiModel,AppListAdapter.AppViewHolder>(DifferCallBack()) {



    inner class AppViewHolder(private val binding: ApplicationCardItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBindHolder(item: DownloadAppListUiModel, onClickListener: (String) -> Unit) {
            Glide.with(binding.root.context).load(item.iconUrl).into(binding.image)
            binding.name.text = item.name
            val developerNameAndCategory = "${item.developerName} , ${item.category}"
            binding.developerNameAndCategory.text = developerNameAndCategory
            binding.rate.text = item.ratingValue.toString()
            binding.downloads.text = item.downloads

            binding.root.setOnClickListener { onClickListener(item.packageName) }
        }
    }

    class DifferCallBack  : DiffUtil.ItemCallback<DownloadAppListUiModel>() {
        override fun areItemsTheSame(
            oldItem: DownloadAppListUiModel,
            newItem: DownloadAppListUiModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: DownloadAppListUiModel,
            newItem: DownloadAppListUiModel
        ): Boolean {
            return oldItem == newItem
        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val binding = ApplicationCardItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AppViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        val apps = getItem(position)
        holder.onBindHolder(apps,onClickListener)
    }

}