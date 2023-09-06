package com.syatria.core.domain.usecase

import com.syatria.core.data.Resource
import com.syatria.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserUseCase {
    fun getAllUser(): Flow<Resource<List<User>>>

    fun getFavoriteUser(): Flow<List<User>>

    fun setFavoriteUser(user: User, state: Boolean)
}