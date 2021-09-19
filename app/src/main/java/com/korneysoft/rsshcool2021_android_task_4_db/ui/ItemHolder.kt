package com.korneysoft.rsshcool2021_android_task_4_db.ui

import android.graphics.PorterDuff
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.korneysoft.rsshcool2021_android_task_4_db.R
import com.korneysoft.rsshcool2021_android_task_4_db.databinding.ItemBinding
import com.korneysoft.rsshcool2021_android_task_4_db.data.Item
import android.util.TypedValue

class ItemHolder(
    private val binding: ItemBinding,
    val parent: View
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var _item: Item
    private var isSelected = false

    val item get() = _item

    fun bind(item: Item, isSelected:Boolean) {
        this._item = item
        this.isSelected=isSelected

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
            val color=typedValue.data
            binding.cardView.background.setColorFilter(color, PorterDuff.Mode.MULTIPLY)
        }else{
            binding.cardView.background.clearColorFilter()
        }



    }


}

