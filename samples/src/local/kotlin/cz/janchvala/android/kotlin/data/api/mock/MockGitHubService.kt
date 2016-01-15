package cz.janchvala.android.kotlin.data.api.mock

import cz.janchvala.android.kotlin.data.api.GitHubService
import cz.janchvala.android.kotlin.data.api.dto.Repo
import rx.Observable

class MockGitHubService : GitHubService {

    override fun listRepos(user: String): Observable<List<Repo>> =
            Observable.just(MockGitHub.MOCK_REPO_LIST)
}
