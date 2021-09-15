package com.korneysoft.rsshcool2021_android_task_4_db.ui

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.korneysoft.rsshcool2021_android_task_4_db.databinding.ItemBinding
import com.korneysoft.rsshcool2021_android_task_4_db.data.Item


class ItemHolder(
    private val binding: ItemBinding,
    private val parent: View
) : RecyclerView.ViewHolder(binding.root) {

    var v: View? = null

    init{
        this.v = parent
    }

    private lateinit var _item: Item
    val item get() = _item

    fun bind(itemEssence: Item) {
        this._item = itemEssence

        binding.apply {
            name.text = itemEssence.name
            age.text = itemEssence.age.toString()
            breed.text = itemEssence.breed
        }


    }
}