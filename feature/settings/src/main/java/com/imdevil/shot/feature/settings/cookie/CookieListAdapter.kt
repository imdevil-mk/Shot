package com.imdevil.shot.feature.settings.cookie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.imdevil.shot.core.model.data.Cookie
import com.imdevil.shot.feature.settings.databinding.ListItemCookieBinding

class CookieListAdapter : ListAdapter<Cookie, CookieHolder>(CookieDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CookieHolder {
        return CookieHolder(
            ListItemCookieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CookieHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class CookieHolder(private val binding: ListItemCookieBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(cookie: Cookie) {
        with(binding) {
            name.text = cookie.name
            value.text = cookie.value
            domain.text = cookie.domain
            path.text = cookie.path
            expiration.text = cookie.expirationDate
        }
    }
}

class CookieDiffCallback : DiffUtil.ItemCallback<Cookie>() {
    override fun areItemsTheSame(oldItem: Cookie, newItem: Cookie): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Cookie, newItem: Cookie): Boolean {
        return oldItem.name == newItem.name
                && oldItem.value == newItem.value
                && oldItem.domain == newItem.domain
                && oldItem.path == newItem.path
                && oldItem.expirationDate == newItem.expirationDate
    }

}