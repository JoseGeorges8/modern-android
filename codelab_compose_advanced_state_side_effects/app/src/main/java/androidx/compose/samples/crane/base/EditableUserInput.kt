/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.compose.samples.crane.base

import androidx.annotation.DrawableRes
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.samples.crane.ui.captionTextStyle
import androidx.compose.ui.graphics.SolidColor


/// State Holder
class EditableUserInputState(private val hint: String, initialText: String) {
    var text by mutableStateOf(initialText)
    val isHint: Boolean
        get() = text == hint

    // It's a good practice to place Saver definitions close to the class they work with.
    // In this case, we use a listSaver as an implementation detail to store and restore an instance of EditableUserInputState in the saver
    companion object {
        val Saver: Saver<EditableUserInputState, *> = listSaver(
            save = { listOf(it.hint, it.text) },
            restore = {
                EditableUserInputState(
                    hint = it[0],
                    initialText = it[1]
                )
            }
        )
    }
}

// State holders always need to be remembered in order to keep them in the Composition and
// not create a new one every time. It's a good practice to create a method in
// the same file that does this to remove boilerplate and avoid any mistakes that might occur
@Composable
fun rememberEditableUserInputState(hint: String): EditableUserInputState =
// If we only remember this state, it won't survive activity recreations.
// To achieve that, we can use the rememberSaveable API instead which behaves similarly
// to remember, but the stored value also survives activity and process recreation.
// Internally, it uses the saved instance state mechanism.

// rememberSaveable does all this with no extra work for objects that can be stored inside a Bundle.
// That's not the case for the EditableUserInputState class that we created in our project.
    // Therefore, we need to tell rememberSaveable how to save and restore an instance of this class using a Saver.
    rememberSaveable(hint, saver = EditableUserInputState.Saver) {
        EditableUserInputState(hint, hint)
    }

@Composable
fun CraneEditableUserInput(
    state: EditableUserInputState = rememberEditableUserInputState(""),
    caption: String? = null,
    @DrawableRes vectorImageId: Int? = null
) {
    CraneBaseUserInput(
        caption = caption,
        tintIcon = { !state.isHint },
        showCaption = { !state.isHint },
        vectorImageId = vectorImageId
    ) {
        BasicTextField(
            value = state.text,
            onValueChange = {
                state.text = it
            },
            textStyle = if (state.isHint) {
                captionTextStyle.copy(color = LocalContentColor.current)
            } else {
                MaterialTheme.typography.body1.copy(color = LocalContentColor.current)
            },
            cursorBrush = SolidColor(LocalContentColor.current)
        )
    }
}
