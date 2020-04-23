package non.shahad.stayhomegallery

import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import non.shahad.stayhomegallery.di.components.DaggerAppComponent
import timber.log.Timber

class LouvreApplication() : DaggerApplication(){

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Stetho.initializeWithDefaults(this);
    }

    private val appComponent = DaggerAppComponent
        .factory().create(this)

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = appComponent

}