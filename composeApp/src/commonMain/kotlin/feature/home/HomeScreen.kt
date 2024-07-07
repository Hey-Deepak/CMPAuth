package feature.home

import feature.auth.screen.EmailPasswordScreen
import feature.auth.screen.LoginScreen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

class HomeScreen: Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val scope = rememberCoroutineScope()

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(
                onClick = {
                   navigator.push(LoginScreen())
                }
            ){
                Text("Login with Gmail")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                   navigator.push(EmailPasswordScreen())
                }
            ){
                Text("Login with Email-Password")
            }

        }

    }
}