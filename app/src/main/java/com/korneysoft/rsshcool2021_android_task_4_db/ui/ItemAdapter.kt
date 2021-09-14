package com.korneysoft.rsshcool2021_android_task_4_db.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.korneysoft.rsshcool2021_android_task_4_db.data.Item
import com.korneysoft.rsshcool2021_android_task_4_db.databinding.ItemBinding

class ItemAdapter() : ListAdapter<Item, ItemHolder>(itemComparator) {

    private var itemsCount: Int=0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(layoutInflater, parent, false)

        return ItemHolder(binding, binding.root.context.resources)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return itemsCount
        //return items.size
    }

    fun update(items: List<Item>) {
        itemsCount=items.size
        submitList(items)
    }

    companion object {

        private val itemComparator = object : DiffUtil.ItemCallback<Item>() {

            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
                oldItem == newItem

//            override fun getChangePayload(oldItem: ItemEssence, newItem: ItemEssence): Any {
//               return ullloldItem.getChanges(newItem)
//            }
        }
    }


}