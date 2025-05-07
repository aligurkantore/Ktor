package com.example.ktor.data.domain.repository

import com.example.ktor.data.model.response.CharactersResponseModel
import com.example.ktor.data.source.remote.Service
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterRepository(private val service: Service) {
    fun getCharacters(page: Int): Flow<CharactersResponseModel> = flow {
        val response = service.getCharacters(page)
        emit(response)
    }
}