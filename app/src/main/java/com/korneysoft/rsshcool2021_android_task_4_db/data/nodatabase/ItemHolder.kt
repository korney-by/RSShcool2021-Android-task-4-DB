package com.korneysoft.rsshcool2021_android_task_4_db.data.nodatabase

import android.content.res.Resources
import androidx.recyclerview.widget.RecyclerView
import com.korneysoft.rsshcool2021_android_task_4_db.databinding.ItemBinding
import com.korneysoft.rsshcool2021_android_task_4_db.data.ItemEssence
import com.korneysoft.rsshcool2021_android_task_4_db.data.ItemHolderInterface


class ItemHolder(
    private val binding: ItemBinding,
    private val resources: Resources
) :
    RecyclerView.ViewHolder(binding.root), ItemHolderInterface {

    private lateinit var _item: ItemEssence
    override val item get()=_item


    fun bind(itemEssence: ItemEssence) {
        this._item = itemEssence

        binding.apply {
            name.text = itemEssence.name
            age.text = itemEssence.age.toString()
            breed.text = itemEssence.breed
        }
    }



}