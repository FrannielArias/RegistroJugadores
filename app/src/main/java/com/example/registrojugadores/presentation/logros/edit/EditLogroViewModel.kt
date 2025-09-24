package com.example.registrojugadores.presentation.logros.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registrojugadores.domain.logros.model.Logro
import com.example.registrojugadores.domain.logros.useCase.DeleteLogroUseCase
import com.example.registrojugadores.domain.logros.useCase.ExisteTituloUseCase
import com.example.registrojugadores.domain.logros.useCase.GetLogroUseCase
import com.example.registrojugadores.domain.logros.useCase.UpsertLogroUseCase
import com.example.registrojugadores.domain.logros.useCase.validateDescripcion
import com.example.registrojugadores.domain.logros.useCase.validateTitulo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditLogroViewModel @Inject constructor(
    private val getLogroUseCase: GetLogroUseCase,
    private val upsertLogroUseCase: UpsertLogroUseCase,
    private val deleteLogroUseCase: DeleteLogroUseCase,
    private val existeTituloUseCase: ExisteTituloUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(value = EditLogroUiState())
    val state: StateFlow<EditLogroUiState> = _state.asStateFlow()

    fun onEvent(event: EditLogroUiEvent){
        when (event){
            is EditLogroUiEvent.Load -> onLoad(id = event.id)
            is EditLogroUiEvent.TituloChanged -> _state.update {
                it.copy(titulo = event.value, tituloError = null)
            }

            is EditLogroUiEvent.DescripcionChanged -> _state.update {
                it.copy(descripcion = event.value, descripcionError = null)
            }

            EditLogroUiEvent.Save -> onSave()
            EditLogroUiEvent.Delete -> onDelete()
        }
    }

    private fun onLoad(id: Int?){
        if(id == null || id == 0 ){
            _state.update { it.copy(isNew = true, logroId = null) }
            return
        }
        viewModelScope.launch {
            val logro = getLogroUseCase(id)
            if(logro != null){
                _state.update {
                    it.copy(
                        isNew = false,
                        logroId = logro.logroId,
                        descripcion = logro.descripcion
                    )
                }
            }
        }
    }

    private fun onSave(){
        val titulo = state.value.titulo
        val tituloValidations = validateTitulo(titulo)
        val descripcion = state.value.descripcion
        val descriptionValidations = validateDescripcion(descripcion)

        if(!tituloValidations.isValid || !descriptionValidations.isValid){
            _state.update {
                it.copy(
                    tituloError = tituloValidations.error,
                    descripcionError = descriptionValidations.error
                )
            }
            return
        }
        viewModelScope.launch {
            val currenId = state.value.logroId
            if(existeTituloUseCase(titulo, currenId)){
                _state.update {
                    it.copy(
                        tituloError = "Ya existe un logro con este titulo"
                    )
                }
                return@launch
            }
            _state.update { it.copy(isSaving = true) }
            val id = state.value.logroId ?: 0
            val logro = Logro(
                logroId = id,
                titulo = titulo,
                descripcion = descripcion
            )
            val result = upsertLogroUseCase(logro)
            result.onSuccess { newId ->
                _state.value = EditLogroUiState()
            }.onFailure { existeTituloUseCase ->
                _state.update { it.copy(isSaving = true) }
            }
        }
    }

    private fun onDelete(){
        val id = state.value.logroId ?: return
        viewModelScope.launch {
            _state.update { it.copy(isDeleting = true) }
            deleteLogroUseCase(id)
            _state.update { it.copy(isDeleting = false, deleted = true) }
        }
    }
}