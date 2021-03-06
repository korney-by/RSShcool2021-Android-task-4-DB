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
import com.korneysoft.rsshcool2021_android_task_4_db.ui.interfaces.KeyboardControlInterface
import com.korneysoft.rsshcool2021_android_task_4_db.ui.interfaces.SetPreferencesInterface
import com.korneysoft.rsshcool2021_android_task_4_db.ui.interfaces.ShowFragmentAddItemInterface
import com.korneysoft.rsshcool2021_android_task_4_db.ui.interfaces.ToolbarUpdateInterface
import com.korneysoft.rsshcool2021_android_task_4_db.viewmodel.ItemViewModel

private const val TAG = "T4-ItemListFragment"

class ItemListFragment : Fragment() {
    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!

    private val fragmentName by lazy { getString(R.string.base_name) }

    private var selectedItem: Item? = null
        set(value) {
            if (field == value) return
            if (field != null) onDeselectItem(field)
            field = value
        }

    private val viewModel: ItemViewModel by activityViewModels()
    private val itemAdapter: ItemAdapter =
        ItemAdapter({ getSelectedItem() }, { onLongClickItem(it) })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = itemAdapter
        }

        connectSwipeToRecyclerView()
        setupActionListeners()

        return view
    }

    private fun getSelectedItem() = selectedItem

    private fun onDeselectItem(item: Item?) {
        item?.let { item ->
            itemAdapter.onDeselectItem(item)
        }
    }

    private fun toolbarClose() {
        selectedItem = null
        binding.motionActionToolbar.transitionToStart()
    }

    private fun toolbarShow() {
        binding.actionToolbar.title = selectedItem?.name
        binding.motionActionToolbar.transitionToEnd()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        applyPreferences()
        registerObservers()
        setToolbar()
        selectedItem = null
    }

    private fun setToolbar() {
        var iconSort = 0
        var actionButtonChangeSortOrder: () -> Unit = {}

        if (viewModel.isSorted) {
            actionButtonChangeSortOrder = { onClickButtonChangeSortOrder() }
            iconSort = if (viewModel.sortIsDesc) R.drawable.ic_baseline_sort_desc_24
            else R.drawable.ic_baseline_sort_asc_24
        }

        activity?.let { activity ->
            if (activity is ToolbarUpdateInterface) {
                activity.apply {
                    setToolbarTitle(fragmentName, viewModel.daoTypeName)
                    setToolbarHamburgerButton(0) {}
                    setToolBarMenu(R.menu.toolbar_menu_sort,
                        iconSort,
                        arrayOf({ actionButtonChangeSortOrder() },
                            { openSettingsFragment() }
                        ))
                }
            }
        }
    }

    private fun onClickButtonChangeSortOrder() {
        viewModel.changeSortOrder()
        setToolbar()
    }

    override fun onResume() {
        super.onResume()
        activity?.let {
            if (it is KeyboardControlInterface) {
                it.hideKeyboard()
            }
        }
    }

    private fun onLongClickItem(item: Item) {
        if (selectedItem == null) {
            selectedItem = item
            toolbarShow()
        }
    }

    private fun applyPreferences() {
        activity?.let {
            if (it is SetPreferencesInterface) {
                it.setPreferences()
            }
        }
    }

    private fun registerObservers() {
        viewModel.itemListLiveData.observe(
            viewLifecycleOwner,
            Observer { items ->
                items?.let {
                    Log.d(TAG, "registerObservers")
                    updateUI(items)
                }
            }
        )
    }

    private fun updateUI(items: List<Item>) {
        itemAdapter.update(items)
    }

    private fun setupActionListeners() {
        setupAddButtonListener()
        setupActionToolbarListeners()
    }

    private fun setupActionToolbarListeners() {
        binding.actionToolbar.let { toolbar ->

            toolbar.setNavigationOnClickListener { toolbarClose() }
            toolbar.menu.findItem(R.id.item_delete).setOnMenuItemClickListener {
                selectedItem?.let { item -> viewModel.deleteItem(item) }
                toolbarClose()
                true
            }

            toolbar.menu.findItem(R.id.item_edit).setOnMenuItemClickListener {
                selectedItem?.let {
                    showItemDetails(it)
                }
                toolbarClose()
                true
            }
        }
    }

    private fun showItemDetails(item: Item?) {
        activity?.let {
            if (it is ShowFragmentAddItemInterface) {
                it.openItemDetailsFragment(item)
            }
        }
    }

    private fun setupAddButtonListener() {
        binding.addFloatingButton.setOnClickListener {
            // toolbarClose()
            showItemDetails(null)
        }
    }

    private fun openSettingsFragment() {
        activity?.let {
            if (it is ShowFragmentAddItemInterface) {
                it.openSettingsFragment()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun connectSwipeToRecyclerView() {
        val itemTouchHelperCallback =
            object :
                ItemTouchHelper.SimpleCallback(
                    0,
                    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                ) {

                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    if ((viewHolder is ItemHolder)) {
                        viewHolder.item?.let { item -> viewModel.deleteItem(item) }
                    }
                }
            }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }

    companion object {
        @JvmStatic
        fun newInstance() = ItemListFragment()
    }
}