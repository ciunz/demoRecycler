package sen.com.feature_user.di

import dagger.Module
import dagger.Provides
import sen.com.feature_user.data.DataManager
import sen.com.feature_user.data.UserServices
import sen.com.feature_user.feature.UsersPresenter
import sen.com.feature_user.feature.adapters.UsersAdapter
import sen.com.feature_user.remote.RemoteRepository
import sen.com.feature_user.remote.RemoteRepositoryImpl
import sen.com.network.services

/**
 * Created by korneliussendy on 2019-05-20,
 * at 23:09.
 * TestCermati
 */
@Module
class UsersModule {
    @Provides
    fun provideUsersService(): UserServices = services()

    @Provides
    fun provideRemoteRepositoryImpl(services: UserServices): RemoteRepositoryImpl {
        return RemoteRepositoryImpl(services)
    }

    @Provides
    fun provideRemoteRepository(remoteRepositoryImpl: RemoteRepositoryImpl): RemoteRepository {
        return remoteRepositoryImpl
    }

    @Provides
    fun provideDataManager(remote: RemoteRepository): DataManager = DataManager(remote)

    @Provides
    fun provideUsersPresenter(manager: DataManager) = UsersPresenter(manager)

    @Provides
    fun provideUsersAdapter() = UsersAdapter()

}