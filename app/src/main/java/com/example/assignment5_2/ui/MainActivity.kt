package com.example.assignment5_2.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment5_2.data.User
import com.example.assignment5_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val users = mutableListOf(
        User("Umair", "Saeed", "umair@miu.com", "123456"),
        User("Saad", "khan", "saad@miu.com", "123456"),
        User("Bilal", "Amjad", "bilal@miu.com", "123456"),
        User("Mark", "Larry", "mark@miu.com", "123456"),
        User("david", "Warner", "david@miu.com", "123456")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val user: User? = intent.getSerializableExtra("user") as User?
        user?.let { users.add(it) }
    }

    fun onSign(view: View) {
        val email = binding.etEmail.text.toString().trim()
        val pass = binding.etPassword.text.toString().trim()
        if(email.isEmpty()){
            Toast.makeText(applicationContext,"Please enter your email", Toast.LENGTH_LONG).show()
            return
        }
        if(pass.isEmpty()){
            Toast.makeText(applicationContext,"Please enter your password", Toast.LENGTH_LONG).show()
            return
        }
        var isUserFound = false
        for(user in users){
            if(user.username == email && user.password == pass){
                isUserFound = true
                openShoppingCategoryActivity(user)
            }
        }
        if(!isUserFound){
            Toast.makeText(applicationContext,"Username and password not found.", Toast.LENGTH_LONG).show()
        }
    }

    private fun openShoppingCategoryActivity(user: User){
        val intent = Intent(this, ShoppingActivity::class.java)
        intent.putExtra("user", user)
        startActivity(intent)
    }

    fun onSignUp(view: View) {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    @SuppressLint("QueryPermissionsNeeded")
    fun onForgetPasswordClick(view: View) {
        val email = binding.etEmail.text.toString().trim()
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_EMAIL, email)
        intent.putExtra(Intent.EXTRA_SUBJECT, "Forgot Password from MDP Course")
        intent.putExtra(
            Intent.EXTRA_TEXT, "Forgot Password from MDP Course, " +
                "for resetting use this code: 123456.");
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }else{
            Toast.makeText(applicationContext,"No application found to complete the requested action", Toast.LENGTH_LONG).show()
        }
    }

}