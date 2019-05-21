package sen.com.feature_user.feature


import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import retrofit2.HttpException
import sen.com.abstraction.base.BasePresenter
import sen.com.feature_user.data.DataManager
import java.io.IOException


/**
 * Created by korneliussendy on 2019-05-20,
 * at 22:45.
 * TestCermati
 */
class UsersPresenter(private val dataManager: DataManager) : BasePresenter<UsersView>() {
    private var page = 1
    private val perPage = 20

    private var query = ""
    private var hasMore = true
    private var total = 0
    private var loading = false
    private var error = true

    fun searchGithubUsers() = searchGithubUsers(query)

    fun searchGithubUsers(query: String) {
        if (loading || !hasMore || error) return

        if (query.isEmpty()) {
            v.clearData()
            v.emptyQueryError(Throwable("Query cannot empty"))
            error = true
            return
        }

        this.query = query
        subscribe {
            dataManager.getGithubRepo(query, page, perPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    v.showLoadingUser()
                    loading = true
                }
                .subscribe({ response ->
                    v.hideLoadingUser()
                    total += response.items.size
                    hasMore = total >= page * perPage
                    page++
                    v.successLoadUser(response.items)
                    v.notifyNoMoreResults(hasMore)
                    loading = false
                }, {
                    if (it is HttpException) handleError(it)
                    else v.failedLoadUser(it)
                    v.hideLoadingUser()
                    v.log(it)
                    error = true
                    loading = false
                })
        }
    }

    fun tryAgain() {
        error = false
        searchGithubUsers(query)
    }

    fun handleError(ex: HttpException) {
        try {
            val jsonString = ex.response().errorBody()?.string()
            val err = JSONObject(jsonString)
            v.log(jsonString)
            val errMsg = "${ex.code()} : ${err.getString("message") ?: ex.message()}"
            v.failedLoadUser(Throwable(errMsg))
        } catch (e: IOException) {
            v.failedLoadUser(ex)
        }
    }

    fun loadNew(query: String) {
        hasMore = true
        page = 1
        total = 0
        loading = false
        error = false
        v.clearData()
        searchGithubUsers(query)
    }


}