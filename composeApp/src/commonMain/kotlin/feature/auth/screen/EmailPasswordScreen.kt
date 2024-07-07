package feature.auth.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.launch


class EmailPasswordScreen : Screen{

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val scope = rememberCoroutineScope()
        val auth = remember { Firebase.auth }
        var firebaseUser: FirebaseUser? by remember { mutableStateOf(null) }
        var userEmail by remember { mutableStateOf("") }
        var userPassword by remember { mutableStateOf("") }




        if (firebaseUser == null)
        {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                TextField(
                    value = userEmail,
                    onValueChange = { userEmail = it },
                    placeholder = { Text("Email Id") }
                )

                Spacer(modifier = Modifier.height(12.dp))

                TextField(
                    value = userPassword,
                    onValueChange = { userPassword = it },
                    placeholder = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        scope.launch {
                            try {
                                auth.createUserWithEmailAndPassword(
                                    email = userEmail,
                                    password = userPassword
                                )
                                firebaseUser = auth.currentUser
                            } catch (e: Exception) {
                                auth.createUserWithEmailAndPassword(
                                    email = userEmail,
                                    password = userPassword
                                )
                            }
                        }
                    }
                ) {
                    Text("Sign in")
                }
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = firebaseUser?.uid ?: "Unknow ID")
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = {
                        scope.launch {
                            auth.signOut()
                            firebaseUser = auth.currentUser
                        }
                    }
                ) {
                    Text(text = "Sign Out")
                }
            }
        }
    }

}