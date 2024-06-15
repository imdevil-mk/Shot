package com.imdevil.core.ui.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.lang.reflect.Type

interface IItemViewBuilder<T : IRecyclerData> {
    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, data: IRecyclerData)
}

abstract class AbsItemViewBuilder<T : IRecyclerData, VH : RecyclerView.ViewHolder>(
    val viewType: Int,
    val dataType: Type,
) : IItemViewBuilder<T> {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, data: IRecyclerData) {
        onBindViewHolderEx(holder as VH, data as T)
    }

    abstract fun onBindViewHolderEx(holder: VH, data: T)
}