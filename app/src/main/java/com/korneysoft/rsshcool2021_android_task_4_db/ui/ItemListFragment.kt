package com.korneysoft.rsshcool2021_android_task_4_db.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.korneysoft.rsshcool2021_android_task_4_db.R
import com.korneysoft.rsshcool2021_android_task_4_db.data.Item
import com.korneysoft.rsshcool2021_android_task_4_db.databinding.FragmentItemListBinding
import com.korneysoft.rsshcool2021_android_task_4_db.ui.interfaces.KeyboardInterface
import com.korneysoft.rsshcool2021_android_task_4_db.ui.interfaces.ShowFragmentAddItemInterface
import com.korneysoft.rsshcool2021_android_task_4_db.ui.interfaces.ToolbarUpdateItnerface
import com.korneysoft.rsshcool2021_android_task_4_db.viewmodel.ItemViewModel

private const val TAG = "T4-ItemListFragment"

class ItemListFragment() : Fragment() {
    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!

    private val fragmentName by lazy { getString(R.string.base_name) }

    private val itemListViewModel: ItemViewModel by activityViewModels()
    private val itemAdapter: ItemAdapter = ItemAdapter()

    //    private val itemListViewModel: ItemListViewModel by lazy {
//        ViewModelProviders.of(requireActivity()).get(ItemListViewModel::class.java)
//    }
    //private val recyclerViewAdapter by lazy { itemListViewModel.db.adapter }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        val view = binding.root


        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = itemAdapter
        }

        connectSwipeToReciclerView()
        setupViewModelListeners()
        setupActionListeners()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerObservers()
    }

    override fun onResume() {
        super.onResume()
        val parentActivity = activity
        if (parentActivity is KeyboardInterface) {
            parentActivity.hideKeyboard()
        }
        if (parentActivity is ToolbarUpdateItnerface) {
            parentActivity.setToolBarSettings(fragmentName,R.menu.toolbar_menu_sort,R.drawable.ic_baseline_filter_alt_24, R.drawable.ic_baseline_menu_24, {})
        }
    }

    private fun registerObservers() {
        itemListViewModel.itemListLiveData.observe(
            viewLifecycleOwner,
            Observer { items ->
                items?.let {
                    Log.d(TAG, "${items.toString()}")
                    updateUI(items)
                }
            }
        )
    }

    private fun updateUI(items: List<Item>) {
        itemAdapter.update(items)
    }

    private fun setupActionListeners() {
        binding.addButton.setOnClickListener() {
            val showFragmentAddItemInterface = activity

            if (showFragmentAddItemInterface is ShowFragmentAddItemInterface) {
                showFragmentAddItemInterface.openAddItemFragment()
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupViewModelListeners() {
        //itemListViewModel.onChangeData = {}
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
                    if ((viewHolder is ItemHolder)) {
                        itemListViewModel.deleteItem(viewHolder.item)
                        registerObservers()
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