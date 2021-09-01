package com.korneysoft.rsshcool2021_android_task_4_db.viewmodel

import android.content.res.Resources
import androidx.recyclerview.widget.RecyclerView
import com.korneysoft.rsshcool2021_android_task_4_db.databinding.ItemBinding


class ItemHolder(
    private val binding: ItemBinding,
    private val resources: Resources
) :
    RecyclerView.ViewHolder(binding.root) {

    val item by lazy { this.itemEssence }

    private lateinit var itemEssence: ItemEssence

    fun bind(itemEssence: ItemEssence) {
        this.itemEssence = itemEssence

        binding.apply {
            name.text = itemEssence.name
            age.text = itemEssence.age.toString()
            breed.text = itemEssence.breed
        }
    }

}