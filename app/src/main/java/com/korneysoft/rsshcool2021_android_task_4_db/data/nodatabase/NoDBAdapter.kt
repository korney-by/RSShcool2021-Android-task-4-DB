package com.korneysoft.rsshcool2021_android_task_4_db.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.korneysoft.rsshcool2021_android_task_4_db.data.Item
import com.korneysoft.rsshcool2021_android_task_4_db.data.nodatabase.NoDBItemHolder
import com.korneysoft.rsshcool2021_android_task_4_db.databinding.ItemBinding
import com.korneysoft.rsshcool2021_android_task_4_db.data.nodatabase.NoDBAdapterInterface

class NoDBAdapter(val db: NoDBAdapterInterface) :
    ListAdapter<Item, NoDBItemHolder>(itemComparator){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoDBItemHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(layoutInflater, parent, false)

        return NoDBItemHolder(binding, binding.root.context.resources)
    }

    override fun onBindViewHolder(holderNoDB: NoDBItemHolder, position: Int) {
        val itemEssence =getItem(position)
        holderNoDB.bind(itemEssence)
    }

    override fun getItemCount(): Int {
        return db.getItemCount()
    }

    fun update(){
       submitList(db.getItemList().toList())
   }

    private companion object {

        private val itemComparator = object : DiffUtil.ItemCallback<Item>() {

            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.equals(newItem)

            }

//            override fun getChangePayload(oldItem: ItemEssence, newItem: ItemEssence): Any {
//               return ullloldItem.getChanges(newItem)
//            }
        }
    }


}