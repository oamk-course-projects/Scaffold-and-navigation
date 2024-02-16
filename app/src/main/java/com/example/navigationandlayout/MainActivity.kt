package com.example.navigationandlayout

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.material.*
import androidx.compose.material.icons.filled.MoreVert
import androidx.navigation.NavController
import androidx.compose.runtime.Composable
import com.google.ai.client.generativeai.type.content

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationAndLayoutTheme {
                MyScaffoldLayout()
            }
        }
    }
}

@Composable
fun NavigationAndLayoutTheme(content: @Composable () -> Unit) {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar() {
    TopAppBar(
        title = { Text("SemicolonSpace") },
        actions = {
            IconButton(onClick = { /* Handle settings action */ }) {
                Icon(Icons.Filled.Settings, "Settings")
            }
        }
    )
}

@Composable
fun DropdownMenuItem(onClick: () -> Unit, content: @Composable () -> Unit) {
    // Implementation
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(title: String, navController: NavController) {
    var expanded by remember { mutableStateOf(false) }


    TopAppBar(
        title = { Text(title) },
        actions = {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(Icons.Filled.MoreVert, contentDescription = null)
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(onClick = { navController.navigate("info") }) {
                        Text("Info")

                    }
                    DropdownMenuItem(onClick = { navController.navigate("settings") }) {
                        Text("Settings")

                    }
                }
            }
        }
    )
    @Composable
    fun ScreenTopBar(title: String, navController: NavController) {
        TopAppBar(
            title = { Text(title) },
            actions = {
                // Actions if there are any
            }
        )
    }
}
fun  contentForScreen(navController: NavController) {
    // Content for the screen
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        topBar = { MainTopBar(title = "My App", navController = navController) },
        content= { contentForScreen(navController) }
    )
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = { ScreenTopBar(title = "Home", navController = navController) },
        content = { Text(text = "Content for Home screen") }
    )
}

@Composable
fun ScreenTopBar(title: String, navController: NavController) {
    TODO("Not yet implemented")
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InfoScreen(navController: NavController) {
    Scaffold(
        topBar = { ScreenTopBar(title = "Info", navController = navController) },
        content = { Text(text = "Content for Info screen") }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(
        topBar = { ScreenTopBar(title = "Settings", navController = navController) },
        content = { Text(text = "Content for Settings screen") }
    )
}

// Assuming `ScreenTopBar` is similar to `MainTopBar` with a different n}


@Composable
fun MyScaffoldLayout() {
    val contextForToast = LocalContext.current

    Scaffold(
        topBar = { MyTopAppBar() },
        bottomBar = { MyBottomBar(contextForToast) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Rest of the app UI")
        }
    }
}

@Composable
fun MyBottomBar(contextForToast: Context) {
    val bottomBarItemsList = listOf(
        BottomBarItem(icon = Icons.Filled.Home, name = "Home"),
        BottomBarItem(icon = Icons.Filled.Person, name = "Profile"),
        BottomBarItem(icon = Icons.Filled.Favorite, name = "Favorites")
    )

    var selectedItem by remember { mutableStateOf(0) }

    NavigationBar {
        bottomBarItemsList.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.name) },
                label = { Text(item.name) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    Toast.makeText(contextForToast, item.name, Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}

data class BottomBarItem(val icon: ImageVector, val name: String)
