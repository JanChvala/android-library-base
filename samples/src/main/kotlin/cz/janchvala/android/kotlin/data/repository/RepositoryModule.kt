package cz.janchvala.android.kotlin.data.repository

import cz.janchvala.android.kotlin.AppScope
import cz.janchvala.android.kotlin.data.api.GitHubService
import dagger.Module
import dagger.Provides

@Module
public class RepositoryModule {

    @Provides
    @AppScope
    fun provideGitHubRepository(gitHubService: GitHubService): GitHubRepository =
            GitHubRepository(gitHubService)
}
