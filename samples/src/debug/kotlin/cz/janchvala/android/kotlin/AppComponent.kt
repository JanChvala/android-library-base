package cz.janchvala.android.kotlin

import dagger.Component
import cz.janchvala.android.kotlin.data.DebugDataModule

@AppScope
@Component(modules = arrayOf(DebugAppModule::class, DebugDataModule::class))
public interface AppComponent : MainAppComponent {

    fun inject(app: KotlinApp)

    object Initializer {
        fun init(app: KotlinApp): AppComponent =
                DaggerAppComponent.builder()
                        .debugAppModule(DebugAppModule(app))
                        .debugDataModule(DebugDataModule())
                        .build()
    }
}
