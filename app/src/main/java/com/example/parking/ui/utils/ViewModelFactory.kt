package com.example.parking.ui.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.parking.di.Injection
import com.example.parking.ui.screen.home.HomeViewModel

class ViewModelFactory(
    private val injection: Injection,
    private val context: Context,
//    private val dataStore: DataStore<Preferences> = context.dataStore
) :ViewModelProvider.NewInstanceFactory()
{
    @Suppress("UNCHECKED_CAST")
    override fun <T: ViewModel> create(modelClass: Class<T>) :T {

        if(modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(injection.ProfileRepository()) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}