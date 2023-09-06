package com.syatria.like

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favModule = module {
    viewModel { FavViewModel(get()) }
}