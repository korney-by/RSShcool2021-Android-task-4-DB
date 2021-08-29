package com.korneysoft.rsshcool2021_android_task_4_db

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.korneysoft.rsshcool2021_android_task_4_db.databinding.FragmentItemsListBinding
import com.korneysoft.rsshcool2021_android_task_4_db.placeholder.PlaceholderContent

/**
 * A fragment representing a list of Items.
 */
class AnimalsListFragment : Fragment() {
    private var _binding: FragmentItemsListBinding? = null
    private val binding get() = _binding!!

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemsListBinding.inflate(inflater, container, false)
        val view = binding.root

        // Set the adapter
        with(binding.recyclerView) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = MyItemRecyclerViewAdapter(PlaceholderContent.ITEMS)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            AnimalsListFragment().apply {
                arguments = bundleOf(
                    ARG_COLUMN_COUNT to columnCount
                )
            }
    }
}