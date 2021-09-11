package com.korneysoft.rsshcool2021_android_task_4_db.data.nodatabase

import android.content.res.Resources
import androidx.recyclerview.widget.RecyclerView
import com.korneysoft.rsshcool2021_android_task_4_db.databinding.ItemBinding
import com.korneysoft.rsshcool2021_android_task_4_db.data.Item


class NoDBItemHolder(
    private val binding: ItemBinding,
    private val resources: Resources
) :
    RecyclerView.ViewHolder(binding.root) {

    private lateinit var _item: Item
    val item get()=_item


    fun bind(item: Item) {
        this._item = item

        binding.apply {
            name.text = item.name
            age.text = item.age.toString()
            breed.text = item.breed
        }
    }



}