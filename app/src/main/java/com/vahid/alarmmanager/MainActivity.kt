package com.vahid.alarmmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vahid.alarmmanager.ui.theme.AlarmManagerTheme
import java.time.LocalDateTime

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val scheduler = AndroidAlarmScheduler(this)
        var alarmItem: AlarmItem? = null
        setContent {
            AlarmManagerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var secondText by remember {
                        mutableStateOf("")
                    }
                    var message by remember {
                        mutableStateOf("")
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        OutlinedTextField(value = secondText,
                            onValueChange = {
                                secondText = it
                            },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(text = "Trigger Alarm in Second")
                            }
                        )
                        OutlinedTextField(value = message, onValueChange = {
                            message = it
                        },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(text = "Message")
                            }
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(onClick = {
                                alarmItem = AlarmItem(
                                    time = LocalDateTime.now().plusSeconds(secondText.toLong()),
                                    message = message
                                )
                                alarmItem?.let {
                                    scheduler::scheduler
                                }
                                secondText = ""
                                message = ""
                            }) {
                                Text(text = "Schedule")
                            }
                            Button(onClick = {
                                alarmItem.let {
                                    scheduler::cancel
                                }
                            }) {
                                Text(text = "Cancel")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AlarmManagerTheme {
        Greeting("Android")
    }
}