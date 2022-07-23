package jp.co.yumemi.android.code_check.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import jp.co.yumemi.android.code_check.domain.datasource.GitHubApiDataSource
import jp.co.yumemi.android.code_check.domain.repository.GitHubApiRepository

@Module
@InstallIn(FragmentComponent::class)
object RepositoryModule {

    @Provides
    fun bindsGitHubRepository(gitHubApiDataSource: GitHubApiDataSource): GitHubApiRepository {
        return GitHubApiRepository(gitHubApiDataSource)
    }
}