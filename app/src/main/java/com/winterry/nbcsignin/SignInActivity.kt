package com.winterry.nbcsignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        findViewById<Button>(R.id.signInButton).setOnClickListener {
            val idString = findViewById<EditText>(R.id.idInputEditText).text.toString()
            val passwordString = findViewById<EditText>(R.id.passwordInputEditText).text.toString()

            if(isValidSignIn(idString, passwordString)) {
                Toast.makeText(this, getString(R.string.sign_in_success), Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeActivity::class.java).apply {
                    putExtra("id", idString)
                })
            }else {
                Toast.makeText(this, getString(R.string.sign_in_failure), Toast.LENGTH_SHORT).show()
            }

        }

        findViewById<Button>(R.id.signUpButton).setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    private fun isValidSignIn(idString: String, passwordString: String): Boolean {
        return idString.isNotEmpty() && passwordString.isNotEmpty()
    }
}