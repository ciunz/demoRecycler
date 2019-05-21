package sen.com.feature_user.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import sen.com.feature_user.model.GitHubResponse
import sen.com.feature_user.model.User

/**
 * Created by korneliussendy on 2019-05-20,
 * at 22:25.
 * TestCermati
 */
interface UserServices {
    @GET("/users")
    fun getGithubUsers(): Single<List<User>>

    @GET("search/users")
    fun searchGithubUsers(
        @Query("q") query: String,
        @Query("page") page:Int,
        @Query("per_page") perPage:Int
    ): Single<GitHubResponse<User>>
}