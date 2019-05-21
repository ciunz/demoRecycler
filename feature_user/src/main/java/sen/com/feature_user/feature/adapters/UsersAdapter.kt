package sen.com.feature_user.feature.adapters

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.cell_item_user.view.*
import sen.com.abstraction.base.BaseAdapter
import sen.com.abstraction.base.BaseViewHolder
import sen.com.feature_user.R
import sen.com.feature_user.model.User

/**
 * Created by korneliussendy on 2019-05-21,
 * at 01:31.
 * TestCermati
 */
class UsersAdapter : BaseAdapter<User, UsersAdapter.UserViewHolder>(R.layout.cell_item_user) {

    override fun createView(view: View, viewType: Int): UserViewHolder {
        return UserViewHolder(view)
    }

    override fun onBindView(holder: UserViewHolder, item: User) {
        holder.bind(item)
    }


    class UserViewHolder(v: View) : BaseViewHolder<User>(v) {
        override fun bind(item: User) {
            Glide.with(v)
                .setDefaultRequestOptions(
                    RequestOptions
                        .placeholderOf(R.drawable.ic_placeholder)
                        .error(R.drawable.ic_image_error)
                )
                .load(item.avatar_url)
                .into(v.ivImage)
            v.tvName.text = item.login
        }
    }
}