package com.korneysoft.rsshcool2021_android_task_4_db.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.korneysoft.rsshcool2021_android_task_4_db.R
import com.korneysoft.rsshcool2021_android_task_4_db.data.Item
import com.korneysoft.rsshcool2021_android_task_4_db.databinding.FragmentItemDetailsBinding
import com.korneysoft.rsshcool2021_android_task_4_db.ui.interfaces.ToolbarUpdateInterface
import com.korneysoft.rsshcool2021_android_task_4_db.viewmodel.ItemViewModel

class ItemDetailsFragment : Fragment() {
    private var _binding: FragmentItemDetailsBinding? = null
    private val binding get() = _binding!!

    private val fragmentName by lazy {
        if (editItem == null) {
            getString(R.string.add_item_fragment)
        } else {
            getString(R.string.edit_item_fragment)
        }
    }

    private var nameIsNotNull: Boolean = false
    private var ageIsNotNull: Boolean = false
    private var breedIsNotNull: Boolean = false
    private var editItem: Item? = null

    private val itemListViewModel: ItemViewModel by activityViewModels()
    //private val repository=ItemRepository.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editItem = getItemFromBundle(arguments)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemDetailsBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.applyButton.setOnClickListener() {
            if (editItem == null) {
                initializeAddItem()
            } else {
                initializeUpdateItem()
            }
            closeFragment()
        }

        binding.editTextName.doOnTextChanged { inputText, _, _, _ ->
            nameIsNotNull = inputText?.isNotEmpty() ?: false
            onInfoChanged()
        }
        binding.editTextAge.doOnTextChanged { inputText, _, _, _ ->
            ageIsNotNull = inputText?.isNotEmpty() ?: false
            onInfoChanged()
        }
        binding.editTextBreed.doOnTextChanged { inputText, _, _, _ ->
            breedIsNotNull = inputText?.isNotEmpty() ?: false
            onInfoChanged()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
        showEditValues(editItem)
    }

    private fun showEditValues(item: Item?) { item?.let { it ->
            binding.editTextName.setText(it.name)
            binding.editTextAge.setText(it.age.toString())
            binding.editTextBreed.setText(it.breed)
            binding.applyButton.text = getString(R.string.apply_button_text)
        }
    }

    private fun getItemFromBundle(arguments: Bundle?): Item? {
        if (arguments != null) {
            val id = arguments.getInt(ARG_ID)
            val name = arguments.getString(ARG_NAME) ?: ""
            val age = arguments.getInt(ARG_AGE)
            val breed = arguments.getString(ARG_BREED) ?: ""
            return Item(id, name, age, breed)
        } else return null
    }

    private fun setToolbar() {
        activity.let {
            if (it is ToolbarUpdateInterface) {
                it.apply {
                    setToolbarTitle(fragmentName)
                    setToolbarHamburgerButton(
                        R.drawable.ic_baseline_arrow_back_24,
                        { closeFragment() })
                    setToolBarMenu(0,0, emptyArray())
                }
            }
        }
    }


    private fun initializeAddItem() {
        itemListViewModel.addItem(CreateNewItem())
    }

    private fun initializeUpdateItem() {
        editItem?.let {
            it.name = binding.editTextName.text.toString()
            it.age = binding.editTextAge.text.toString().toInt()
            it.breed = binding.editTextBreed.text.toString()
            Log.d("T4-ItemDetailsFragment","  $it")
            itemListViewModel.updateItem(it)

        }
    }

    private fun closeFragment() {
        activity?.onBackPressed()
    }

    private fun CreateNewItem(): Item {
        return Item(
            0,
            binding.editTextName.text.toString(),
            binding.editTextAge.text.toString().toIntOrNull() ?: 0,
            binding.editTextBreed.text.toString()
        )
    }


    private fun onInfoChanged() {
        binding.applyButton.isEnabled = (nameIsNotNull && ageIsNotNull && breedIsNotNull)
    }

    companion object {
        const val ARG_ID = "ARG_ID"
        const val ARG_NAME = "ARG_NAME"
        const val ARG_AGE = "ARG_AGE"
        const val ARG_BREED = "ARG_BREED"

        @JvmStatic
        fun newInstance(item: Item?): ItemDetailsFragment {
            if (item != null) {
                return ItemDetailsFragment().apply {
                    this.arguments =
                        bundleOf(
                            ARG_ID to item.id,
                            ARG_NAME to item.name,
                            ARG_AGE to item.age,
                            ARG_BREED to item.breed
                        )
                }
            } else return ItemDetailsFragment()
        }
    }
}