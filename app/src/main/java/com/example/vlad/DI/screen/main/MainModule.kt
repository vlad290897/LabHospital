package com.example.vlad.DI.screen.main

import com.example.vlad.data.main.MainRepositoryImp
import com.example.vlad.db.DbHelper
import com.example.vlad.domain.main.MainInteractor
import com.example.vlad.domain.main.MainInteractorImp
import com.example.vlad.presentation.activity.main.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class MainModule {
    @Provides
    fun provideMainPresenter(mainInteractor: MainInteractorImp):MainPresenter = MainPresenter(mainInteractor)
    @Provides
    fun provideMainInteractor(repository:MainRepositoryImp):MainInteractorImp = MainInteractorImp(repository)
    @Provides
    fun provideMainRepository(dbHelper: DbHelper):MainRepositoryImp = MainRepositoryImp(dbHelper)
}