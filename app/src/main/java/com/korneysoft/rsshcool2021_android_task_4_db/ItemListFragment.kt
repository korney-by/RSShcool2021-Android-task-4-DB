package com.korneysoft.rsshcool2021_android_task_4_db

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.korneysoft.rsshcool2021_android_task_4_db.databinding.FragmentItemsListBinding
import com.korneysoft.rsshcool2021_android_task_4_db.databinding.ItemBinding
import com.korneysoft.rsshcool2021_android_task_4_db.viewmodel.ItemEssence
import com.korneysoft.rsshcool2021_android_task_4_db.viewmodel.ItemListViewModel

private const val TAG = "T4-ItemListFragment"

class ItemListFragment : Fragment() {
    private var _binding: FragmentItemsListBinding? = null
    private val binding get() = _binding!!

    //private val itemListViewModel= ViewModelProviders.of(this).get(ItemListViewModel::class.java)

    private val itemListViewModel: ItemListViewModel by lazy {
        ViewModelProviders.of(this).get(ItemListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        arguments?.let {
//        //TODO - delete if not needed
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemsListBinding.inflate(inflater, container, false)
        val view = binding.root

        // Set the adapter
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ItemAdapter(itemListViewModel.ITEMS)
        }


        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance() =ItemListFragment()
//                .apply {
                //TODO - delete if not needed
//                arguments = bundleOf(
//                    ARG_COLUMN_COUNT to columnCount
//                )
//            }
    }

    private inner class ItemHolder(
        private val binding: ItemBinding,
        private val resources: Resources
    ) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var itemEssence: ItemEssence

        fun bind(itemEssence: ItemEssence) {
            this.itemEssence = itemEssence

            binding.apply {
                name.text = itemEssence.name
                age.text = itemEssence.age.toString()
                breed.text = itemEssence.breed
            }
        }

    }

    private inner class ItemAdapter(var items: List<ItemEssence>) :
        RecyclerView.Adapter<ItemHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
            val view = layoutInflater.inflate(R.layout.item, parent, false)

            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemBinding.inflate(layoutInflater, parent, false)
            return ItemHolder(binding, binding.root.context.resources)


        }

        override fun onBindViewHolder(holder: ItemHolder, position: Int) {
            val itemEssence = itemListViewModel.ITEMS[position]
            holder.bind(itemEssence)
        }

        override fun getItemCount(): Int {
            return itemListViewModel.ITEMS.size
        }

    }

}