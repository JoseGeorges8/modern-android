package com.josegeorges.codelab_compose_state.ui.reusable_composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.josegeorges.codelab_compose_state.data.models.WellnessTask
import com.josegeorges.codelab_compose_state.data.repositories.WellnessTaskRepository
import com.josegeorges.codelab_compose_state.ui.theme.Codelab_compose_stateTheme

@Composable
fun WellnessTasksList(
    modifier: Modifier = Modifier,
    list: List<WellnessTask>,
    onCloseTask: (WellnessTask) -> Unit,
    onCheckedTask: (WellnessTask, Boolean) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        /*
        The items method receives a key parameter.
        By default, each item's state is keyed against the position of the item in the list.

        In a mutable list, this causes issues when the data set changes,
        since items that change position effectively lose any remembered state.

        You can easily fix this by using the id of each WellnessTaskItem as the key for each item.
        */
        items(items = list, key = { task -> task.id }) { task ->
            WellnessTaskItem(
                taskName = task.label,
                checked = task.checked,
                onClose = { onCloseTask(task) },
                onCheckedChange = { checked -> onCheckedTask(task, checked) },
            )
        }
    }

}

@Preview
@Composable
fun PreviewWellnessTasksList(
    modifier: Modifier = Modifier,
    list: List<WellnessTask> = remember { WellnessTaskRepository().getWellnessTasks() }
) {
    Codelab_compose_stateTheme {
        Surface {
            WellnessTasksList(
                list = WellnessTaskRepository().getWellnessTasks(),
                onCloseTask = {},
                onCheckedTask = { _, _ -> }
            )
        }
    }

}