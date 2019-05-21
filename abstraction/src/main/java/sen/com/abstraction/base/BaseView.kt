package sen.com.abstraction.base

import androidx.annotation.StringRes

/**
 * Created by korneliussendy on 2019-05-20,
 * at 17:02.
 * TestCermati
 */
interface BaseView {
    fun onMessage(message: String?)
    fun onMessage(@StringRes stringResId: Int)

    fun isNetworkConnect(): Boolean

    fun hideKeyboard()

    fun log(message: Any?)

    fun log(message: Throwable)

}