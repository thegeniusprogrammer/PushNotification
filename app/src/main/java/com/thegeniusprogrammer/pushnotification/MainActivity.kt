package com.thegeniusprogrammer.pushnotification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessaging
import com.thegeniusprogrammer.pushnotification.api.RetrofitInstance
import com.thegeniusprogrammer.pushnotification.databinding.ActivityMainBinding
import com.thegeniusprogrammer.pushnotification.modelclass.NotificationData
import com.thegeniusprogrammer.pushnotification.modelclass.PushNotification
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MainActivity : AppCompatActivity() {



    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//         Topic behave as a Token, we can pass topic in the place of token
//        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)

        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            binding.editTextTextPersonName3.setText(it)


        }

        binding.button.setOnClickListener {
            val title = binding.editTextTextPersonName.text.toString()
            val desc = binding.editTextTextPersonName2.text.toString()
            val token = binding.editTextTextPersonName3.text.toString()
            if (title.isNotEmpty() && desc.isNotEmpty() && token.isNotEmpty()){
                PushNotification(NotificationData(title,desc), token).also {
                    sendNotification(it)
                }
            }
        }












    }



    private fun sendNotification(notification: PushNotification) = CoroutineScope(IO).launch {
        try {
            val response = RetrofitInstance.api.sendNotification(notification)
            withContext(Main){
                if (response.isSuccessful){

                    Toast.makeText(applicationContext, "Success", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
                }
            }
         

        }catch (e:Exception){
            withContext(Main){
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()

            }
        }
    }
}