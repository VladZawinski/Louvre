package non.shahad.stayhomegallery

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import non.shahad.stayhomegallery.di.components.DaggerAppComponent
import timber.log.Timber

class LouvreApplication() : DaggerApplication(){

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

    }

    private val appComponent = DaggerAppComponent
        .builder()
        .application(this)
        .build()

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return appComponent
    }
}