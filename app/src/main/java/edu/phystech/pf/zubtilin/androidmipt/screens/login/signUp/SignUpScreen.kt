package edu.phystech.pf.zubtilin.androidmipt.screens.login.signUp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import edu.phystech.pf.zubtilin.androidmipt.R
import edu.phystech.pf.zubtilin.androidmipt.screens.login.Logo


@Composable
fun SignUpScreen(signUpScreenViewModel: SignUpScreenViewModel = viewModel(), navController: NavController) {
    Column(
        modifier = Modifier.padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val state by signUpScreenViewModel.viewState.observeAsState()
        val viewState = state ?: return

        Logo()

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = stringResource(R.string.sign_up_label),
            style = TextStyle(fontSize = 20.sp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            singleLine = true,
            label = { Text(text = stringResource(R.string.username_label)) },
            value = viewState.username,
            onValueChange = { signUpScreenViewModel.obtainEvent(SignUpEvent.ChangeUsername(it), navController) },
            placeholder = {
                Text(
                    text = stringResource(R.string.username_placeholder),
                    color = Color.Gray
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.profile),
                    modifier = Modifier.size(30.dp),
                    contentDescription = "",
                    tint = Color.Green
                )
            }

        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            label = { Text(text = stringResource(R.string.email_label)) },
            value = viewState.email,
            onValueChange = { signUpScreenViewModel.obtainEvent(SignUpEvent.ChangeEmail(it), navController) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            placeholder = {
                Text(
                    text = stringResource(R.string.email_placeholder),
                    color = Color.Gray
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.message),
                    modifier = Modifier.size(30.dp),
                    contentDescription = "",
                    tint = Color.Green
                )
            }
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            label = { Text(text = stringResource(R.string.password_label)) },
            value = viewState.password,
            onValueChange = { signUpScreenViewModel.obtainEvent(SignUpEvent.ChangePassword(it), navController) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.lock),
                    modifier = Modifier.size(30.dp),
                    contentDescription = "",
                    tint = Color.Green
                )
            }
        )

        Spacer(modifier = Modifier.height(15.dp))

        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Row{
                Checkbox(
                    checked = viewState.keepSigned,
                    onCheckedChange = { signUpScreenViewModel.obtainEvent(SignUpEvent.ChangeKeepSigned, navController) },
                    colors = CheckboxDefaults.colors(checkedColor = Color(0xFF53E88B))
                )
                Text(
                    stringResource(R.string.agreement_1),
                    modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp),
                    color = Color.Gray
                )
            }

            Row{
                Checkbox(
                    checked = viewState.emailAboutPricing,
                    onCheckedChange = { signUpScreenViewModel.obtainEvent(SignUpEvent.ChangeEmailAboutPricing, navController) },
                    colors = CheckboxDefaults.colors(checkedColor = Color(0xFF53E88B))
                )
                Text(
                    stringResource(R.string.agreement_2),
                    modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp),
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = { signUpScreenViewModel.obtainEvent(SignUpEvent.SignUp, navController)},
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF53E88B)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = stringResource(R.string.sign_up))
            }
        }

        ClickableText(
            text = AnnotatedString(stringResource(R.string.agreement_3)),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(20.dp),
            onClick = { signUpScreenViewModel.obtainEvent(SignUpEvent.ToLogin, navController)},
            style = TextStyle(
                fontSize = 14.sp,
                //fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline,
                color = Color(0xFF53E88B),
            )
        )
    }
}