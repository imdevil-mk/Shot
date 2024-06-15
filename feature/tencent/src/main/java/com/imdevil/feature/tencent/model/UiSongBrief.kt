package com.imdevil.feature.tencent.model

import com.imdevil.core.tencent.bean.SingerBrief
import com.imdevil.core.tencent.bean.SongBrief
import com.imdevil.core.ui.recyclerview.IRecyclerData

data class UiSongBrief(
    val id: String,
    val mid: String,
    val name: String,
    val mediaId: String = "",
    val singers: List<SingerBrief>,
    val albumId: String = "",
    val albumMid: String = "",
    val albumName: String = "",
) : IRecyclerData {
    override fun areItemsTheSame(oldItem: IRecyclerData) = when (oldItem) {
        is UiSongBrief -> oldItem == this
        else -> false
    }

    override fun areContentsTheSame(oldItem: IRecyclerData) = when (oldItem) {
        is UiSongBrief ->
            oldItem.id == this.id
                    && oldItem.mid == this.mid
                    && oldItem.name == this.name
                    && oldItem.mediaId == this.mediaId
                    && oldItem.singers == this.singers
                    && oldItem.albumId == this.albumId
                    && oldItem.albumMid == this.albumMid
                    && oldItem.albumName == this.albumName

        else -> false
    }
}

fun SongBrief.mapToUiSongBrief() = UiSongBrief(
    this.id,
    this.mid,
    this.name,
    this.mediaId,
    this.singers,
    this.albumId,
    this.albumMid,
    this.albumName,
)