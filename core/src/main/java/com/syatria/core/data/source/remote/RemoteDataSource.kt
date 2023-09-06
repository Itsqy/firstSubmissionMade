package com.syatria.core.data.source.remote

import android.util.Log
import com.syatria.core.data.source.remote.network.ApiResponse
import com.syatria.core.data.source.remote.network.ApiService
import com.syatria.core.data.source.remote.response.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllUsers(): Flow<ApiResponse<List<UserResponse>>> {
        return flow {
            try {
                val response = apiService.getUsers()
                val dataArray = response.places
                if (dataArray.isNotEmpty()) {
                    emit(
                        ApiResponse.Success(response.places)
                    )
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("failed", "failed Catch data from internet ")
            }
        }.flowOn(Dispatchers.IO)
    }
}