package sen.com.feature_user.di

import dagger.Component
import sen.com.feature_user.feature.UsersActivity

/**
 * Created by korneliussendy on 2019-05-20,
 * at 23:09.
 * TestCermati
 */

@Component(modules = [UsersModule::class])
interface UsersComponent {
    fun inject(activity: UsersActivity)
}