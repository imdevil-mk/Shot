package com.imdevil.feature.tencent.feature.recommend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.imdevil.core.ui.recyclerview.AbsItemViewBuilder
import com.imdevil.feature.tencent.model.UiSongBrief
import com.imdevil.shot.feature.tencent.databinding.RecommendListItemSongBinding

class SongItemBuilder :
    AbsItemViewBuilder<UiSongBrief, SongItemBuilder.SongViewHolder>(2, UiSongBrief::class.java) {
    override fun onBindViewHolderEx(holder: SongViewHolder, data: UiSongBrief) {
        holder.bind(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return SongViewHolder(
            RecommendListItemSongBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    class SongViewHolder(private val binding: RecommendListItemSongBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(song: UiSongBrief) {
            binding.recommendSongName.text = song.name
        }
    }
}

