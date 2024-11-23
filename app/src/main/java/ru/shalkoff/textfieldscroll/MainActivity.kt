package ru.shalkoff.textfieldscroll

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.shalkoff.textfieldscroll.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MainContent()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainContent() {
    Scaffold(
        modifier = Modifier
            .focusable()
            .systemBarsPadding(),
        topBar = {
            TopAppBar(
                title = { Text("Регистрация") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Gray
                )
            )
        },
        content = { innerPadding ->
            RegistrationContent(
                modifier = Modifier
                    .consumeWindowInsets(
                        PaddingValues(
                            bottom = innerPadding.calculateBottomPadding()
                        )
                    )
                    .imePadding()
                    .padding(innerPadding)
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .background(Color.Gray)
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
            ) {
                Button(
                    modifier = Modifier.align(Alignment.Center),
                    onClick = { }
                ) { Text("Далее") }
            }
        }
    )
}

@Composable
private fun RegistrationContent(
    modifier: Modifier
) {
    val states = remember {
        mutableStateListOf("", "", "", "", "", "", "", "", "")
    }
    val labels = listOf(
        "Имя", "Отчество", "Фамилия",
        "Логин", "Почта", "Адрес", "Дата рождения",
        "Пароль", "Повтор пароля"
    )
    LazyColumn(
        state = rememberLazyListState(),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        itemsIndexed(states) { i, _ ->
            TextField(value = states[i],
                modifier = Modifier.padding(top = 16.dp),
                onValueChange = {
                    states[i] = it
                },
                label = {
                    Text(labels[i])
                })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        MainContent()
    }
}