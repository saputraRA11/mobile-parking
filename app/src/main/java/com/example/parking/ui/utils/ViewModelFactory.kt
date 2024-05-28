package com.example.parking.ui.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.parking.data.preferences.dataStore
import com.example.parking.data.remote.retrofit.ApiConfig
import com.example.parking.di.Injection
import com.example.parking.ui.screen.Form.AddAreaViewModel
import com.example.parking.ui.screen.home.HomeViewModel
import com.example.parking.ui.screen.login.LoginViewModel
//import com.example.parking.ui.screen.home.HomeViewModel
import com.example.parking.ui.screen.register.RegisterViewModel
import com.example.parking.ui.screen.validation.ValidationViewModel

class ViewModelFactory(
    private val injection: Injection,
    context: Context,
) :ViewModelProvider.NewInstanceFactory()
{
    val dataStore: DataStore<Preferences> = context.dataStore
    val apiService = ApiConfig.getApiService()
    @Suppress("UNCHECKED_CAST")
    override fun <T: ViewModel> create(modelClass: Class<T>) :T {
        if(modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(injection.userRepository(apiService),injection.localStorageRepository(dataStore)) as T
        } else if(modelClass.isAssignableFrom(ValidationViewModel::class.java)){
            return ValidationViewModel(injection.userRepository(apiService),injection.localStorageRepository(dataStore)) as T
        } else if(modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(injection.userRepository(apiService),injection.localStorageRepository(dataStore)) as T
        } else if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(injection.userRepository(apiService),injection.localStorageRepository(dataStore)) as T
        }  else if(modelClass.isAssignableFrom(AddAreaViewModel::class.java)){
            return AddAreaViewModel(injection.parkingRepository(apiService),injection.localStorageRepository(dataStore)) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}