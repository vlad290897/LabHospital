package com.example.vlad.DI.screen.entries

import com.example.vlad.data.entries.EntriesRepositoryImp
import com.example.vlad.db.DbHelper
import com.example.vlad.domain.entries.EntriesInteractorImp
import com.example.vlad.presentation.fragment.entries.presenter.EntriesPresenter
import dagger.Module
import dagger.Provides

@Module
class EntriesModule {
    @Provides
    fun provideEntriesPresenter(interactorImp: EntriesInteractorImp): EntriesPresenter = EntriesPresenter(interactorImp)

    @Provides
    fun provideEntriesInteractor(repositoryImp: EntriesRepositoryImp): EntriesInteractorImp = EntriesInteractorImp(repositoryImp)

    @Provides
    fun provideEntriesRepository(dbHelper: DbHelper): EntriesRepositoryImp = EntriesRepositoryImp(dbHelper)
}