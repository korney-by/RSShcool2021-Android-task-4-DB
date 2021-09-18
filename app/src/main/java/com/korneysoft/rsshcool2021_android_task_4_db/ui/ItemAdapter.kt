package com.korneysoft.rsshcool2021_android_task_4_db.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.korneysoft.rsshcool2021_android_task_4_db.data.Item
import com.korneysoft.rsshcool2021_android_task_4_db.databinding.ItemBinding


class ItemAdapter(
    val fragment: Fragment,
    private val listenerSelectItem: (Item) -> Unit
) :
    ListAdapter<Item, ItemHolder>(itemComparator) {

    private var itemsCount: Int = 0
    private val mFragment: ItemListFragment = fragment as ItemListFragment
    private val selectedItem: Item? get() = mFragment.selectedItem
    private var selectedHolder: ItemHolder? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(layoutInflater, parent, false)

        return ItemHolder(binding, binding.root)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, item == selectedItem)

        setupLongClickListener(holder)
    }

    private fun setupLongClickListener(holder: ItemHolder) {
        holder.parent.setOnLongClickListener()
        {
            Log.d("T4 - ","selectedHolder=$selectedHolder ,  holder=$holder")
            selectedHolder = holder
            listenerSelectItem(holder.item)
            holder.bind(holder.item, holder.item == selectedItem)
            setupDeselectListener(holder)
            true
        }
    }

    private fun setupDeselectListener(holder: ItemHolder) {
        mFragment.onDeselectItem = {
            if (selectedHolder?.item == selectedItem) {
                selectedHolder?.bind(holder.item, false)
            }
            selectedHolder=null
        }
    }

    override fun getItemCount(): Int {
        return itemsCount
        //return items.size
    }

    fun redrawSelectedItem() {
        val position = getCurrentList().indexOf(selectedItem)
        notifyItemChanged(position)
    }

    fun update(items: List<Item>) {
        itemsCount = items.size
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