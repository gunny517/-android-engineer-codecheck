/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check.ui.item_list

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.co.yumemi.android.code_check.domain.model.Item
import jp.co.yumemi.android.code_check.usecase.GetRepositorySearchResultUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * OneFragment で使う
 */
@HiltViewModel
class ItemListViewModel @Inject constructor(
    val getRepositorySearchResultUseCase: GetRepositorySearchResultUseCase,
) : ViewModel() {

    val searchResult: MutableLiveData<List<Item>> = MutableLiveData()

    fun execSearch(editText: TextView, action: Int, event: KeyEvent?): Boolean {
        if (action == EditorInfo.IME_ACTION_SEARCH){
            editText.text?.let { inputText ->
                viewModelScope.launch {
                    getRepositorySearchResultUseCase(
                        query = inputText.toString(),
                    ) { result ->
                        result.onSuccess {
                            searchResult.value = result.getOrNull()
                        }.onFailure {
                            // TODO
                        }
                    }
                }
            }
            return true
        }
        return false
    }
}
