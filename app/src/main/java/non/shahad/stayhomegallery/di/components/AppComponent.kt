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
    AndroidSupportInjectionModule::class
])
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application : Application) : Builder

        fun build() : AppComponent
    }

    override fun inject(instance:  DaggerApplication)
}