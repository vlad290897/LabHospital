package com.example.vlad.DI.screen.addcard

import com.example.vlad.data.addcard.AddCardRepositoryImp
import com.example.vlad.db.DbHelper
import com.example.vlad.domain.addcard.AddCardInteractorImp
import com.example.vlad.network.FirebaseAuthentication
import com.example.vlad.presentation.fragment.addcard.presenter.AddCardPresenter
import dagger.Module
import dagger.Provides

@Module
class AddCardModule {
    @Provides
    fun provideAddCardPresenter(addCardInteractorImp: AddCardInteractorImp):AddCardPresenter = AddCardPresenter(addCardInteractorImp)
    @Provides
    fun provideAddCardInteractor(addCardRepositoryImp: AddCardRepositoryImp):AddCardInteractorImp = AddCardInteractorImp(addCardRepositoryImp)
    @Provides
    fun provideAddCardRepository(dbHelper: DbHelper,firebaseAuthentication: FirebaseAuthentication):AddCardRepositoryImp = AddCardRepositoryImp(firebaseAuthentication,dbHelper)
}