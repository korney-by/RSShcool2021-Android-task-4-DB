package com.korneysoft.rsshcool2021_android_task_4_db.data.sqlite

import android.content.res.Resources
import android.database.Cursor
import android.util.Log
import com.androidessence.recyclerviewcursoradapter.RecyclerViewCursorViewHolder
import com.korneysoft.rsshcool2021_android_task_4_db.databinding.ItemBinding
import com.korneysoft.rsshcool2021_android_task_4_db.data.ItemEssence
import com.korneysoft.rsshcool2021_android_task_4_db.data.ItemHolderInterface

private const val TAG="T4-ItemCursorHolder"

class ItemCursorHolder(
    private val binding: ItemBinding,
    private val resources: Resources,
    val db: SQLiteCursorAdapterInterface
) :
    RecyclerViewCursorViewHolder(binding.root), ItemHolderInterface {

    private lateinit var _item: ItemEssence
    override val item get()=_item

    override fun bindCursor(cursor: Cursor?) {
        cursor?.let { it ->

            _item = db.getItem(it)
            Log.d(TAG,"bindCursor - id: ${_item.id}")
            binding.apply {
                name.text = _item.name
                age.text = _item.age.toString()
                breed.text = _item.breed
            }
        }
    }
}