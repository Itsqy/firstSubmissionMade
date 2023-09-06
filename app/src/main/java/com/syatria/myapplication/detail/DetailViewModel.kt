package com.syatria.myapplication.detail

import androidx.lifecycle.ViewModel
import com.syatria.core.domain.model.User
import com.syatria.core.domain.usecase.UserUseCase

class DetailViewModel(private val userUseCase: UserUseCase) : ViewModel() {
    fun setFavUser(user: User, state: Boolean) {
        return userUseCase.setFavoriteUser(user, state)
    }
}