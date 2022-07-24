package jp.co.yumemi.android.code_check.domain.repository

import jp.co.yumemi.android.code_check.domain.datasource.GitHubApiDataSource
import jp.co.yumemi.android.code_check.domain.model.Item
import javax.inject.Inject

class GitHubApiRepository @Inject constructor(
    private val gitHubApiDataSource: GitHubApiDataSource
) {

    fun getRepositorySearchResult(query: String, callback: (Result<List<Item>>) -> Unit) {
        return gitHubApiDataSource.getRepositorySearchResult(
            query = query,
            callback = callback,
        )
    }
}