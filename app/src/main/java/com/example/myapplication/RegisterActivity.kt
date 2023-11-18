package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.PrintStream
import java.util.Scanner

class RegisterActivity : AppCompatActivity() {
    private val existingUsernamesList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        loadUsernames()
        val registerBtn = findViewById<Button>(R.id.registerNewBtn)
        registerBtn.setOnClickListener { onRegisterBtnClick() }
    }

    private fun onRegisterBtnClick() {
        val usernameET = findViewById<EditText>(R.id.newUsernameTextInput)
        val username = usernameET.text.toString()

        // If the username already exists we make a toast and return early
        if (existingUsernamesList.contains(username)) {
            Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show()
            return
        }

        val pwET = findViewById<EditText>(R.id.newPwTextInput)
        val confirmPWET = findViewById<EditText>(R.id.pwConfirmTextInput)
        val pw = pwET.text.toString()
        val confirmPW = confirmPWET.text.toString()

        // If the passwords do not match, we make a toast and return early
        if (pw != confirmPW) {
            Toast.makeText(this, "Password mismatch", Toast.LENGTH_SHORT).show()

            return
        }

        // We now register the user
        registerUser(username, pw)
    }

    private fun loadUsernames() {
        val usernamePWScanner = Scanner(resources.openRawResource(R.raw.username_password))
        while (usernamePWScanner.hasNextLine()) {
            val line = usernamePWScanner.nextLine()
            val lineVals = line.split("\t")
            existingUsernamesList.add(lineVals[0])
        }
        usernamePWScanner.close()
    }

    private fun registerUser(username: String, pw: String) {
        val user = "$username\t$pw"
        val outStream = PrintStream(openFileOutput("extra_username_pw.txt", MODE_APPEND))
        outStream.println(user)
        outStream.close()

        val intent = Intent()
        intent.putExtra("username", username)
        intent.putExtra("pw", pw)
        setResult(Activity.RESULT_OK, intent)

        finish()
    }
}