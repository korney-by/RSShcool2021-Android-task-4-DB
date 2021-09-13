package com.korneysoft.rsshcool2021_android_task_4_db.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.korneysoft.rsshcool2021_android_task_4_db.R
import com.korneysoft.rsshcool2021_android_task_4_db.data.Item
import com.korneysoft.rsshcool2021_android_task_4_db.databinding.FragmentAddItemBinding
import com.korneysoft.rsshcool2021_android_task_4_db.ui.interfaces.ToolbarUpdateInterface
import com.korneysoft.rsshcool2021_android_task_4_db.viewmodel.ItemViewModel

class AddItemFragment : Fragment() {
    private var _binding: FragmentAddItemBinding? = null
    private val binding get() = _binding!!

    private val fragmentName by lazy { getString(R.string.add_item_fragment) }

    private var nameIsNotNull: Boolean = false
    private var ageIsNotNull: Boolean = false
    private var breedIsNotNull: Boolean = false

    private val itemListViewModel: ItemViewModel by activityViewModels()
    //private val repository=ItemRepository.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddItemBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.addFloatingButton.setOnClickListener() {
            initializeAddItem()
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
    }

    private fun setToolbar() {
        activity.let {
            if (it is ToolbarUpdateInterface) {
                it.apply {
                    setToolbarTitle(fragmentName)
                    setToolbarHamburgerButton(R.drawable.ic_baseline_arrow_back_24, { closeFragment() })
                    setToolBarMenu(0, emptyArray())
                }
            }
        }
    }


    private fun initializeAddItem() {
        itemListViewModel.addItem(CreateNewItem())
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
        binding.addFloatingButton.isEnabled = (nameIsNotNull && ageIsNotNull && breedIsNotNull)
    }

    companion object {
        @JvmStatic
        fun newInstance() = AddItemFragment()
    }
}