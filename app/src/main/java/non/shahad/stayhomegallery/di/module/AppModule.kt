package non.shahad.stayhomegallery.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideContext(application: Application) = application.applicationContext
}