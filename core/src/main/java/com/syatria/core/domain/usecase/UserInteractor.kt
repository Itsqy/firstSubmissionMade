package com.syatria.core.domain.usecase

import com.syatria.core.data.Resource
import com.syatria.core.domain.model.User
import com.syatria.core.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow

class UserInteractor(private val userRepository: IUserRepository) : UserUseCase {
    override fun getAllUser(): Flow<Resource<List<User>>> {
        return userRepository.getAllUsers()
    }

    override fun getFavoriteUser(): Flow<List<User>> {
        return userRepository.getFavUsers()
    }

    override fun setFavoriteUser(user: User, state: Boolean) {
        return userRepository.setFavUser(user, state)
    }
}