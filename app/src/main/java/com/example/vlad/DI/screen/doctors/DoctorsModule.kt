package com.example.vlad.DI.screen.doctors

import com.example.vlad.data.doctors.DoctorsRepositoryImp
import com.example.vlad.db.DbHelper
import com.example.vlad.domain.doctors.DoctorsInteractorImp
import com.example.vlad.presentation.fragment.doctors.presenter.DoctorsPresenter
import dagger.Module
import dagger.Provides

@Module
class DoctorsModule {
    @Provides
    fun provideDoctorsPresenter(doctorsInteractor: DoctorsInteractorImp): DoctorsPresenter = DoctorsPresenter(doctorsInteractor)
    @Provides
    fun provideDoctorsInteractor(doctorsRepository: DoctorsRepositoryImp): DoctorsInteractorImp = DoctorsInteractorImp(doctorsRepository)
    @Provides
    fun provideDoctorsRepository(dbHelper: DbHelper):DoctorsRepositoryImp = DoctorsRepositoryImp(dbHelper)

}