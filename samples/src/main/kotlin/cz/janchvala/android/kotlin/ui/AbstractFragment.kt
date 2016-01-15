package cz.janchvala.android.kotlin.ui

import com.trello.rxlifecycle.components.support.RxFragment
import cz.janchvala.android.kotlin.KotlinApp

public abstract class AbstractFragment : RxFragment() {

    override fun onDestroy() {
        super.onDestroy()
        KotlinApp.refWatcher(activity).watch(this)
    }
}
