package com.syatria.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.syatria.core.data.source.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {

    @Query("SELECT * from usertable")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Query("Select * from usertable where isFavorite = 1")
    fun getFavUsers(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(user: List<UserEntity>)

    @Update
    fun updateFavoriteTourism(user: UserEntity)
}