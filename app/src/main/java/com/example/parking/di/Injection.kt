package com.example.parking.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.parking.data.preferences.SettingLocalStorage
import com.example.parking.data.remote.retrofit.ApiService
import com.example.parking.data.repository.ParkingHistoryRepository
import com.example.parking.data.repository.ParkingRepository
import com.example.parking.data.repository.PaymentRepository
import com.example.parking.data.repository.UserRepository

object Injection {
    fun userRepository(apiService: ApiService): UserRepository {
        return UserRepository.getInstance(apiService)
    }

    fun parkingRepository(apiService: ApiService): ParkingRepository {
        return ParkingRepository.getInstance(apiService)
    }

    fun paymentRepository(apiService: ApiService): PaymentRepository {
        return PaymentRepository.getInstance(apiService)
    }

    fun parkingHistoryRepository(apiService: ApiService): ParkingHistoryRepository {
        return ParkingHistoryRepository.getInstance(apiService)
    }

    fun localStorageRepository(dataStore: DataStore<Preferences>): SettingLocalStorage {
        return SettingLocalStorage.getInstance(dataStore)
    }
}