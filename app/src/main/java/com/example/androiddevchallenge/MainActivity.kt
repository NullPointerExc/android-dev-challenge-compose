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
package com.example.androiddevchallenge

import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    var running by remember { mutableStateOf(false) }
    var value by remember { mutableStateOf(10L) }
    var progress by remember { mutableStateOf(1f) }
    val countDownTimer by remember {
        mutableStateOf(object : CountDownTimer(11000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                value--
                progress -= 0.1f
            }

            override fun onFinish() {
                value = 10
                progress = 1f
                running = false
            }
        })
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.DarkGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box {
            CircularProgressIndicator(
                progress = progress,
                modifier = Modifier
                    .size(264.dp)
                    .offset(y = (-16).dp),
                color = Color.Yellow
            )
            Column(
                modifier = Modifier.size(264.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(modifier = Modifier) {
                    Surface(
                        modifier = Modifier.size(75.dp),
                        shape = CircleShape,
                        color = Color.Gray
                    ) {
                        IconButton(
                            onClick = {
                                if (!running) {
                                    countDownTimer.start()
                                    running = true
                                }
                            }
                        ) {
                            Image(
                                painterResource(R.drawable.ic_play),
                                contentDescription = null,
                                modifier = Modifier.requiredSize(48.dp)
                            )
                        }
                    }
                    Spacer(Modifier.size(20.dp))
                    Surface(
                        modifier = Modifier.size(75.dp),
                        shape = CircleShape,
                        color = Color.Gray
                    ) {
                        IconButton(
                            onClick = {
                                countDownTimer.cancel()
                                value = 10
                                progress = 1f
                                running = false
                            }
                        ) {
                            Image(
                                painterResource(R.drawable.ic_stop),
                                contentDescription = null,
                                modifier = Modifier.requiredSize(48.dp)
                            )
                        }
                    }
                }
                Surface(
                    modifier = Modifier
                        .size(150.dp)
                        .offset(y = (-16).dp),
                    shape = CircleShape,
                    color = Color.Gray
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = "$value",
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            fontSize = 48.sp,
                            style = TextStyle(fontWeight = FontWeight.Bold)
                        )
                    }
                }
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
