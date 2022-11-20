/**
 * Compose essentials Pathway: Write your first Compose app
 *
 * Link to this Codelab:
 * https://developer.android.com/codelabs/jetpack-compose-basics?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fjetpack-compose-for-android-developers-1%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fjetpack-compose-basics#0
 * */
package com.josegeorges.codelab_compose_basics

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josegeorges.codelab_compose_basics.ui.theme.CodelabComposeBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodelabComposeBasicsTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

// As a best practice, your function should include a Modifier parameter
// that is assigned an empty Modifier by default. Forward this modifier
// to the first composable you call inside your function. This way,
// the calling site can adapt layout instructions and behaviors from
// outside of your composable function.
@Composable
fun MyApp(modifier: Modifier = Modifier) {
    // using a by keyword instead of the =
    // This is a property delegate that saves you from typing .value every time
    // Instead of using remember you can use rememberSaveable.
    // This will save each state surviving configuration changes (such as rotations) and process death.
    var shouldShowOnBoarding by rememberSaveable { mutableStateOf(true) }

    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        if (shouldShowOnBoarding) {
            OnBoardingScreen(onContinueClicked = { shouldShowOnBoarding = false })
        } else {
            Greetings()
        }
    }
}

@Composable
fun OnBoardingScreen(modifier: Modifier = Modifier, onContinueClicked: () -> Unit) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Basics Codelab!")
        ElevatedButton(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text("Continue")
        }
    }
}

@Composable
fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = List(1000) { "$it" }
) {
    // LazyColumn doesn't recycle its children like RecyclerView.
    // It emits new Composables as you scroll through it and is still performant,
    // as emitting Composables is relatively cheap compared to instantiating Android Views
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}

@Composable
fun Greeting(name: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name = name)
    }
}

@Composable
fun CardContent(name: String) {
    /*
    -- mutableStateOf --
    To add internal state to a composable, you can use the mutableStateOf function,
    which makes Compose recompose functions that read that State

    -- remember function --
    To preserve state across recompositions, remember the mutable state using remember
    remember is used to guard against recomposition, so the state is not reset.
    */
    val expanded = remember {
        mutableStateOf(false)
    }
    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        // -- Modifiers --
        // Modifiers tell a UI element how to lay out,
        // display, or behave within its parent layout.
        // There are dozens of modifiers which can be used to align,
        // animate, lay out, make clickable or scrollable, transform, etc.
        Column(
            // The weight modifier makes the element fill all available space,
            // making it flexible, effectively pushing away the other elements
            // that don't have a weight, which are called inflexible.
            modifier = Modifier
                .weight(1F)
                .padding(12.dp)
        ) {
            // -- Text color change --
            // Material Design is opinionated, so this
            // text will be white
            // Surface understands that, when the background is set to the primary color,
            // any text on top of it should use the onPrimary color, which is also defined
            // in the theme.
            Text(text = "Hello")
            Text(
                text = "$name!", style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            if (expanded.value) {
                Text(
                    text = ("Composem ipsum color sit lazy, " +
                            "padding theme elit, sed do bouncy. ").repeat(4),
                )
            }
        }
        IconButton(
            // Functions are first class citizens in Kotlin, so they can be assigned to a
            // variable, passed into other functions and even be returned from them.
            onClick = { expanded.value = !expanded.value }) {
            // The composable function will automatically be "subscribed" to the state.
            // If the state changes, composables that read these fields will be recomposed
            // to display the updates.
            if (expanded.value) Icon(
                imageVector = Icons.Filled.ExpandLess,
                contentDescription = stringResource(R.string.show_less)
            )
            else Icon(
                imageVector = Icons.Filled.ExpandMore, contentDescription = stringResource(
                    R.string.show_more
                )
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnBoardingPreview() {
    CodelabComposeBasicsTheme {
        OnBoardingScreen(onContinueClicked = {})
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "Dark"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
private fun GreetingPreview() {
    CodelabComposeBasicsTheme {
        Greetings()
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    CodelabComposeBasicsTheme {
        MyApp(Modifier.fillMaxSize())
    }
}