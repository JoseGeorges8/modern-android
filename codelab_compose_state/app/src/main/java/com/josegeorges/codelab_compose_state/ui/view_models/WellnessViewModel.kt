package com.josegeorges.codelab_compose_state.ui.view_models

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.josegeorges.codelab_compose_state.data.models.WellnessTask
import com.josegeorges.codelab_compose_state.data.repositories.WellnessTaskRepository

// ViewModels are not part of the Composition.
// Therefore, you should not hold state created in composables (for example, a remembered value)
// because this could cause memory leaks.
class WellnessViewModel(
    wellnessTaskRepository: WellnessTaskRepository = WellnessTaskRepository()
) : ViewModel() {

    private val _tasks = wellnessTaskRepository.getWellnessTasks().toMutableStateList()
    val tasks: List<WellnessTask> get() = _tasks

    fun remove(item: WellnessTask) {
        _tasks.remove(item)
    }

    fun changeTaskChecked(item: WellnessTask, checked: Boolean) =
        tasks.find { it.id == item.id }?.let { task ->
            task.checked = checked
        }

}