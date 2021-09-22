package com.korneysoft.rsshcool2021_android_task_4_db.ui

import android.graphics.PorterDuff
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.korneysoft.rsshcool2021_android_task_4_db.R
import com.korneysoft.rsshcool2021_android_task_4_db.data.Item
import com.korneysoft.rsshcool2021_android_task_4_db.databinding.ItemBinding

class ItemHolder(
    private val binding: ItemBinding,
    val parent: View
) : RecyclerView.ViewHolder(binding.root) {

    private var _item: Item? = null
    private var isSelected = false

    val item: Item? get() = _item

    fun bind(item: Item, isSelected: Boolean) {
        this._item = item
        this.isSelected = isSelected

        setBackgroundHolder()
        binding.apply {
            name.text = item.name
            age.text = item.age.toString()
            breed.text = item.breed
        }
    }

    private fun setBackgroundHolder() {
        if (isSelected) {
            val typedValue = TypedValue()
            parent.context.theme.resolveAttribute(R.attr.colorPrimary, typedValue, true)
            val color = typedValue.data
            binding.cardView.background.setColorFilter(color, PorterDuff.Mode.MULTIPLY)
        } else {
            binding.cardView.background.clearColorFilter()
        }
    }
}