package com.egci428.firedatabase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        saveBtn.setOnClickListener {
            saveData()
        }
    }

    private fun saveData(){
        val msg = editText.text.toString()
        if(msg.isEmpty()){
            editText.error = "please enter something"
            return
        }
        val dataReference = FirebaseDatabase.getInstance().getReference("dataMsg")
        val messageId = dataReference.push().key
        val messageData = Message(messageId, msg, ratingBar.rating.toInt())
        dataReference.child(messageId).setValue(messageData).addOnCompleteListener {
            Toast.makeText(applicationContext, "message saved", Toast.LENGTH_SHORT).show()
        }
    }

}
