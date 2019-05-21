package sen.com.abstraction.base

/**
 * Created by korneliussendy on 2019-05-20,
 * at 17:03.
 * TestCermati
 */
interface BasePresenterIntr<V: BaseView> {
    fun attachView(v:V?)
    fun detachView()
}