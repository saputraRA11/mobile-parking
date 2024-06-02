package com.example.parking.ui.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.parking.data.preferences.dataStore
import com.example.parking.data.remote.retrofit.ApiConfig
import com.example.parking.data.repository.ParkingHistoryRepository
import com.example.parking.di.Injection
import com.example.parking.ui.screen.auth.AuthViewModel
import com.example.parking.ui.screen.form.FormViewModel
import com.example.parking.ui.screen.home.HomeViewModel
import com.example.parking.ui.screen.payment.PaymentViewModel

//import com.example.parking.ui.screen.home.HomeViewModel

class ViewModelFactory(
    private val injection: Injection,
    context: Context,
) :ViewModelProvider.NewInstanceFactory()
{
    val dataStore: DataStore<Preferences> = context.dataStore
    val apiService = ApiConfig.getApiService()
    @Suppress("UNCHECKED_CAST")
    override fun <T: ViewModel> create(modelClass: Class<T>) :T {
        if(modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(injection.userRepository(apiService),injection.localStorageRepository(dataStore)) as T
        }  else if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(
                injection.userRepository(apiService),
                injection.localStorageRepository(dataStore),
                injection.parkingRepository(apiService),
                injection.parkingHistoryRepository(apiService)
            ) as T
        }  else if(modelClass.isAssignableFrom(FormViewModel::class.java)){
            return FormViewModel(
                injection.parkingRepository(apiService),
                injection.localStorageRepository(dataStore),
                injection.userRepository(apiService)
            ) as T
        } else if (modelClass.isAssignableFrom(PaymentViewModel::class.java)) {
            return PaymentViewModel(
                injection.userRepository(apiService),
                injection.localStorageRepository(dataStore),
                injection.parkingRepository(apiService),
                injection.parkingHistoryRepository(apiService),
                injection.paymentRepository(apiService)
                ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}