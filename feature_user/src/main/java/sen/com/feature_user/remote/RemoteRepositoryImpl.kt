package sen.com.feature_user.remote

import io.reactivex.Single
import sen.com.feature_user.data.UserServices
import sen.com.feature_user.model.GitHubResponse
import sen.com.feature_user.model.User
import javax.inject.Inject

/**
 * Created by korneliussendy on 2019-05-20,
 * at 22:30.
 * TestCermati
 */
class RemoteRepositoryImpl @Inject constructor(private val service: UserServices) : RemoteRepository {

    override fun getGithubUsers(): Single<List<User>> = service.getGithubUsers()

    override fun searchGithubUsers(query: String, page: Int, perPage: Int): Single<GitHubResponse<User>> =
        service.searchGithubUsers(query, page, perPage);

}