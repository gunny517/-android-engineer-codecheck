/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check.ui.item_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import dagger.hilt.android.AndroidEntryPoint
import jp.co.yumemi.android.code_check.R
import jp.co.yumemi.android.code_check.databinding.FragmentItemIstBinding
import jp.co.yumemi.android.code_check.domain.model.Item

@AndroidEntryPoint
class ItemListFragment: Fragment(R.layout.fragment_item_ist) {

    private val viewModel: ItemListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout(view)
    }

    private fun initLayout(view: View){
        val binding = FragmentItemIstBinding.bind(view)
        val layoutManager = LinearLayoutManager(requireContext())
        val dividerItemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        val adapter = ItemListAdapter(object : ItemListAdapter.OnItemClickListener {
            override fun itemClick(item: Item){
                gotoItemDetailFragment(item)
            }
        })

        binding.viewModel = viewModel
        binding.searchInputText.setOnEditorActionListener(null)
        viewModel.searchResult.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        binding.recyclerView.also{
            it.layoutManager = layoutManager
            it.addItemDecoration(dividerItemDecoration)
            it.adapter= adapter
        }
    }

    fun gotoItemDetailFragment(item: Item) {
        val action = ItemListFragmentDirections.actionOneFragmentToItemDetailFragment(item = item)
        findNavController().navigate(action)
    }
}

