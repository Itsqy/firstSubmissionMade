package com.syatria.core.domain.repository

import com.syatria.core.data.Resource
import com.syatria.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IUserRepository {

    fun getAllUsers(): Flow<Resource<List<User>>>

    fun getFavUsers(): Flow<List<User>>

    fun setFavUser(user: User, state: Boolean)
}