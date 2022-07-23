package jp.co.yumemi.android.code_check.ui.item_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.co.yumemi.android.code_check.domain.model.Item
import javax.inject.Inject

@HiltViewModel
class ItemDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val item: Item = savedStateHandle.get<Item>("item") ?: throw IllegalArgumentException("should have Item.")

    val name: MutableLiveData<String> = MutableLiveData(item.name)

    val language: MutableLiveData<String> = MutableLiveData(item.language)

    val stargazersCount: MutableLiveData<Long> = MutableLiveData(item.stargazersCount)

    val watchersCount: MutableLiveData<Long> = MutableLiveData(item.watchersCount)

    val forksCount: MutableLiveData<Long> = MutableLiveData(item.forksCount)

    val openIssuesCount: MutableLiveData<Long> = MutableLiveData(item.openIssuesCount)

}