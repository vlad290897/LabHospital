package com.example.vlad.DI.screen.auth

import android.content.Context
import com.example.vlad.data.authentification.AuthenticationRepositoryImp
import com.example.vlad.db.DbHelper
import com.example.vlad.domain.auth.AuthenticationInteractorImp
import com.example.vlad.network.FirebaseAuthentication
import com.example.vlad.presentation.activity.signin.SignInPresenter
import com.example.vlad.presentation.activity.signup.SignUpPresenter
import dagger.Module
import dagger.Provides

@Module
class AuthModule {
    @Provides
    fun provideSignInPresenter(authenticationInteractor: AuthenticationInteractorImp)=SignInPresenter(authenticationInteractor)
    @Provides
    fun provideSignUpPresenter(authenticationInteractor: AuthenticationInteractorImp): SignUpPresenter = SignUpPresenter(authenticationInteractor)

    @Provides
    fun provideAuthInteractor(repository: AuthenticationRepositoryImp, dbHelper: DbHelper): AuthenticationInteractorImp = AuthenticationInteractorImp(repository,dbHelper)
    @Provides
    fun provideAuthRepository(firebaseAuthentication: FirebaseAuthentication): AuthenticationRepositoryImp = AuthenticationRepositoryImp(firebaseAuthentication)

}