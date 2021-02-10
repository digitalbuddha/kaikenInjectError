package com.example.kaikeninjecterror

import com.dropbox.kaiken.runtime.InjectorFactory
import com.dropbox.kaiken.scoping.DependencyProviderResolver
import com.squareup.anvil.annotations.ContributesTo
import dagger.Component
import javax.inject.Scope

@Scope
internal annotation class LaunchScope

@Component(
    dependencies = [LaunchDependencies::class]
)
@LaunchScope
interface LaunchComponent : LaunchActivityInjector, LaunchDependencies {
    @Component.Factory
    interface Factory {
        fun create(
            dependencies: LaunchDependencies
        ): LaunchComponent
    }
}

@ContributesTo(AUserScope::class)
interface LaunchDependencies {
    fun getLifecycleLogger(): LifecycleLogger
}


internal fun DependencyProviderResolver.injector() =
    object : InjectorFactory<LaunchComponent> {
        override fun createInjector() = DaggerLaunchComponent.factory().create(resolveDependencyProvider())
    }
