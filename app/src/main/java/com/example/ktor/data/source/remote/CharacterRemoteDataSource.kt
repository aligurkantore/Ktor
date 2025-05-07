package com.example.ktor.data.source.remote

import com.example.ktor.data.model.response.CharactersResponseModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.path

class CharacterRemoteDataSource(private val client: HttpClient) : Service {

    override suspend fun getCharacters(page: Int): CharactersResponseModel {
        return client.get {
            url {
                path("character/")
                parameters.append("page", page.toString())
            }
        }.body()
    }

}