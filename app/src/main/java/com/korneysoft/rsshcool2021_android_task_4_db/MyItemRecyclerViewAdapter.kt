package com.korneysoft.rsshcool2021_android_task_4_db

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater

import android.view.ViewGroup
import android.widget.TextView
import com.korneysoft.rsshcool2021_android_task_4_db.databinding.ItemBinding
import com.korneysoft.rsshcool2021_android_task_4_db.placeholder.PlaceholderContent

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyItemRecyclerViewAdapter(
    private val values: List<PlaceholderContent.PlaceholderItem>
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.name.text = item.name
        holder.age.text = item.age
        holder.breed.text = item.breed
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.name
        val age: TextView = binding.age
        val breed: TextView = binding.breed

        override fun toString(): String {
            return super.toString() + " '" + name.text + "'"
        }
    }

}