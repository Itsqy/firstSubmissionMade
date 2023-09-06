package com.syatria.myapplication.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.syatria.core.domain.usecase.UserUseCase

class HomeViewModel(userUseCase: UserUseCase) : ViewModel() {
    val user = userUseCase.getAllUser().asLiveData()

}