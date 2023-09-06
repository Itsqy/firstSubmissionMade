package com.syatria.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.syatria.core.data.source.local.room.UserDao
import com.syatria.core.data.source.local.entity.UserEntity


@Database(entities = [UserEntity::class], version = 3, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}