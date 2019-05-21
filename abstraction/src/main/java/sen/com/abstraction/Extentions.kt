package sen.com.abstraction

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.DividerItemDecoration


/**
 * Created by korneliussendy on 2019-05-21,
 * at 11:24.
 * TestCermati
 */

fun AppCompatActivity.setupRecyclerView(rv: RecyclerView, adapter: Adapter<*>, divider: Boolean) {
    val manager = LinearLayoutManager(this)
    rv.layoutManager = manager
    rv.adapter = adapter
    if (divider) {
        rv.addItemDecoration(
            DividerItemDecoration(
                this,
                manager.orientation
            )
        )
    }
}

fun AppCompatActivity.setupRecyclerView(rv: RecyclerView, adapter: Adapter<*>) =
    setupRecyclerView(rv, adapter, false)

fun AppCompatActivity.visible(vararg views: View) = views.forEach { it.visibility + View.VISIBLE }
fun AppCompatActivity.gone(vararg views: View) = views.forEach { it.visibility + View.GONE }
fun AppCompatActivity.enable(vararg views: View) = views.forEach { it.isEnabled = true }
fun AppCompatActivity.disable(vararg views: View) = views.forEach { it.isEnabled = false }
fun AppCompatActivity.showKeyboard(et: EditText) {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(et, InputMethodManager.SHOW_IMPLICIT)
}

fun RecyclerView.setupLoadMore(onLoadMode: () -> Unit) = setupLoadMore(4, onLoadMode)

fun RecyclerView.setupLoadMore(threshold: Int, onLoadMode: () -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val manager = layoutManager as LinearLayoutManager
            super.onScrolled(recyclerView, dx, dy)
            val totalItemCount = manager.itemCount
            val lastVisibleItem = manager.findLastVisibleItemPosition()
            if (totalItemCount <= lastVisibleItem + threshold) {
                post { onLoadMode() }
            }
        }
    })
}
