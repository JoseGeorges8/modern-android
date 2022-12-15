# Navigation in Jetpack Compose Codelab

This folder contains the source code for the
[Navigation in Jetpack Compose Codelab](https://developer.android.com/codelabs/jetpack-compose-navigation)
codelab.

## License
```
Copyright 2022 The Android Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

## Notes

The 3 main parts of Navigation are the `NavController`, `NavGraph`, and `NavHost`. 

To use compose navigation:

- Add the latest Compose Navigation dependency
- Set up the `NavController`
- Add a `NavHost` and create the `NavGraph`
- Prepare routes for navigating between different app destinations

The `NavController` is always associated with a single `NavHost` composable. 
The `NavHost` acts as a container and is responsible for displaying the current destination of the `NavGraph`. 
As you navigate between composables, the content of the `NavHost` is automatically recomposed. 
It also links the `NavController` with a `NavGraph` that maps out the composable destinations to navigate between. It is essentially a collection of fetchable destinations.

