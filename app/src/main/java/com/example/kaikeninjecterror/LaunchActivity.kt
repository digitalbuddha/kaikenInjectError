package com.example.kaikeninjecterror

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dropbox.kaiken.Injector
import com.dropbox.kaiken.annotation.DaggerInjectable
import com.dropbox.kaiken.runtime.InjectorFactory
import com.dropbox.kaiken.runtime.InjectorHolder
import com.dropbox.kaiken.scoping.AuthRequiredActivity
import com.dropbox.kaiken.scoping.DependencyProviderResolver
import com.squareup.anvil.annotations.ContributesTo
import com.squareup.anvil.annotations.MergeComponent
import dagger.Component
import javax.inject.Inject
import javax.inject.Scope

@ContributesTo(AUserScope::class)
interface LaunchDependencies {
    fun getLifecycleLogger(): LifecycleLogger
}
@DaggerInjectable(dependency = LaunchDependencies::class)
class LaunchActivity : AppCompatActivity(), AuthRequiredActivity,
    InjectorHolder<LaunchActivityInjector> {
    @Inject lateinit var lifecycleLogger: LifecycleLogger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
    }

    override fun getInjectorFactory(): InjectorFactory<LaunchActivityInjector> = injector()
}
//
//@Scope
//annotation class LaunchActivityScope
//
//class ALaunchActivityScope
//
//@LaunchActivityScope
//@MergeComponent(
//    scope = ALaunchActivityScope::class,
//    dependencies = [LaunchDependencies::class]
//)
//interface LaunchActivityComponent {
//    @Component.Factory
//    interface Factory {
//        fun create(LaunchDependencies: LaunchDependencies): LaunchActivityComponent
//    }
//}
//
//
//fun LaunchActivity.inject() {
//    val activityInjector: LaunchActivityInjector = this.locateInjector()
//    activityInjector.inject(this)
//}
//
//fun DependencyProviderResolver.injector(): InjectorFactory<LaunchActivityInjector> = object :
//    InjectorFactory<LaunchActivityInjector> {
//    override fun createInjector() =
//        DaggerLaunchActivityComponent.Factory().create(resolveDependencyProvider()) as
//                LaunchActivityInjector
//}
//
//
//@ContributesTo(scope = ALaunchActivityScope::class)
//interface LaunchActivityInjector : Injector {
//    fun inject(activity: LaunchActivity)
//}
