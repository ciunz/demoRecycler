package sen.com.feature_user.feature

import kotlinx.android.synthetic.main.act_users.*
import sen.com.abstraction.base.BaseActivity
import sen.com.feature_user.R
import sen.com.feature_user.di.DaggerUsersComponent
import sen.com.feature_user.di.UsersModule
import sen.com.feature_user.feature.adapters.UsersAdapter
import sen.com.feature_user.model.User
import javax.inject.Inject
import android.view.inputmethod.EditorInfo
import sen.com.abstraction.*


/**
 * Created by korneliussendy on 2019-05-20,
 * at 22:49.
 * TestCermati
 */
class UsersActivity : BaseActivity(), UsersView {

    override fun contentView() = R.layout.act_users
    @Inject
    lateinit var adapter: UsersAdapter
    @Inject
    lateinit var presenter: UsersPresenter


    override fun initInjector() {
        DaggerUsersComponent.builder()
            .usersModule(UsersModule())
            .build()
            .inject(this)
    }

    override fun initView() {
        presenter.attachView(this)
        adapter = UsersAdapter()
        setupRecyclerView(rv, adapter, true)
        setupLoadMore()
        setupEditText()
//        presenter.searchGithubUsers()

    }

    private fun setupLoadMore() = rv.setupLoadMore { presenter.searchGithubUsers() }

    private fun setupEditText() = tilSearch
        .editText?.setOnEditorActionListener { v, actionId, event ->
        var handled = false
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            presenter.loadNew(tilSearch.editText?.text.toString())
            hideKeyboard()
            handled = true
        }
        handled
    }

    override fun emptyQueryError(throwable: Throwable) {
        enable(tilSearch)
        adapter.showError(throwable.localizedMessage, "Change") {
            etSearch.requestFocus()
            showKeyboard(etSearch)
        }
    }

    override fun showLoadingUser() {
        disable(tilSearch)
        adapter.hideError()
        adapter.showLoader()

    }

    override fun hideLoadingUser() {
        adapter.hideLoader()
    }

    override fun failedLoadUser(e: Throwable) {
        enable(tilSearch)
        adapter.showError(e.localizedMessage, "Try Again") { presenter.tryAgain() }
    }

    override fun successLoadUser(users: List<User>?) {
        enable(tilSearch)
        users?.let { adapter.addItems(it) }
    }

    override fun notifyNoMoreResults(hasMore: Boolean) {
        adapter.showError("End of Results")
    }

    override fun clearData() {
        adapter.clear()
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}