package com.syatria.core.utils

import com.syatria.core.data.source.local.entity.UserEntity
import com.syatria.core.data.source.remote.response.UserResponse
import com.syatria.core.domain.model.User

object DataMapper {
    fun mapResponsesToEntities(input: List<UserResponse>): List<UserEntity> {
        val userList = ArrayList<UserEntity>()
        input.map {
            val user = UserEntity(
                userId = it.id,
                name = it.name,
                status = it.status,
                avatarUrl = it.image,
                isFavorite = false

            )
            userList.add(user)
        }
        return userList
    }

    fun mapEntitiesToDomain(input: List<UserEntity>): List<User> =
        input.map {
            User(
                it.userId,
                it.name,
                it.status,
                it.avatarUrl,
                it.isFavorite
            )
        }

    fun mapDomainToEntity(input: User) = UserEntity(
        userId = input.userId,
        name = input.name,
        status = input.status,
        avatarUrl = input.imageUser,
        isFavorite = input.isFavorite

    )
}