/**
 * Nov 19, 2022
 *
 * Tutorial from the Compose Documentation
 * https://developer.android.com/jetpack/compose/tutorial
 *
 * */

package com.josegeorges.compose_tutorial

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josegeorges.compose_tutorial.data.SampleData
import com.josegeorges.compose_tutorial.ui.theme.Compose_tutorialTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // The setContent block defines the activity's layout where composable functions are called.
        setContent {
            Compose_tutorialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    // Composable functions can only be called from other composable functions.
                    Conversation(SampleData.conversationSample)
                }
            }
        }
    }
}

data class Message(val author: String, val body: String)

@Composable
fun Conversation(messages: List<Message>) {
    // LazyColumn and LazyRow render only the elements that are visible on screen,
    // so they are designed to be very efficient for long lists.
    LazyColumn {
        messages.map { item { MessageCard(it) } }
    }
}

@Preview
@Composable
fun PreviewConversation() {
    Compose_tutorialTheme {
        Conversation(SampleData.conversationSample)
    }
}


// To make a function composable, add the @Composable annotation.
@Composable
fun MessageCard(msg: Message) {
    // UI elements are hierarchical, with elements contained in other elements.
    // In Compose, you build a UI hierarchy by calling composable functions from other composable functions.

    // The Row function lets you arrange elements horizontally
    // To decorate or configure a composable, Compose uses modifiers.
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            // You can chain modifiers to create richer composables.
            modifier = Modifier
                // Set image size to 40 dp
                .size(40.dp)
                // Clip image to be shaped as a circle
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape),
            // painterResource retrieves the image from the resources
            painter = painterResource(R.drawable.img),
            contentDescription = "Contact profile picture",
        )

        // Add a horizontal space between the image and the column
        Spacer(modifier = Modifier.width(8.dp))

        /*
         Composable functions can store local state in memory by using remember
         and track changes to the value passed to mutableStateOf

         Composables (and their children) using this state will get redrawn
         automatically when the value is updated. This is called recomposition.

         By using Compose’s state APIs like remember and mutableStateOf,
         any changes to state automatically update the UI
        */

        // We keep track if the message is expanded or not in this variable
        var isExpanded by remember { mutableStateOf(false) }
        // surfaceColor will be updated gradually from one color to the other
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
        )

        // The Column function lets you arrange elements vertically
        // We toggle the isExpanded variable when we click on this Column
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            // the Text composable function that is defined by the
            // Compose UI library displays a text label on the screen.
            Text(
                text = msg.author,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )
            // Add a vertical space between the author and message texts
            Spacer(modifier = Modifier.height(4.dp))
            // Wrap the message body text around a
            // Surface composable. Doing so allows customizing
            // the message body's shape and elevation.
            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                // surfaceColor color will be changing gradually from primary to surface
                color = surfaceColor,
                // animateContentSize will change the Surface size gradually
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp),
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.body2,
                    // If the message is expanded, we display all its content
                    // otherwise we only display the first line
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                )
            }
        }
    }
}

/**
The @Preview annotation lets you preview your composable functions within Android Studio
without having to build and install the app to an Android device or emulator.

The annotation must be used on a composable function that does not take in parameters.

You can create multiple previews in your file as separate functions,
or add multiple annotations to the same function.
 */
@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewMessageCard() {
    Compose_tutorialTheme {
        Surface {
            // Composable functions can only be called from other composable functions.
            MessageCard(Message("Jose", "Hello World!"))
        }
    }
}
