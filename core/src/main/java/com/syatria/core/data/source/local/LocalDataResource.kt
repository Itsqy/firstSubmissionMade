package com.syatria.core.data.source.local

import com.syatria.core.data.source.local.entity.UserEntity
import com.syatria.core.data.source.local.room.UserDao
import kotlinx.coroutines.flow.Flow

class LocalDataResource(private val userDao: UserDao) {

    fun getAllUsers(): Flow<List<UserEntity>> = userDao.getAllUsers()

    fun getFavUsers(): Flow<List<UserEntity>> = userDao.getFavUsers()

    fun insertUsers(userList: List<UserEntity>) = userDao.insertUsers(userList)

    fun setFavUser(users: UserEntity, state: Boolean) {
        users.isFavorite = state
        userDao.updateFavoriteTourism(users)
    }
}