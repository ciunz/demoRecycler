package sen.com.abstraction.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.simple_item_error.view.*
import kotlinx.android.synthetic.main.simple_item_loading.view.*
import sen.com.abstraction.R




/**
 * Created by korneliussendy on 2019-05-21,
 * at 00:16.
 * TestCermati
 */

abstract class BaseAdapter<I, VH : RecyclerView.ViewHolder>(@LayoutRes var cell: Int) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = ArrayList<I?>()
    private val TYPE_ERROR = -1
    private val TYPE_LOAD = 0
    private val TYPE_ITEM = 1

    private var msg: String? = null

    private var errorMsg: String? = null
    private var errorButtonText: String? = null
    private var error = false
    private var onErrorClick: (() -> Unit)? = null

    val onClick: ((v: View) -> Unit)? = null


    override fun getItemViewType(position: Int): Int {
        return if (items[position] == null) if (error) TYPE_ERROR else TYPE_LOAD else TYPE_ITEM
    }

    override fun getItemCount(): Int = items.size

    fun addItem(item: I?) {
        items.add(item)
        notifyItemInserted(itemCount - 1)
    }

    fun addItems(items: List<I>) {
        val count = itemCount
        this.items.addAll(items)
        notifyItemRangeInserted(count, itemCount - 1)
    }

    fun showLoader() {
        addItem(null)
        msg = "Loading"
    }

    fun hideLoader() {
        removeLast()
        msg = ""
    }

    fun showError() = showError("Error", "", null)

    fun showError(errorMessage: String?) = showError(errorMessage, "", null)

    fun showError(errorMessage: String?, btnText: String?, onErrorClick: (() -> Unit)?) {
        this.errorMsg = errorMessage
        this.onErrorClick = onErrorClick
        this.errorButtonText = btnText
        error = true
        addItem(null)
    }

    fun hideError() {
        if(!error) return
        this.errorMsg = ""
        error = false
//        if (items[itemCount - 1] == null)
            removeLast()
    }

    fun remove(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun removeLast() {
        if (itemCount > 0) {
            items.removeAt(itemCount - 1)
            notifyItemRemoved(itemCount)
        }
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val resLayout = when (viewType) {
            TYPE_ERROR -> R.layout.simple_item_error
            TYPE_LOAD -> R.layout.simple_item_loading
            else -> cell

        }
        val v = inflater.inflate(
            resLayout, parent, false
        )
        return when (viewType) {
            TYPE_ERROR -> ErrorViewHolder(v)
            TYPE_LOAD -> LoaderViewHolder(v)
            else -> createView(v, viewType)

        }
    }

    abstract fun createView(view: View, viewType: Int): VH

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LoaderViewHolder) {
            holder.bind(msg)
        } else if (holder is ErrorViewHolder) {
            holder.bind(errorMsg, errorButtonText, onErrorClick)
        } else {
            val item = items[position]
            if (item != null) onBindView(holder as VH, item)
        }
    }

    abstract fun onBindView(holder: VH, item: I)

    class LoaderViewHolder(private var v: View) : RecyclerView.ViewHolder(v) {
        fun bind(msg: String?) {
            v.tvInfo.visibility = if (msg.isNullOrEmpty()) View.GONE else View.VISIBLE
            v.tvInfo.text = msg
        }
    }

    class ErrorViewHolder(var v: View) : RecyclerView.ViewHolder(v) {
        fun bind(msg: String?, btnText: String?, onErrorClick: (() -> Unit)?) {
            v.tvInfoError.visibility = if (msg.isNullOrEmpty()) View.GONE else View.VISIBLE
            v.tvInfoError.text = msg
            v.btnAction.text = btnText
            if (onErrorClick != null) {
                v.btnAction.visibility = View.VISIBLE
                v.btnAction.setOnClickListener { onErrorClick() }
            } else {
                v.btnAction.visibility = View.GONE
            }
        }
    }
}