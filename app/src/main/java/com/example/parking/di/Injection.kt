package com.example.parking.di

import com.example.parking.data.repository.UserRepository

object Injection {
    fun ProfileRepository(): UserRepository {
        return UserRepository.getInstance()
    }
}