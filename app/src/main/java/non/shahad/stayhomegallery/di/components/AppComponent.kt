package non.shahad.stayhomegallery.di.components

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import non.shahad.stayhomegallery.LouvreApplication
import non.shahad.stayhomegallery.di.module.*
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    NetworkModule::class,
    ActivityModule::class,
    PersistenceModule::class,
    StoreModule::class,
    AndroidSupportInjectionModule::class
])
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Factory
    interface Factory{
        fun create (@BindsInstance application : Application) : AppComponent
    }
}