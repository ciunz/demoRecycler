package sen.com.abstraction.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by korneliussendy on 2019-05-21,
 * at 01:04.
 * TestCermati
 */
abstract class BaseViewHolder<I>(var v: View) : RecyclerView.ViewHolder(v) {

    abstract fun bind(item: I)
}