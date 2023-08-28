/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.woof

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateOffset
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.woof.data.Dog
import com.example.woof.data.dogs
import com.example.woof.ui.AnimatedChildAlignment
import com.example.woof.ui.theme.WoofTheme
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WoofTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
//                    AnimatedChildAlignmentTest()
//                    TransitionAnimation()
                    WoofCloneApp()
                }
            }
        }
    }
}


@Composable
fun AnimatedChildAlignmentTest() {
    var alignment by remember {
        mutableStateOf(Alignment.Center)
    }

    var alignmentTopStart by remember {
        mutableStateOf(Alignment.BottomStart)
    }
    var alignmentTopEnd by remember {
        mutableStateOf(Alignment.BottomEnd)
    }


    var alignmentCenterStart by remember {
        mutableStateOf(Alignment.BottomStart)
    }

    var alignmentCenterEnd by remember {
        mutableStateOf(Alignment.BottomEnd)
    }

    var alignmentBottomStart by remember {
        mutableStateOf(Alignment.BottomStart)
    }

    var alignmentBottomEnd by remember {
        mutableStateOf(Alignment.BottomEnd)
    }


    var alignmentValue by remember {
        mutableStateOf(0f)
    }

    alignment = when (alignmentValue.roundToInt()) {
        0 -> Alignment.TopStart
        1 -> Alignment.TopCenter
        2 -> Alignment.TopEnd
        3 -> Alignment.CenterStart
        4 -> Alignment.Center
        5 -> Alignment.CenterEnd
        6 -> Alignment.BottomStart
        7 -> Alignment.BottomCenter
        else -> Alignment.BottomEnd
    }

    val text = when (alignmentValue.roundToInt()) {
        0 -> "Alignment.TopStart"
        1 -> "Alignment.TopCenter"
        2 -> "Alignment.TopEnd"
        3 -> "Alignment.CenterStart"
        4 -> "Alignment.Center"
        5 -> "Alignment.CenterEnd"
        6 -> "Alignment.BottomStart"
        7 -> "Alignment.BottomCenter"
        else -> "Alignment.BottomEnd"
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .padding(10.dp)
    ) {

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Alignment: $text", fontSize = 20.sp)
        Slider(
            value = alignmentValue, onValueChange = {
                alignmentValue = it
            }, valueRange = 0f..8f, steps = 7
        )


//        AnimatedChildAlignment(alignment = Alignment.TopStart)
        /* AnimatedChildAlignment(alignment = Alignment.TopEnd)
         AnimatedChildAlignment(alignment = Alignment.CenterStart)
         AnimatedChildAlignment(alignment = Alignment.CenterEnd)
         AnimatedChildAlignment(alignment = Alignment.BottomStart)
         AnimatedChildAlignment(alignment = Alignment.BottomEnd)*/
    }


    AnimatedChildAlignment(alignment = alignmentTopStart)
    AnimatedChildAlignment(alignment = alignmentTopEnd)
    AnimatedChildAlignment(alignment = alignmentCenterStart)
    AnimatedChildAlignment(alignment = alignmentCenterEnd)
    AnimatedChildAlignment(alignment = alignmentBottomStart)
    AnimatedChildAlignment(alignment = alignmentBottomEnd)

    LaunchedEffect(key1 = Unit, block = {

        delay(500)
        alignmentTopStart = Alignment.TopStart
        delay(500)
        alignmentTopEnd = Alignment.TopEnd
        delay(500)
        alignmentCenterStart = Alignment.CenterStart
        delay(500)
        alignmentCenterEnd = Alignment.CenterEnd
        delay(500)
        alignmentBottomStart = Alignment.BottomStart
        delay(500)
        alignmentBottomEnd = Alignment.BottomEnd
    })

}

@Composable
private fun TransitionAnimation() {
    var isAnimated by remember { mutableStateOf(false) }
    val transition = updateTransition(targetState = isAnimated, label = "transition")


    val rocketOffset by transition.animateOffset(transitionSpec = {
        if (this.targetState) {
            tween(1000) // launch duration

        } else {
            tween(1500) // land duration
        }
    }, label = "rocket offset") { animated ->
        if (animated) Offset(200f, 0f) else Offset(200f, 500f)
    }

    val rocketSize by transition.animateDp(transitionSpec = {
        tween(1000)
    }, "") { animated ->
        if (animated) 75.dp else 150.dp
    }


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_woof_logo),
            contentDescription = "Rocket",
            modifier = Modifier
                .size(rocketSize)

                .alpha(1.0f)
                .offset(rocketOffset.x.dp, rocketOffset.y.dp)
        )
        Button(
            onClick = { isAnimated = !isAnimated }, modifier = Modifier.padding(top = 10.dp)
        ) {
            Text(text = if (isAnimated) "Land rocket" else "Launch rocket")
        }
    }
}


enum class State {
    HIDDEN, SHOW
}

enum class ButtonState {
    IDLE, PRESSED
}


@Preview()
@Composable
fun WoofCloneApp() {

    Scaffold(topBar = {
        WoofTopAppBar()
    }) { it: PaddingValues ->

        var wiggleState by remember {
            mutableStateOf(false)
        }

        val coroutineScope = rememberCoroutineScope()
        val offsetX = remember {
            Animatable(0f)
        }
        val offsetY = remember {
            Animatable(0f)
        }

        val animatedAlpha by animateFloatAsState(
            targetValue = if (wiggleState) 1.0f else 0.5f,
            label = "alpha"
        )

        Column(
            Modifier
                .fillMaxWidth()
                .clickable {
                    if (wiggleState) {
                        wiggleState = false
                        coroutineScope.launch {
                            offsetX.animateTo(
                                50f,
                                spring(Spring.DampingRatioHighBouncy, Spring.StiffnessHigh)
                            )
                        }

                        coroutineScope.launch {
                            offsetY.animateTo(
                                50f,
                                spring(Spring.DampingRatioHighBouncy, Spring.StiffnessHigh)
                            )
                        }


                    } else {

                        wiggleState = true
                        coroutineScope.launch {
                            offsetX.animateTo(
                                0f,
                                spring(Spring.DampingRatioHighBouncy, Spring.StiffnessHigh)
                            )
                        }

                        coroutineScope.launch {
                            offsetY.animateTo(
                                0f,
                                spring(Spring.DampingRatioHighBouncy, Spring.StiffnessHigh)
                            )
                        }

                    }
                }
                .padding(it)
        ) {

            Spacer(modifier = Modifier.height(50.dp))

            Row(Modifier.fillMaxWidth()) {

                Image(
                    painter = painterResource(id = R.drawable.ic_woof_logo),
                    contentDescription = null,
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .offset {
                            IntOffset(offsetX.value.toInt(), offsetY.value.toInt())
                        }
                        .alpha(animatedAlpha)
                        .weight(1f)
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_woof_logo),
                    contentDescription = null,
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .offset {
                            IntOffset(offsetX.value.toInt(), offsetY.value.toInt())
                        }
                        .alpha(animatedAlpha)
                        .weight(1f)
                )

            }

            Spacer(modifier = Modifier.height(50.dp))


            Row(Modifier.fillMaxWidth()) {

                Image(
                    painter = painterResource(id = R.drawable.ic_woof_logo),
                    contentDescription = null,
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .offset {
                            IntOffset(offsetX.value.toInt(), offsetY.value.toInt())
                        }
                        .alpha(animatedAlpha)
                        .weight(1f)
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_woof_logo),
                    contentDescription = null,
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .offset {
                            IntOffset(offsetX.value.toInt(), offsetY.value.toInt())
                        }
                        .alpha(animatedAlpha)
                        .weight(1f)
                )

                val buttonState = remember { mutableStateOf(ButtonState.IDLE) }

                FavButton()

            }


        }


        LaunchedEffect(key1 = Unit, block = {

            offsetX.animateTo(
                50f,
                animationSpec = spring(Spring.DampingRatioHighBouncy, Spring.StiffnessHigh)
            )

            offsetY.animateTo(
                50f,
                animationSpec = spring(Spring.DampingRatioHighBouncy, Spring.StiffnessHigh)
            )

        }

        )


    }

}

@Composable
fun FavButton() {


}

/**
 * Composable that displays an app bar and a list of dogs.
 */
@Composable
fun WoofApp() {
    Scaffold(topBar = {
        WoofTopAppBar()
    }) { it ->

        var pawsState by remember { mutableStateOf(State.HIDDEN) }

        val offsetAnimation by animateDpAsState(
            if (pawsState == State.HIDDEN) 0.dp else 30.dp,
            spring(dampingRatio = Spring.DampingRatioLowBouncy, stiffness = Spring.StiffnessHigh)
        )


        val coroutineScope = rememberCoroutineScope()

        val offsetX = remember { Animatable(0f) }
        val offsetY = remember { Animatable(0f) }


        var useRC = remember("Hello", "World") {

            return@remember "YES"
        }
        LazyColumn(contentPadding = it) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            Log.d("TAG", "WoofApp: pawsState called")
                            pawsState = if (pawsState == State.HIDDEN) {

                                Log.d("TAG", "WoofApp: inside State.HIDDEN")

                                coroutineScope.launch {

                                    launch {
                                        offsetX.animateTo(
                                            targetValue = 30f,
                                            animationSpec = spring(dampingRatio = Spring.DampingRatioHighBouncy)
                                        )
                                    }


                                }


                                coroutineScope.launch {
                                    launch {
                                        offsetY.animateTo(
                                            targetValue = 50f,
                                            animationSpec = spring(dampingRatio = Spring.DampingRatioHighBouncy)
                                        )
                                    }
                                }
                                State.SHOW
                            } else {

                                Log.d("TAG", "WoofApp: inside State.SHOW")
                                coroutineScope.launch {

                                    launch {
                                        offsetX.animateTo(
                                            targetValue = 40f,
                                            animationSpec = spring(dampingRatio = Spring.DampingRatioHighBouncy)
                                        )
                                    }


                                }


                                coroutineScope.launch {
                                    launch {
                                        offsetY.animateTo(
                                            targetValue = 40f,
                                            animationSpec = spring(dampingRatio = Spring.DampingRatioHighBouncy)
                                        )
                                    }
                                }
                                State.HIDDEN
                            }
                        },

                    ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Cyan)


                    ) {
                        Icon(painter = painterResource(id = R.drawable.ic_woof_logo),
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(top = 64.dp, bottom = 64.dp)
                                .offset {
                                    IntOffset(
                                        offsetX.value.toInt(), offsetY.value.toInt()
                                    )
                                }
//                                .offset(x=offsetAnimation,y=offsetAnimation)
                                .width(100.dp)
                                .height(100.dp),
                            contentDescription = null)

                        Icon(painter = painterResource(id = R.drawable.ic_woof_logo),
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .offset {
                                    IntOffset(
                                        offsetX.value.toInt(), offsetY.value.toInt()
                                    )
                                }
//                                .offset(x=offsetAnimation,y=offsetAnimation)
                                .padding(top = 64.dp, bottom = 64.dp, end = 64.dp)
                                .width(100.dp)
                                .height(100.dp),
                            contentDescription = null)

                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Cyan)


                    ) {
                        Icon(painter = painterResource(id = R.drawable.ic_woof_logo),
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(top = 64.dp, bottom = 64.dp)
                                .offset {
                                    IntOffset(
                                        offsetX.value.toInt(), offsetY.value.toInt()
                                    )
                                }
//                                .offset(x=offsetAnimation,y=offsetAnimation)
                                .width(100.dp)
                                .height(100.dp),
                            contentDescription = null)

                        Icon(painter = painterResource(id = R.drawable.ic_woof_logo),
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .offset {
                                    IntOffset(
                                        offsetX.value.toInt(), offsetY.value.toInt()
                                    )
                                }
//                                .offset(x=offsetAnimation,y=offsetAnimation)
                                .padding(top = 64.dp, bottom = 64.dp, end = 64.dp)
                                .width(100.dp)
                                .height(100.dp),
                            contentDescription = null)

                    }


                }


            }
            items(dogs) {
                DogItem(
                    dog = it, modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                )
            }


        }
        LaunchedEffect(key1 = Unit, block = {
            Log.d("TAG", "WoofApp: Side effect coroutine scope ")
            launch {
                offsetX.animateTo(
                    targetValue = 50f,
                    animationSpec = spring(dampingRatio = Spring.DampingRatioHighBouncy)
                )
            }
        })

        LaunchedEffect(key1 = Unit, block = {
            Log.d("TAG", "WoofApp: Side effect coroutine scope ")


            launch {
                offsetY.animateTo(
                    targetValue = 50f,
                    animationSpec = spring(dampingRatio = Spring.DampingRatioHighBouncy)
                )
            }

        })




    }
}

/**
 * Composable that displays a list item containing a dog icon and their information.
 *
 * @param dog contains the data that populates the list item
 * @param modifier modifiers to set to this composable
 */
@Composable
fun DogItem(
    dog: Dog, modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    val color by animateColorAsState(
        targetValue = if (expanded) MaterialTheme.colorScheme.tertiaryContainer
        else MaterialTheme.colorScheme.primaryContainer,
    )


    Card(
        modifier = Modifier
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy, stiffness = Spring.StiffnessMedium
                )

            )
            .background(color = color)


    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
                .background(color = color)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = color)
                    .padding(dimensionResource(R.dimen.padding_small))

            ) {
                DogIcon(dog.imageResourceId)
                DogInformation(dog.name, dog.age)
                Spacer(modifier = Modifier.weight(1f))
                DogItemButton(expanded = expanded, onClick = { expanded = !expanded })
            }
            if (expanded) DogHobby(
                dog.hobbies, modifier = modifier
                    .padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_small),
                        end = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_medium)
                    )
                    .background(color = color)
            )
        }


    }
}

@Composable
fun DogItemButton(expanded: Boolean, onClick: () -> Unit) {
    IconButton(onClick = onClick) {

        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = null
        )
    }

}


@Composable
fun DogHobby(
    @StringRes dogHobby: Int, modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.about), style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = stringResource(dogHobby), style = MaterialTheme.typography.bodyLarge
        )


    }

}


/**
 * Composable that displays a Top Bar with an icon and text.
 *
 * @param modifier modifiers to set to this composable
 */
@Composable
fun WoofTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.image_size))
                        .padding(dimensionResource(R.dimen.padding_small)),
                    painter = painterResource(R.drawable.ic_woof_logo),

                    // Content Description is not needed here - image is decorative, and setting a
                    // null content description allows accessibility services to skip this element
                    // during navigation.

                    contentDescription = null
                )
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        }, modifier = modifier
    )
}

/**
 * Composable that displays a photo of a dog.
 *
 * @param dogIcon is the resource ID for the image of the dog
 * @param modifier modifiers to set to this composable
 */
@Composable
fun DogIcon(
    @DrawableRes dogIcon: Int, modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .size(dimensionResource(R.dimen.image_size))
            .padding(dimensionResource(R.dimen.padding_small))
            .clip(MaterialTheme.shapes.small),
        contentScale = ContentScale.Crop,
        painter = painterResource(dogIcon),

        // Content Description is not needed here - image is decorative, and setting a null content
        // description allows accessibility services to skip this element during navigation.

        contentDescription = null
    )
}

/**
 * Composable that displays a dog's name and age.
 *
 * @param dogName is the resource ID for the string of the dog's name
 * @param dogAge is the Int that represents the dog's age
 * @param modifier modifiers to set to this composable
 */
@Composable
fun DogInformation(
    @StringRes dogName: Int, dogAge: Int, modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(dogName),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
        )
        Text(
            text = stringResource(R.string.years_old, dogAge),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

/**
 * Composable that displays what the UI of the app looks like in light theme in the design tab.
 */

@Composable
fun WoofPreview() {
    WoofTheme(darkTheme = false) {
        WoofApp()
    }
}

/**
 * Composable that displays what the UI of the app looks like in dark theme in the design tab.
 */

@Composable
fun WoofDarkThemePreview() {
    WoofTheme(darkTheme = true) {
        WoofApp()
    }
}
