package com.syatria.myapplication.di

import com.syatria.core.domain.usecase.UserInteractor
import com.syatria.core.domain.usecase.UserUseCase
import com.syatria.myapplication.detail.DetailViewModel

import com.syatria.myapplication.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<UserUseCase> { UserInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }

}