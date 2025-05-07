package com.example.ktor.data.domain.usecase

import com.example.ktor.data.domain.repository.CharacterRepository
import com.example.ktor.data.model.response.CharactersResponseModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke(page: Int): Flow<CharactersResponseModel> {
        return repository.getCharacters(page)
    }
}