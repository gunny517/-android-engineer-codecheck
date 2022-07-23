package jp.co.yumemi.android.code_check.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import jp.co.yumemi.android.code_check.domain.repository.GitHubApiRepository
import jp.co.yumemi.android.code_check.usecase.GetRepositorySearchResultUseCase

@Module
@InstallIn(FragmentComponent::class)
object UseCaseModule {

    @Provides
    fun bindsGetRepositorySearchResultUseCase(gitHubApiRepository: GitHubApiRepository): GetRepositorySearchResultUseCase {
        return GetRepositorySearchResultUseCase(gitHubApiRepository)
    }
}