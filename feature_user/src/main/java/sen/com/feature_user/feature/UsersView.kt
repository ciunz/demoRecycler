package sen.com.feature_user.feature

import sen.com.abstraction.base.BaseView
import sen.com.feature_user.model.User

/**
 * Created by korneliussendy on 2019-05-20,
 * at 22:39.
 * TestCermati
 */
interface UsersView : BaseView {
    fun showLoadingUser()
    fun hideLoadingUser()
    fun failedLoadUser(e: Throwable)
    fun successLoadUser(users: List<User>?)
    fun clearData()
    fun notifyNoMoreResults(hasMore: Boolean)
    fun emptyQueryError(throwable: Throwable)
}