/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check.ui.item_detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import jp.co.yumemi.android.code_check.R
import jp.co.yumemi.android.code_check.TopActivity.Companion.lastSearchDate
import jp.co.yumemi.android.code_check.databinding.FragmentItemDetailBinding

class ItemDetailFragment : Fragment(R.layout.fragment_item_detail) {

    private val args: ItemDetailFragmentArgs by navArgs()

    private var _binding: FragmentItemDetailBinding? = null

    private val binding get() = _binding!!

    private val viewModel: ItemDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout(view)
    }

    private fun initLayout(view: View){
        Log.d("検索した日時", lastSearchDate.toString())

        _binding = FragmentItemDetailBinding.bind(view)
        binding.viewModel = viewModel
        val item = args.item
        binding.ownerIconView.load(item.ownerIconUrl);
    }
}
