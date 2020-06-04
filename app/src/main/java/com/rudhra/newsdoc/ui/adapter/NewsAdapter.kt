package com.rudhra.newsdoc.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rudhra.newsdoc.databinding.ListItemNewsBinding
import com.rudhra.newsdoc.ui.model.NewsItem

class NewsAdapter(val listener: OnClickListener) :
    ListAdapter<NewsItem, NewsAdapter.ViewHolder>(NewsDiffCallback()) {

    var listItems: List<NewsItem>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemNewsBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listItems?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(listItems!![position])

    inner class ViewHolder(private val binding: ListItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NewsItem) {
            binding.item = item
            binding.executePendingBindings()
            binding.root.setOnClickListener { listener.onItemClick(listItems?.get(adapterPosition)?.url!!,ClickType.ITEM) }
            binding.shareImageCard.setOnClickListener { listener.onItemClick(listItems?.get(adapterPosition)?.url!!,ClickType.SHARE) }
        }
    }


    /** Once DiffUtil figures out what has changed, RecyclerView can use that information to update only
     * the items that were changed, added, removed, or moved, which is much more efficient than redoing the entire list.**/
    class NewsDiffCallback : DiffUtil.ItemCallback<NewsItem>() {

        override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            return oldItem.title == newItem.title
        }
    }

    interface OnClickListener {
        fun onItemClick(url: String,type:ClickType)
    }

    enum class ClickType{
        SHARE,ITEM
    }
}