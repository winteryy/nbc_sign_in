package com.winterry.nbcsignin.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.winterry.nbcsignin.R
import com.winterry.nbcsignin.domain.UserDataValidator
import com.winterry.nbcsignin.presentation.model.UserEvent
import com.winterry.nbcsignin.presentation.util.asText
import com.winterry.nbcsignin.presentation.viewmodel.SignUpViewModel
import com.winterry.nbcsignin.presentation.viewmodel.SignUpViewModelFactory

class SignUpActivity: AppCompatActivity() {

    private val signUpViewModel: SignUpViewModel by viewModels {
        SignUpViewModelFactory(UserDataValidator())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        setObservers()
        setClickListeners()
    }

    private fun setClickListeners() {
        findViewById<Button>(R.id.signUpButton).setOnClickListener {
            val nameString = findViewById<EditText>(R.id.nameInputEditText).text.toString()
            val emailString = findViewById<EditText>(R.id.emailInputEditText).text.toString()
            val idString = findViewById<EditText>(R.id.idInputEditText).text.toString()
            val passwordString = findViewById<EditText>(R.id.passwordInputEditText).text.toString()
            val passwordConfirmString = findViewById<EditText>(R.id.passwordConfirmInputEditText).text.toString()

            signUpViewModel.registerAuth(nameString, emailString, idString, passwordString, passwordConfirmString)
        }
    }

    private fun setObservers() {
        signUpViewModel.userEvent.observe(this) { event ->
            when (event) {
                is UserEvent.Error -> {
                    makeToast(event.msg)
                }

                is UserEvent.RegisterSuccess -> {
                    val userInfo = event.user
                    makeToast(userInfo.asText())
                    val intent = Intent().putExtra(
                        SignInActivity.USER_INFO,
                        arrayListOf(userInfo.id, userInfo.password)
                    )
                    setResult(RESULT_OK, intent)
                    finish()
                }

                UserEvent.None -> {}
                UserEvent.Success -> {}
            }
        }
    }

    private fun makeToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}