package com.syatria.like

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.syatria.core.domain.usecase.UserUseCase

class FavViewModel(userUseCase: UserUseCase) : ViewModel() {

    val dataFavUser = userUseCase.getFavoriteUser().asLiveData()
}