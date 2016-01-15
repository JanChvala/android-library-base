package cz.janchvala.android.kotlin

import android.app.Application
import cz.janchvala.android.kotlin.data.repository.GitHubRepository

public interface MainAppComponent {

    public fun application(): Application

    public fun gitHubRepository(): GitHubRepository

}
