package com.josegeorges.codelab_compose_state.ui.reusable_composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josegeorges.codelab_compose_state.ui.theme.Codelab_compose_stateTheme

@Composable
fun WellnessTaskItem(
    taskName: String,
    checked: Boolean,
    onClose: () -> Unit,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            text = taskName
        )
        Checkbox(onCheckedChange = onCheckedChange, checked = checked)
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }
}

@Preview
@Composable
fun WellnessTaskItemPreview() {
    Codelab_compose_stateTheme {
        Surface {
            WellnessTaskItem(taskName = "My task", modifier = Modifier, onClose = {}, onCheckedChange = {}, checked = true)
        }
    }
}