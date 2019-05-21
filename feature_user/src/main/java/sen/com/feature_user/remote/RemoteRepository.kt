package sen.com.feature_user.remote

import io.reactivex.Single
import sen.com.feature_user.model.GitHubResponse
import sen.com.feature_user.model.User

/**
 * Created by korneliussendy on 2019-05-20,
 * at 22:28.
 * TestCermati
 */
interface RemoteRepository {
    fun getGithubUsers(): Single<List<User>>
    fun searchGithubUsers(query: String, page: Int, perPage: Int): Single<GitHubResponse<User>>
}