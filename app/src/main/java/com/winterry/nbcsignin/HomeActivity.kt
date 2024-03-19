package com.winterry.nbcsignin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        findViewById<TextView>(R.id.idTextView).text = try {
            "아이디: ${intent.getStringExtra(SignInActivity.ID)}"
        } catch (e: Exception) {
            "아이디: None"
        }

        findViewById<Button>(R.id.finishButton).setOnClickListener {
            finish()
        }

    }

}