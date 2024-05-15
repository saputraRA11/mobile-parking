package com.example.parking.data.repository

import com.example.parking.data.model.DummyUserSource
import com.example.parking.data.model.User
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.Flow

class UserRepository {
    fun getMyUsers(phone:String): Flow<User?> {

        return flowOf(DummyUserSource.dummyUser.find {it.phone == phone})
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(): UserRepository =
            instance ?: synchronized(this) {
                UserRepository().apply {
                    instance = this
                }
            }
    }

}