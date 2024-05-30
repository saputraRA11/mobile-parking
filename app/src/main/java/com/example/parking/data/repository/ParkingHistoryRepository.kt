package com.example.parking.data.repository

import com.example.parking.data.model.ParkingHistory.BodyCreateHistory
import com.example.parking.data.model.ParkingHistory.BodyUpdateHistory
import com.example.parking.data.model.ParkingHistory.QueryAggregateHistory
import com.example.parking.data.remote.response.ParkingHistory.CreateHistoryResponse
import com.example.parking.data.remote.response.ParkingHistory.GetDetailHistoryResponse
import com.example.parking.data.remote.response.ParkingHistory.GetHistoryResponse
import com.example.parking.data.remote.response.ParkingHistory.UpdateHistoryResponse
import com.example.parking.data.remote.retrofit.ApiService
import com.example.parking.data.util.retrofitErrorHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ParkingHistoryRepository
private constructor(
    private val apiService: ApiService
){
    suspend fun createHistory(bodyCreateHistory: BodyCreateHistory): Flow<CreateHistoryResponse> {
        try {
            return flowOf(retrofitErrorHandler(apiService.createHistory(bodyCreateHistory)))
        } catch (e:Exception){
            throw e
        }
    }

    suspend fun updateHistory(id:String,bodyUpdateHistory: BodyUpdateHistory): Flow<UpdateHistoryResponse> {
        try {
            return flowOf(retrofitErrorHandler(apiService.updateHistory(id,bodyUpdateHistory)))
        } catch (e:Exception){
            throw e
        }
    }

    suspend fun detailHistory(id: String): Flow<GetDetailHistoryResponse> {
        try {
            return flowOf(retrofitErrorHandler(apiService.getHistoryById(id)))
        } catch (e:Exception){
            throw e
        }
    }

    suspend fun aggregateHistory(queryAggregateHistory: QueryAggregateHistory): Flow<GetHistoryResponse> {
        try {
            return flowOf(retrofitErrorHandler(apiService.aggregateHistory(queryAggregateHistory)))
        } catch (e:Exception){
            throw e
        }
    }

    companion object {
        @Volatile
        private var instance: ParkingHistoryRepository? = null
        fun getInstance(
            apiService: ApiService
        ): ParkingHistoryRepository =
            instance ?: synchronized(this) {
                ParkingHistoryRepository(apiService).apply {
                    instance = this
                }
            }
    }
}