package com.korneysoft.rsshcool2021_android_task_4_db.ui

import android.graphics.Color
import android.graphics.Color.WHITE
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.korneysoft.rsshcool2021_android_task_4_db.databinding.FragmentItemsListBinding
import com.korneysoft.rsshcool2021_android_task_4_db.data.nodatabase.ItemHolder
import com.korneysoft.rsshcool2021_android_task_4_db.data.sqlite.ItemCursorHolder
import com.korneysoft.rsshcool2021_android_task_4_db.viewmodel.ItemListViewModel
import android.graphics.drawable.GradientDrawable
import android.graphics.PorterDuffColorFilter







private const val TAG = "T4-ItemListFragment"

class ItemListFragment() : Fragment() {
    private var _binding: FragmentItemsListBinding? = null
    private val binding get() = _binding!!

    private val itemListViewModel: ItemListViewModel by activityViewModels()

    //    private val itemListViewModel: ItemListViewModel by lazy {
//        ViewModelProviders.of(requireActivity()).get(ItemListViewModel::class.java)
//    }
    private val recyclerViewAdapter by lazy { itemListViewModel.db.adapter }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        arguments?.let {
//        //TODO - delete if not needed
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemsListBinding.inflate(inflater, container, false)
        val view = binding.root

        // Set the adapter
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerViewAdapter
        }

        updateRecycleView()

        connectSwipeToReciclerView()
        setupDBListeners()

        binding.addButton.setOnClickListener() {
            AddFragment.newInstance()
            updateRecycleView()
        }
        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupDBListeners() {
        itemListViewModel.db.onDelete = { updateRecycleView() }
        itemListViewModel.db.onAdd = { updateRecycleView() }
    }

    private fun updateRecycleView() {
        //Log.d(TAG, "left ${itemListViewModel.db.getItemCount()}")
        recyclerViewAdapter. update()
    }


    private fun connectSwipeToReciclerView() {
        val itemTouchHelperCallback =
            object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {

                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    if (viewHolder is ItemHolder) {
                        itemListViewModel.db.delete(viewHolder.item)
                    }
                    if (viewHolder is ItemCursorHolder) {
                        itemListViewModel.db.delete(viewHolder.item)
                    }
                }

            }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }


    companion object {

        @JvmStatic
        fun newInstance() = ItemListFragment()
//                .apply {
        //TODO - delete if not needed
//                arguments = bundleOf(
//                    ARG_COLUMN_COUNT to columnCount
//                )
//            }
    }


}