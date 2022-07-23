package jp.co.yumemi.android.code_check.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jp.co.yumemi.android.code_check.domain.datasource.*

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun bindsGitHubApiDataSource(): GitHubApiDataSource {
        return GitHubApiDataSource()
    }

    @Provides
    fun bindsStringDataSource(@ApplicationContext context: Context): StringDataSource {
        return StringDataSource(context = context)
    }
}