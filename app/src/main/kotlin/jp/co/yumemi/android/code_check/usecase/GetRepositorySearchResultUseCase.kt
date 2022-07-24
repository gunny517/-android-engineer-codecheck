package jp.co.yumemi.android.code_check.usecase

import jp.co.yumemi.android.code_check.domain.model.Item
import jp.co.yumemi.android.code_check.domain.repository.GitHubApiRepository
import javax.inject.Inject

class GetRepositorySearchResultUseCase @Inject constructor(
    private val gitHubApiRepository: GitHubApiRepository
) {

    operator fun invoke(query: String, callback: (Result<List<Item>>) -> Unit) {
        return gitHubApiRepository.getRepositorySearchResult(
            query = query,
            callback = callback,
        )
    }

}