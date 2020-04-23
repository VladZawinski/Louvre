package non.shahad.stayhomegallery.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import non.shahad.stayhomegallery.di.scope.ActivityScope
import non.shahad.stayhomegallery.ui.detail.DetailActivity
import non.shahad.stayhomegallery.ui.main.MainActivity

@Module(includes = [
    ViewModelModule::class
])
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainFragmentBuilderModule::class])
    internal abstract fun mainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector()
    internal abstract fun detailActivity(): DetailActivity
}