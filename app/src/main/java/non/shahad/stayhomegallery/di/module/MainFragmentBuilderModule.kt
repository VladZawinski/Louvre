package non.shahad.stayhomegallery.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import non.shahad.stayhomegallery.ui.collections.CollectionsFragment
import non.shahad.stayhomegallery.ui.explore.ExploreFragment
import non.shahad.stayhomegallery.ui.favorite.FavoriteFragment
import non.shahad.stayhomegallery.ui.home.HomeFragment

@Module
abstract class MainFragmentBuilderModule{

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeExploreFragment(): ExploreFragment

    @ContributesAndroidInjector
    abstract fun contributeCollectionsFragment(): CollectionsFragment

    @ContributesAndroidInjector
    abstract fun contributeFavoriteFragment(): FavoriteFragment
}