package com.example.parking.data.repository

import com.example.parking.data.model.Payment.BodyCreatePayment
import com.example.parking.data.remote.response.Payment.CreatePaymentResponse
import com.example.parking.data.remote.retrofit.ApiService
import com.example.parking.data.util.retrofitErrorHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class PaymentRepository
private constructor(
    private val apiService: ApiService
){
    suspend fun createPayment(bodyCreatePayment: BodyCreatePayment): Flow<CreatePaymentResponse> {
        try {
            return flowOf(retrofitErrorHandler(apiService.createPayment(bodyCreatePayment)))
        } catch (e:Exception){
            throw e
        }
    }
    companion object {
        @Volatile
        private var instance: PaymentRepository? = null
        fun getInstance(
            apiService: ApiService
        ): PaymentRepository =
            instance ?: synchronized(this) {
                PaymentRepository(apiService).apply {
                    instance = this
                }
            }
    }
}