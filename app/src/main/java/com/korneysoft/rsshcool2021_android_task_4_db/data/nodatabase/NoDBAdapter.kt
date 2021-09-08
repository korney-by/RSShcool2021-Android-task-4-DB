package com.korneysoft.rsshcool2021_android_task_4_db.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.korneysoft.rsshcool2021_android_task_4_db.data.ItemEssence
import com.korneysoft.rsshcool2021_android_task_4_db.data.nodatabase.ItemHolder
import com.korneysoft.rsshcool2021_android_task_4_db.databinding.ItemBinding
import com.korneysoft.rsshcool2021_android_task_4_db.data.nodatabase.NoDBAdapterInterface

class NoDBAdapter(val db: NoDBAdapterInterface) :
    ListAdapter<ItemEssence, ItemHolder>(itemComparator){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(layoutInflater, parent, false)

        return ItemHolder(binding, binding.root.context.resources)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val itemEssence =getItem(position)
        holder.bind(itemEssence)
    }

    override fun getItemCount(): Int {
        return db.getItemCount()
    }

    fun update(){
       submitList(db.getItemList().toList())
   }

    private companion object {

        private val itemComparator = object : DiffUtil.ItemCallback<ItemEssence>() {

            override fun areItemsTheSame(oldItem: ItemEssence, newItem: ItemEssence): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ItemEssence, newItem: ItemEssence): Boolean {
                return oldItem.equals(newItem)

            }

//            override fun getChangePayload(oldItem: ItemEssence, newItem: ItemEssence): Any {
//               return ullloldItem.getChanges(newItem)
//            }
        }
    }


}