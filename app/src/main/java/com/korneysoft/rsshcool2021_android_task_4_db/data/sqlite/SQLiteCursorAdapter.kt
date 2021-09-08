package com.korneysoft.rsshcool2021_android_task_4_db.data.sqlite

import android.content.Context
import android.database.Cursor
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.androidessence.recyclerviewcursoradapter.RecyclerViewCursorAdapter
import com.korneysoft.rsshcool2021_android_task_4_db.data.ItemEssence
import com.korneysoft.rsshcool2021_android_task_4_db.databinding.ItemBinding

private const val TAG ="T4-SQLiteCursorAdapter"

class SQLiteCursorAdapter(val db: SQLiteCursorAdapterInterface, context: Context, cursor: Cursor) :
    RecyclerViewCursorAdapter<ItemCursorHolder>(context) {

    init{
        setupCursorAdapter(cursor,0,android.R.layout.simple_list_item_1,false)

    }

    fun update(){
        Log.d(TAG,"update")
        swapCursor(db.getCursor())

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemCursorHolder {

//        val view = mCursorAdapter.newView(parent.getContext(), mCursorAdapter.getCursor(), parent);
//        return new ViewHolder(view);

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(layoutInflater, parent, false)

        return ItemCursorHolder(binding, binding.root.context.resources,db)
    }

    override fun onBindViewHolder(holder: ItemCursorHolder, position: Int) {
        val cursor = mCursorAdapter.getItem(position) as Cursor
        holder.bindCursor(cursor)
        Log.d(TAG,"onBindViewHolder - position: $position")
    }

  // подключение  DiffUtils  https://habr.com/ru/post/469557/

}