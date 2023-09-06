package com.syatria.core.data

import com.syatria.core.utils.AppExecutors
import com.syatria.core.utils.DataMapper
import com.syatria.core.data.source.local.LocalDataResource
import com.syatria.core.data.source.remote.RemoteDataSource
import com.syatria.core.data.source.remote.network.ApiResponse
import com.syatria.core.data.source.remote.response.UserResponse
import com.syatria.core.domain.model.User
import com.syatria.core.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataResource,
    private val appExecutors: AppExecutors
) : IUserRepository {
    override fun getAllUsers(): Flow<Resource<List<User>>> =
        object : NetworkBoundResource<List<User>, List<UserResponse>>() {
            override fun loadFromDB(): Flow<List<User>> {
                return localDataSource.getAllUsers().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<UserResponse>>> {
                return remoteDataSource.getAllUsers()
            }

            override suspend fun saveCallResult(data: List<UserResponse>) {
                val userList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertUsers(userList)
            }

            override fun shouldFetch(data: List<User>?): Boolean {
                return true
            }

        }.asFlow()

    override fun getFavUsers(): Flow<List<User>> {
        return localDataSource.getFavUsers().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavUser(user: User, state: Boolean) {
        val userEntity = DataMapper.mapDomainToEntity(user)
        appExecutors.diskIO().execute {
            localDataSource.setFavUser(userEntity, state)
        }
    }
}