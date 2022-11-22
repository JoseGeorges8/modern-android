package com.josegeorges.codelab_compose_state.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.josegeorges.codelab_compose_state.ui.reusable_composables.StatefulWaterCounter
import com.josegeorges.codelab_compose_state.ui.reusable_composables.WellnessTasksList
import com.josegeorges.codelab_compose_state.ui.theme.Codelab_compose_stateTheme
import com.josegeorges.codelab_compose_state.ui.view_models.WellnessViewModel

@Composable
fun WellnessScreen(
    modifier: Modifier = Modifier, wellnessViewModel: WellnessViewModel = viewModel()
) {
    Column(modifier) {
        StatefulWaterCounter()

        /*
        Warning: You can use the mutableStateListOf API instead to create the list.
        However, the way you use it might result in unexpected recomposition and suboptimal
        UI performance.

        If you just define the list and then add the tasks in a different operation it would result
        in duplicated items being added for every recomposition.

        // Don't do this!
        val list = remember { mutableStateListOf<WellnessTask>() }
        list.addAll(getWellnessTasks())

        Instead, create the list with its initial value in a single operation and then pass it
        to the remember function, like this:

        // Do this instead. Don't need to copy
        val list = remember {
            mutableStateListOf<WellnessTask>().apply { addAll(getWellnessTasks()) }
        }
        */
        /*

        If you try to use rememberSaveable() to store the list in WellnessScreen, you'll get a runtime exception:

        cannot be saved using the current SaveableStateRegistry.
        The default implementation only supports types which can be stored inside the Bundle.
        Please consider implementing a custom Saver for this class and pass it to rememberSaveable()

        This error tells you that you need to provide a custom saver.
        However, you shouldn't be using rememberSaveable to store large amounts of data or complex
        data structures that require lengthy serialization or deserialization.

        Similar rules apply when working with Activity's onSaveInstanceState;
        you can find more information in the Save UI states documentation.
        If you want to do this, you need an alternative storing mechanism.
        You can learn more about different options for preserving UI state in the documentation.

        Save UI states documentation - https://developer.android.com/topic/libraries/architecture/saving-states#onsaveinstancestate
        Options for preserving UI state - https://developer.android.com/topic/libraries/architecture/saving-states#options

        */
        // before using ViewModel
//        val list = remember { WellnessTaskRepository().getWellnessTasks().toMutableStateList() }
        WellnessTasksList(
            list = wellnessViewModel.tasks,
            onCloseTask = { task -> wellnessViewModel.remove(task) },
            onCheckedTask = { task, checked ->
                wellnessViewModel.changeTaskChecked(task, checked)
            },
        )
    }
}

@Preview
@Composable
fun PreviewWellnessScreen(modifier: Modifier = Modifier) {
    Codelab_compose_stateTheme {
        Surface {
            WellnessScreen()
        }
    }

}