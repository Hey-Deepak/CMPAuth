import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import cmpauth.composeapp.generated.resources.Res
import cmpauth.composeapp.generated.resources.compose_multiplatform
import feature.home.HomeScreen


@Composable
@Preview
fun App() {
    MaterialTheme {

        Scaffold(
            topBar = {
                TopAppBar {
                    Text("CMP-Auth")
                }
            }
        ) { paddingValues ->
            Navigator(
                HomeScreen()
            ) { navigator ->
                SlideTransition(
                    navigator = navigator,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }

    }
}