package cz.janchvala.android.kotlin

import android.app.Application
import com.crashlytics.android.Crashlytics
import cz.janchvala.android.kotlin.util.CrashlyticsTree
import io.fabric.sdk.android.Fabric
import timber.log.Timber

public class ReleaseAppLifecycleCallbacks : AppLifecycleCallbacks {

    override fun onCreate(application: Application) {
        Fabric.with(application, Crashlytics())
        Timber.plant(CrashlyticsTree())
    }

    override fun onTerminate(application: Application) {

    }
}
