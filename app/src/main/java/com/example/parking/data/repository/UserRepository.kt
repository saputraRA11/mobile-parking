package com.example.parking.data.repository

import com.example.parking.data.model.Auth.BodyRegister
import com.example.parking.data.model.WA.BodySendOtp
import com.example.parking.data.model.Auth.BodyValidation
import com.example.parking.data.model.User.BodyCreateUser
import com.example.parking.data.model.User.QueryGetUser
import com.example.parking.data.remote.response.WA.OtpResponse
import com.example.parking.data.remote.response.Auth.RegisterResponse
import com.example.parking.data.remote.response.Auth.ValidationResponse
import com.example.parking.data.remote.response.User.CreateUserResponse
import com.example.parking.data.remote.response.User.GetUserResponse
import com.example.parking.data.remote.retrofit.ApiService
import com.example.parking.data.util.retrofitErrorHandler
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.Flow

class UserRepository
private constructor(
    private val apiService: ApiService
){
    suspend fun registerUser(bodyRegister: BodyRegister):Flow<RegisterResponse>  {
        try {
            return flowOf(retrofitErrorHandler(apiService.registerUser(bodyRegister)))
        } catch (e:Exception){
            throw e
        }
    }

    suspend fun sendOtp(bodyOtp: BodySendOtp):Flow<OtpResponse>  {
        try {
            return flowOf(retrofitErrorHandler(apiService.sendOtp(bodyOtp)))
        } catch (e:Exception){
            throw e
        }
    }

    suspend fun validation(bodyValidation: BodyValidation):Flow<ValidationResponse>  {
        try {
            return flowOf(retrofitErrorHandler(apiService.validationUser(bodyValidation)))
        } catch (e:Exception){
            throw e
        }
    }

    suspend fun createUser(bodyCreateUser: BodyCreateUser):Flow<CreateUserResponse>  {
        try {
            return flowOf(retrofitErrorHandler(apiService.createUser(bodyCreateUser)))
        } catch (e:Exception){
            throw e
        }
    }
    suspend fun updateUser(phone:String,bodyUpdateUser: BodyCreateUser):Flow<CreateUserResponse>  {
        try {
            return flowOf(retrofitErrorHandler(apiService.updateUser(phone,bodyUpdateUser)))
        } catch (e:Exception){
            throw e
        }
    }

    suspend fun getUser(queryGetUser: QueryGetUser):Flow<GetUserResponse>  {
        try {
            return flowOf(retrofitErrorHandler(apiService.getUser(queryGetUser)))
        } catch (e:Exception){
            throw e
        }
    }
    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            apiService: ApiService
        ): UserRepository =
            instance ?: synchronized(this) {
                UserRepository(apiService).apply {
                    instance = this
                }
            }
    }

}