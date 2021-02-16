package com.example.kaikeninjecterror

import com.dropbox.kaiken.scoping.AppServices
import com.dropbox.kaiken.scoping.TeardownHelper
import com.dropbox.kaiken.scoping.UserServices
import com.squareup.anvil.annotations.ContributesTo
import com.squareup.anvil.annotations.MergeComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides

abstract class AUserScope
abstract class AAppScope

@AppScope
@MergeComponent(scope = AAppScope::class)
interface LaunchAppServices : AppServices {
    @Component.Factory
    interface Factory {
        fun create(): AppServices
    }
}

@Module
@ContributesTo(AAppScope::class)
class LaunchAppServicesModule {
    @Provides
    @AppScope
    fun provideTeardownHelper(): TeardownHelper = MyTeardownHelper()
    @Provides
    @AppScope
    fun provideLifecycleLogger() : LifecycleLogger = RealLifecycleLogger
}


@ContributesTo(AAppScope::class)
interface UserDependencies {
    fun getLifecycleLogger(): LifecycleLogger
}

@MergeComponent(
    scope = AUserScope::class,
    dependencies = [
        LaunchAppServices::class
    ]
)
@UserScope
interface LaunchUserServices : UserServices {
    @Component.Factory
    interface Factory {
        fun create(
            applicationServices: LaunchAppServices,
        ): LaunchUserServices
    }
}


@Module
@ContributesTo(AUserScope::class)
class UserServicesModule {
}

class MyTeardownHelper : TeardownHelper {
    override fun teardown() {
        // TODO(icamacho): Figure out teardown
    }
}
