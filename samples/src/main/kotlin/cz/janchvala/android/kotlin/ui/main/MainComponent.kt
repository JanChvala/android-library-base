package cz.janchvala.android.kotlin.ui.main

import cz.janchvala.android.kotlin.AppComponent
import cz.janchvala.android.kotlin.KotlinApp
import dagger.Component

@MainScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(MainModule::class))
public interface MainComponent {

    public fun inject(activity: MainActivity)

    public fun inject(fragment: MainFragment)

    public object Initializer {

        public fun init(activity: MainActivity): MainComponent {
            return DaggerMainComponent.builder()
                    .appComponent(KotlinApp.appComponent(activity))
                    .mainModule(MainModule())
                    .build()
        }
    }
}
