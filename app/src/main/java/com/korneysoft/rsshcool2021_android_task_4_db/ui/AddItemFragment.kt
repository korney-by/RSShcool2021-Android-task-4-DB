package com.korneysoft.rsshcool2021_android_task_4_db.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.korneysoft.rsshcool2021_android_task_4_db.databinding.FragmentAddItemBinding
import com.korneysoft.rsshcool2021_android_task_4_db.viewmodel.ItemListViewModel

class AddFragment : Fragment() {
    private var _binding: FragmentAddItemBinding? = null
    private val binding get() = _binding!!

    private val itemListViewModel: ItemListViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddItemBinding.inflate(inflater, container, false)
        val view = binding.root


        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = AddFragment()
    }
}