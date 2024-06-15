package com.imdevil.feature.tencent.feature.recommend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.imdevil.core.common.utils.Types
import com.imdevil.core.ui.recyclerview.AbsItemViewBuilder
import com.imdevil.core.ui.recyclerview.ViewList
import com.imdevil.feature.tencent.feature.recommend.ChildListItemBuilder.ChildListViewHolder
import com.imdevil.feature.tencent.model.UiPlaylistBrief
import com.imdevil.shot.feature.tencent.databinding.RecommendListItemChildListBinding

class ChildListItemBuilder : AbsItemViewBuilder<ViewList<UiPlaylistBrief>, ChildListViewHolder>(
    1,
    Types.newParameterizedType(ViewList::class.java, UiPlaylistBrief::class.java)
) {
    override fun onBindViewHolderEx(holder: ChildListViewHolder, data: ViewList<UiPlaylistBrief>) {
        holder.bind(data.list)
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ChildListViewHolder(
            RecommendListItemChildListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    class ChildListViewHolder(
        private val binding: RecommendListItemChildListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val childAdapter: RecommendChildListAdapter = RecommendChildListAdapter()

        fun bind(playlists: List<UiPlaylistBrief>) {
            binding.recommendMainListChildList.layoutManager =
                LinearLayoutManager(binding.root.context).also {
                    it.orientation = LinearLayoutManager.HORIZONTAL
                }
            binding.recommendMainListChildList.adapter = childAdapter
            childAdapter.submitList(playlists)
        }
    }
}