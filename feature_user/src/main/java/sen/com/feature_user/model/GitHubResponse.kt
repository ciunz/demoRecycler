package sen.com.feature_user.model

/**
 * Created by korneliussendy on 2019-05-21,
 * at 00:07.
 * TestCermati
 */
class GitHubResponse<T : Any> {
    var total_count = 0
    var incomplete_results = false
    var items = ArrayList<T>()

}