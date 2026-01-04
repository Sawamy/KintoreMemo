package com.example.kintorememo.ui.exercise

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

enum class BodyPart(val label: String) {
    CHEST("胸"),
    BACK("背中"),
    SHOULDERS("肩"),
    LEGS("脚"),
    ARMS("腕"),
    CORE("体幹"),
    FULL_BODY("全身"),
    OTHER("その他")
}

data class ExerciseInputState(
    val name: String = "",
    val bodyPart: BodyPart = BodyPart.CHEST,
    val note: String = "",
    val errorMessage: String? = null,
    val savedMessage: String? = null,
    val isSaving: Boolean = false
)

@HiltViewModel
class ExerciseEntryViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(ExerciseInputState())
    val state: StateFlow<ExerciseInputState> = _state.asStateFlow()

    fun onNameChange(name: String) {
        _state.update { it.copy(name = name, errorMessage = null) }
    }

    fun onBodyPartChange(bodyPart: BodyPart) {
        _state.update { it.copy(bodyPart = bodyPart) }
    }

    fun onNoteChange(note: String) {
        _state.update { it.copy(note = note) }
    }

    fun onSave() {
        val trimmedName = _state.value.name.trim()
        if (trimmedName.isEmpty()) {
            _state.update { it.copy(errorMessage = "種目名を入力してください") }
            return
        }

        _state.update {
            it.copy(
                isSaving = true,
                errorMessage = null,
                savedMessage = null
            )
        }

        _state.update {
            it.copy(
                isSaving = false,
                savedMessage = "登録しました: $trimmedName"
            )
        }
    }

    fun onMessageConsumed() {
        _state.update { it.copy(savedMessage = null) }
    }
}
