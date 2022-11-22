package com.josegeorges.codelab_compose_state.data.repositories

import com.josegeorges.codelab_compose_state.data.models.WellnessTask

class WellnessTaskRepository {

    fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }
}