package com.winterry.nbcsignin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        findViewById<Button>(R.id.signUpButton).setOnClickListener {
            val nameString = findViewById<EditText>(R.id.nameInputEditText).text.toString()
            val idString = findViewById<EditText>(R.id.idInputEditText).text.toString()
            val passwordString = findViewById<EditText>(R.id.passwordInputEditText).text.toString()

            if(isValidSignUp(nameString, idString, passwordString)) {
                finish()
            }else {
                Toast.makeText(this, getString(R.string.sign_up_failure), Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun isValidSignUp(nameString: String, idString: String, passwordString: String): Boolean {
        return nameString.isNotEmpty() && idString.isNotEmpty() && passwordString.isNotEmpty()
    }
}