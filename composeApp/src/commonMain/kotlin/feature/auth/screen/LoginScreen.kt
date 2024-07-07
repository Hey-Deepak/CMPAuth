package feature.auth.screen

import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import feature.auth.GoogleAuthProvider
import kotlinx.coroutines.launch
import model.GoogleUser


class LoginScreen : Screen {

    @Composable
    override fun Content() {
        var signedInUser: GoogleUser? by remember { mutableStateOf(null) }

        val googleAuthProvider = koinInject<GoogleAuthProvider>()
        val coroutineScope = rememberCoroutineScope()

        if (signedInUser == null) {

            GoogleButtonUiContainer(onGoogleSignInResult = { googleUser ->
                coroutineScope.launch {
                    signedInUser=googleUser
                }

            }) {
                Button(
                    onClick = { this.onClick() }
                ) {
                    Text("SignIn with Google")
                }
            }
        } else {
            Text("User Name: ${signedInUser?.displayName}")
            Button(
                onClick = {
                    coroutineScope.launch {
                        googleAuthProvider.signOut()
                        signedInUser = null
                    }

                }
            ) {
                Text("Sign Out")
            }
        }
    }


    interface GoogleButtonUiContainerScope {
        fun onClick()
    }

    @Composable
    fun GoogleButtonUiContainer(
        modifier: Modifier = Modifier,
        onGoogleSignInResult: (GoogleUser?) -> Unit,
        content: @Composable GoogleButtonUiContainerScope.() -> Unit,
    ) {
        val googleAuthProvider = koinInject<GoogleAuthProvider>()
        val googleAuthUiProvider = googleAuthProvider.getUiProvider()
        val coroutineScope = rememberCoroutineScope()
        val uiContainerScope = remember {
            object : GoogleButtonUiContainerScope {
                override fun onClick() {
                    coroutineScope.launch {
                        val googleUser = googleAuthUiProvider.signIn()
                        onGoogleSignInResult(googleUser)
                    }
                }
            }
        }
        Surface(
            modifier = modifier,
            content = { uiContainerScope.content() }
        )

    }


}