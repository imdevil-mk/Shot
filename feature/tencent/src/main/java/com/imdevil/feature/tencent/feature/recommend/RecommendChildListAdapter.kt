package com.imdevil.feature.tencent.feature.recommend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.imdevil.feature.tencent.model.UiPlaylistBrief
import com.imdevil.shot.feature.tencent.databinding.RecommendListItemPlaylistBinding

class RecommendChildListAdapter :
    ListAdapter<UiPlaylistBrief, RecommendChildListAdapter.PlaylistViewHolder>(
        PlaylistItemDiffCallback()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        return PlaylistViewHolder(
            RecommendListItemPlaylistBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PlaylistViewHolder(private val binding: RecommendListItemPlaylistBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(playlist: UiPlaylistBrief) {
            binding.recommendPlaylistName.text = playlist.title
        }
    }

    class PlaylistItemDiffCallback : ItemCallback<UiPlaylistBrief>() {
        override fun areItemsTheSame(oldItem: UiPlaylistBrief, newItem: UiPlaylistBrief): Boolean {
            return oldItem.areItemsTheSame(newItem)
        }

        override fun areContentsTheSame(
            oldItem: UiPlaylistBrief,
            newItem: UiPlaylistBrief
        ): Boolean {
            return oldItem.areContentsTheSame(newItem)
        }
    }
}