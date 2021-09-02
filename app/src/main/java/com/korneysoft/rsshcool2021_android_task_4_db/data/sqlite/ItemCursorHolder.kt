package com.korneysoft.rsshcool2021_android_task_4_db.data.sqlite

import android.content.res.Resources
import android.database.Cursor
import android.util.Log
import com.androidessence.recyclerviewcursoradapter.RecyclerViewCursorViewHolder
import com.korneysoft.rsshcool2021_android_task_4_db.databinding.ItemBinding
import com.korneysoft.rsshcool2021_android_task_4_db.data.ItemEssence

private const val TAG="T4-ItemCursorHolder"

class ItemCursorHolder(
    private val binding: ItemBinding,
    private val resources: Resources,
    val db: SQLiteCursorAdapterInterface
) :
    RecyclerViewCursorViewHolder(binding.root) {

    lateinit var item: ItemEssence

    override fun bindCursor(cursor: Cursor?) {
        cursor?.let { it ->

            item = db.getItem(it)
            Log.d(TAG,"bindCursor - id: ${item.id}")
            binding.apply {
                name.text = item.name
                age.text = item.age.toString()
                breed.text = item.breed
            }
        }
    }
}