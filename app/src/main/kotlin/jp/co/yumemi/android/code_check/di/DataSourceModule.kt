package jp.co.yumemi.android.code_check.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.co.yumemi.android.code_check.domain.datasource.GitHubApiDataSource

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun bindsGitHubApiDataSource(): GitHubApiDataSource {
        return GitHubApiDataSource()
    }
}