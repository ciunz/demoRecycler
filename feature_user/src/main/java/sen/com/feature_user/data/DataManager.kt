package sen.com.feature_user.data

import io.reactivex.Single
import sen.com.feature_user.model.GitHubResponse
import sen.com.feature_user.model.User
import sen.com.feature_user.remote.RemoteRepository
import javax.inject.Inject

/**
 * Created by korneliussendy on 2019-05-20,
 * at 23:05.
 * TestCermati
 */
class DataManager @Inject constructor(private val remote: RemoteRepository) {

    fun getGithubRepo(query: String, page: Int, perPage: Int): Single<GitHubResponse<User>> =
        remote.searchGithubUsers(query, page, perPage)
}