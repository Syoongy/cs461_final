package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import java.io.FileNotFoundException
import java.util.Scanner

class MainActivity : AppCompatActivity() {
    private val usersMap = HashMap<String, String>()

    private val registerUserReceiver =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                val username = data?.getStringExtra("username")
                val pw = data?.getStringExtra("pw")
                if (username != null && pw != null) {
                    usersMap[username] = pw
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        populateUserMap()

        val registerBtn = findViewById<Button>(R.id.registerBtn)
        registerBtn.setOnClickListener { onRegisterBtnClick() }

        val loginBtn = findViewById<Button>(R.id.loginBtn)
        loginBtn.setOnClickListener { onLoginBtnClick() }
    }

    private fun populateUserMap() {
        val scanners = ArrayList<Scanner>()
        val usernamePWScanner = Scanner(resources.openRawResource(R.raw.username_password))
        scanners.add(usernamePWScanner)
        try {
            val extraUsersScanner = Scanner(openFileInput("extra_username_pw.txt"))
            scanners.add(extraUsersScanner)
        } catch (e: FileNotFoundException) {
        }

        for (scanner in scanners) {
            while (scanner.hasNextLine()) {
                val line = scanner.nextLine()
                val lineVals = line.split("\t")
                usersMap[lineVals[0]] = lineVals[1]
            }
            scanner.close()
        }
    }

    private fun onRegisterBtnClick() {
        val intent = Intent(this, RegisterActivity::class.java)
        registerUserReceiver.launch(intent)
    }

    private fun onLoginBtnClick() {
        val usernameET = findViewById<EditText>(R.id.usernameTextInput)
        val pwET = findViewById<EditText>(R.id.pwTextInput)
        val username = usernameET.text.toString()
        val pw = pwET.text.toString()
        val inputKey = username + pw
        val retPw = usersMap[username]

        if (retPw == null) {
            Toast.makeText(this, "User doesn't exist", Toast.LENGTH_SHORT).show()
            return
        }
        val retUserKey = username + retPw
        if (inputKey != retUserKey) {
            Toast.makeText(
                this, "Username and/or password is\n" +
                        "incorrect", Toast.LENGTH_SHORT
            ).show()
        } else {
            val intent = Intent(this, BookProfilesActivity::class.java)
            startActivity(intent)
        }
    }
}
