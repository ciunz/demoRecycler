package sen.com.abstraction.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by korneliussendy on 2019-05-20,
 * at 17:03.
 * TestCermati
 */
open class BasePresenter<V : BaseView> : BasePresenterIntr<V> {

    protected lateinit var v: V
    protected var disposable = CompositeDisposable()

    override fun attachView(v: V?) {
        if (v != null)
            this.v = v
        else
            throw NullPointerException("presenter: please attach your view on onCreate() method.")
    }

    override fun detachView() {
        disposable.clear()
    }

    protected fun subscribe(job: () -> Disposable) {
        disposable.add(job())
    }

}