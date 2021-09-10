package com.korneysoft.rsshcool2021_android_task_4_db.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.korneysoft.rsshcool2021_android_task_4_db.data.ItemEssence
import com.korneysoft.rsshcool2021_android_task_4_db.data.ItemRepository
import com.korneysoft.rsshcool2021_android_task_4_db.databinding.FragmentAddItemBinding

class AddItemFragment : Fragment() {
    private var _binding: FragmentAddItemBinding? = null
    private val binding get() = _binding!!

    private var nameIsNotNull: Boolean = false
    private var ageIsNotNull: Boolean = false
    private var breedIsNotNull: Boolean = false

    //private val itemListViewModel: ItemListViewModel by activityViewModels()
    private val repository=ItemRepository.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddItemBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.addButton.setOnClickListener() {
            goAddItem()
            activity?.onBackPressed()
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

    private fun goAddItem() {
        repository.addItem(
            ItemEssence(
                0,
                binding.editTextName.text.toString(),
                binding.editTextAge.text.toString().toIntOrNull() ?: 0,
                binding.editTextBreed.text.toString()
            )
        )
    }

private fun onInfoChanged(){
    binding.addButton.isEnabled=(nameIsNotNull && ageIsNotNull && breedIsNotNull)
}

    companion object {
        @JvmStatic
        fun newInstance() = AddItemFragment()
    }
}