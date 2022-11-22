package com.josegeorges.codelab_compose_state.ui.reusable_composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josegeorges.codelab_compose_state.ui.theme.Codelab_compose_stateTheme

@Composable
fun StatefulWaterCounter(
    modifier: Modifier = Modifier,
) {
    var count by rememberSaveable { mutableStateOf(0) }
    StatelessWaterCounter(count = count, onButtonPressed = { count++ }, modifier = modifier)
}

@Composable
fun StatelessWaterCounter(
    modifier: Modifier = Modifier,
    count: Int,
    onButtonPressed: () -> Unit
) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        Button(onClick = onButtonPressed, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text(text = "Add One")
        }
    }
}

@Preview
@Composable
fun PreviewWaterCounter() {
    Codelab_compose_stateTheme {
        Surface {
            StatelessWaterCounter(count = 2, onButtonPressed = {})
        }
    }
}