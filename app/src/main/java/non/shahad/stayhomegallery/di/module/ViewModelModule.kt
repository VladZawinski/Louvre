package non.shahad.stayhomegallery.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import non.shahad.stayhomegallery.di.ViewModelFactory
import non.shahad.stayhomegallery.di.ViewModelKey
import non.shahad.stayhomegallery.ui.collections.CollectionsViewModel
import non.shahad.stayhomegallery.ui.detail.DetailViewModel
import non.shahad.stayhomegallery.ui.explore.ExploreViewModel
import non.shahad.stayhomegallery.ui.favorite.FavoriteViewModel
import non.shahad.stayhomegallery.ui.home.HomeViewModel

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @IntoMap
    @Binds
    @ViewModelKey(HomeViewModel::class)
    abstract fun provideHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(FavoriteViewModel::class)
    abstract fun provideFavoriteViewModel(favViewModel: FavoriteViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(DetailViewModel::class)
    abstract fun provideDetailViewModel(detailViewModel: DetailViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(ExploreViewModel::class)
    abstract fun provideExploreViewModel(exploreViewModel: ExploreViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(CollectionsViewModel::class)
    abstract fun provideCollectionsViewModel(collectionsViewModel: CollectionsViewModel): ViewModel

}