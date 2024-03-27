package com.winterry.nbcsignin.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatImageView
import com.winterry.nbcsignin.R
import kotlin.random.Random

class HomeActivity : AppCompatActivity() {

    private val imageList = listOf(
        R.drawable.g80,
        R.drawable.g80_front,
        R.drawable.g80_inside,
        R.drawable.g80_inside_2,
        R.drawable.g80_sport
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        findViewById<AppCompatImageView>(R.id.userImageView).let { view ->
            view.setImageDrawable(AppCompatResources.getDrawable(view.context, imageList[Random.nextInt(5)]))
        }

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