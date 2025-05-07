package com.example.ktor.ui.fragment.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktor.data.domain.usecase.CharacterUseCase
import com.example.ktor.data.model.response.CharactersResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getCharactersUseCase: CharacterUseCase
) : ViewModel() {

    private val _characters = MutableStateFlow<CharactersResponseModel?>(null)
    val characters: StateFlow<CharactersResponseModel?> = _characters

    init {
        getCharacters(1)
    }

    private fun getCharacters(page: Int) {
        viewModelScope.launch {
            getCharactersUseCase(page)
                .catch { e ->
                    e.printStackTrace()
                }
                .collect { response ->
                    _characters.value = response
                }
        }
    }
}