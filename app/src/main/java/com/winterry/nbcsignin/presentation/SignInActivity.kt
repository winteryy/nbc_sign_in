package com.winterry.nbcsignin.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.winterry.nbcsignin.R

class SignInActivity : AppCompatActivity() {

    private lateinit var idEditTextView: EditText
    private lateinit var passwordEditTextView: EditText
    private val signUpActivityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val userInfo: List<String> = result?.data?.getStringArrayListExtra(USER_INFO) ?: emptyList()
        if(result.resultCode == RESULT_OK && userInfo.isNotEmpty()) {
            idEditTextView.setText(userInfo[0])
            passwordEditTextView.setText(userInfo[1])
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        idEditTextView = findViewById(R.id.idInputEditText)
        passwordEditTextView = findViewById(R.id.passwordInputEditText)

        findViewById<Button>(R.id.signInButton).setOnClickListener {
            val idString = idEditTextView.text.toString()
            val passwordString = passwordEditTextView.text.toString()

            if(isValidSignIn(idString, passwordString)) {
                Toast.makeText(this, getString(R.string.sign_in_success), Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeActivity::class.java).apply {
                    putExtra(ID, idString)
                })
            }else {
                Toast.makeText(this, getString(R.string.sign_in_failure), Toast.LENGTH_SHORT).show()
            }

        }

        findViewById<Button>(R.id.signUpButton).setOnClickListener {
            signUpActivityResult.launch(Intent(this, SignUpActivity::class.java))
        }
    }

    private fun isValidSignIn(idString: String, passwordString: String): Boolean {
        return idString.isNotEmpty() && passwordString.isNotEmpty()
    }

    companion object {
        const val ID = "id"
        const val USER_INFO = "userInfo"
    }
}