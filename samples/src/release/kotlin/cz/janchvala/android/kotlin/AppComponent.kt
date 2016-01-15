package cz.janchvala.android.kotlin

import cz.janchvala.android.kotlin.data.DataModule
import dagger.Component

@AppScope
@Component(modules = arrayOf(AppModule::class, DataModule::class))
public interface AppComponent : MainAppComponent {

    fun inject(app: KotlinApp)

    object Initializer {

        fun init(app: KotlinApp): AppComponent =
                DaggerAppComponent.builder()
                        .appModule(AppModule(app))
                        .dataModule(DataModule())
                        .build()
    }

}
