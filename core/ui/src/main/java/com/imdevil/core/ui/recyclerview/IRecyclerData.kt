package com.imdevil.core.ui.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.imdevil.core.common.utils.Types
import java.lang.reflect.Type

private const val TAG = "IRecyclerData"

interface IRecyclerData {
    fun areItemsTheSame(oldItem: IRecyclerData): Boolean
    fun areContentsTheSame(oldItem: IRecyclerData): Boolean
}

data class ViewList<T : IRecyclerData>(
    val dataType: Type,
    val list: List<T>
) : IRecyclerData {
    override fun areItemsTheSame(oldItem: IRecyclerData) = when (oldItem) {
        is ViewList<*> -> oldItem == this
        else -> false
    }

    override fun areContentsTheSame(oldItem: IRecyclerData) = when (oldItem) {
        is ViewList<*> -> {
            var same = true
            if (oldItem.list.size != this.list.size) {
                same = false
            } else {
                for ((index, newItem) in list.withIndex()) {
                    val old = oldItem.list[index]
                    if (!newItem.areContentsTheSame(old)) {
                        same = false
                        break
                    }
                }
            }
            same
        }

        else -> false
    }

    fun getParameterType(): Type {
        return Types.newParameterizedType(ViewList::class.java, dataType)
    }
}

class IRecyclerDataDiffCallback : DiffUtil.ItemCallback<IRecyclerData>() {
    override fun areItemsTheSame(oldItem: IRecyclerData, newItem: IRecyclerData): Boolean {
        return newItem.areItemsTheSame(oldItem)
    }

    override fun areContentsTheSame(oldItem: IRecyclerData, newItem: IRecyclerData): Boolean {
        return newItem.areContentsTheSame(oldItem)
    }
}