package com.example.kaikeninjecterror

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dropbox.kaiken.annotation.Injectable
import com.dropbox.kaiken.runtime.InjectorFactory
import com.dropbox.kaiken.runtime.InjectorHolder
import com.dropbox.kaiken.scoping.AuthRequiredActivity
import javax.inject.Inject

@Injectable
class LaunchActivity : AppCompatActivity(), AuthRequiredActivity, InjectorHolder<LaunchComponent> {
    @Inject
    lateinit var lifecycleLogger: LifecycleLogger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (finishIfInvalidAuth()) {
            return
        }

        inject()

        setContentView(R.layout.activity_main)

    }

    override fun getInjectorFactory(): InjectorFactory<LaunchComponent> = injector()
}