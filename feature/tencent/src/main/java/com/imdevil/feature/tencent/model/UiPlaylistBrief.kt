package com.imdevil.feature.tencent.model

import com.imdevil.core.tencent.bean.PlaylistBrief
import com.imdevil.core.ui.recyclerview.IRecyclerData

data class UiPlaylistBrief(
    val id: String,
    val title: String,
    val songSize: String = "",
    val listenCount: String,
    val cover: String,
) : IRecyclerData {

    constructor(playlistBrief: PlaylistBrief) : this(
        playlistBrief.id,
        playlistBrief.title,
        playlistBrief.songSize,
        playlistBrief.listenCount,
        playlistBrief.cover,
    )

    override fun areItemsTheSame(oldItem: IRecyclerData) = when (oldItem) {
        is UiPlaylistBrief -> oldItem == this
        else -> false
    }

    override fun areContentsTheSame(oldItem: IRecyclerData) = when (oldItem) {
        is UiPlaylistBrief ->
            oldItem.id == this.id
                    && oldItem.title == this.title
                    && oldItem.songSize == this.songSize
                    && oldItem.listenCount == this.listenCount
                    && oldItem.cover == this.cover

        else -> false
    }
}

fun PlaylistBrief.mapToUiPlaylistBrief() = UiPlaylistBrief(this)

fun UiPlaylistBrief.mapToPlaylistBrief() = PlaylistBrief(
    this.id,
    this.title,
    this.songSize,
    this.listenCount,
    this.cover,
)
