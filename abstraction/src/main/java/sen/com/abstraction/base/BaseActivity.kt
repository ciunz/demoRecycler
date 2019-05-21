package sen.com.abstraction.base

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import sen.com.abstraction.BuildConfig
import sen.com.abstraction.utils.KeyboardUtils
import sen.com.abstraction.utils.NetworkUtils

/**
 * Created by korneliussendy on 2019-05-20,
 * at 17:01.
 * TestCermati
 */
abstract class BaseActivity : AppCompatActivity(), BaseView {

    /**
     * lifecycle method
     * @method contentView(): @return resLayoutId
     * @method initView()
     */
    @LayoutRes
    abstract fun contentView(): Int

    abstract fun initView()

    abstract fun initInjector()

    /**
     * (optional, use it if needed)
     */
    protected lateinit var savedInstanceState: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            this.savedInstanceState = savedInstanceState
        }
        setContentView(contentView())
        initInjector()
        initView()
    }

    override fun onMessage(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onMessage(stringResId: Int) {
        onMessage(getString(stringResId))
    }

    override fun isNetworkConnect(): Boolean {
        return NetworkUtils.connection(this)
    }

    override fun hideKeyboard() {
        return KeyboardUtils.hide(this)
    }

    override fun log(message: Any?) {
        if (BuildConfig.DEBUG)
            Log.d(this.localClassName, message?.toString() ?: "")
    }

    override fun log(message: Throwable) {
        if (BuildConfig.DEBUG)
            Log.e(this.localClassName, message.localizedMessage, message)
    }
}