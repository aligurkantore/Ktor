package com.example.ktor.data.source.remote

import com.example.ktor.data.model.response.CharactersResponseModel

interface Service {
    suspend fun getCharacters(page: Int): CharactersResponseModel
}