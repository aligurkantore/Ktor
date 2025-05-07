package com.example.ktor.di

import com.example.ktor.data.domain.repository.CharacterRepository
import com.example.ktor.data.source.remote.CharacterRemoteDataSource
import com.example.ktor.data.source.remote.Service
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KtorClientModule {

    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    @Provides
    @Singleton
    fun provideKtorClient(): HttpClient {
        return HttpClient(Android) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
            install(Logging) {
                level = LogLevel.ALL
            }
            defaultRequest {
                url(BASE_URL)
                header("Accept", "application/json")
            }
        }
    }

    @Provides
    @Singleton
    fun provideApiService(client: HttpClient): Service {
        return CharacterRemoteDataSource(client)
    }

    @Provides
    @Singleton
    fun provideRepository(apiService: Service): CharacterRepository {
        return CharacterRepository(apiService)
    }
}