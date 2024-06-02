package com.example.parking.data.repository

import com.example.parking.data.model.Parking.BodyAddParking
import com.example.parking.data.model.Parking.BodyUpdateParking
import com.example.parking.data.remote.response.Parking.AddParkingResponse
import com.example.parking.data.remote.response.Parking.GetParkingOwnerResponse
import com.example.parking.data.remote.response.Parking.GetDetailParkingResponse
import com.example.parking.data.remote.response.Parking.UpdateParkingResponse
import com.example.parking.data.remote.retrofit.ApiService
import com.example.parking.data.util.retrofitErrorHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ParkingRepository
private constructor(
    private val apiService: ApiService
){
    suspend fun addParking(bodyParking: BodyAddParking): Flow<AddParkingResponse> {
        try {
            return flowOf(retrofitErrorHandler(apiService.addParking(bodyParking)))
        } catch (e:Exception){
            throw e
        }
    }

    suspend fun updateParking(id:String,bodyUpdateParking: BodyUpdateParking): Flow<UpdateParkingResponse> {
        try {
            return flowOf(retrofitErrorHandler(apiService.updateParking(id,bodyUpdateParking)))
        } catch (e:Exception){
            throw e
        }
    }

    suspend fun getDetailParking(id: String): Flow<GetDetailParkingResponse> {
        try {
            return flowOf(retrofitErrorHandler(apiService.getParkingById(id)))
        } catch (e:Exception){
            throw e
        }
    }

    suspend fun getParkingByOwner(ownerId: String): Flow<GetParkingOwnerResponse> {
        try {
            return flowOf(retrofitErrorHandler(apiService.getParkingByOwner(ownerId)))
        } catch (e:Exception){
            throw e
        }
    }

    companion object {
        @Volatile
        private var instance: ParkingRepository? = null
        fun getInstance(
            apiService: ApiService
        ): ParkingRepository =
            instance ?: synchronized(this) {
                ParkingRepository(apiService).apply {
                    instance = this
                }
            }
    }
}