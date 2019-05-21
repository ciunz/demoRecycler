package sen.com.feature_user.model

/**
 * Created by korneliussendy on 2019-05-20,
 * at 22:27.
 * TestCermati
 */
class User {
    var login = ""
    var id = ""
    var avatar_url = ""

    override fun toString(): String = "$login $id"
}