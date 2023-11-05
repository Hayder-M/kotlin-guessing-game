package com.example.myapplication

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.util.Random

class mode_debutant : AppCompatActivity() {
    lateinit var mediaPlayer: MediaPlayer
    var secretNumber =0;
    var attempts = 0
    var tentative= ArrayList<Int>();


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mode_debutant)
        val random = Random()
        secretNumber = random.nextInt(100)
        mediaPlayer=MediaPlayer.create(this,R.raw.applaudissement)

    }

    fun verifier(view: View) {
        val nbr_input: EditText = findViewById(R.id.nbr);
        val nbr_essayer: TextView = findViewById(R.id.nrb_essayer);
        attempts++


        var nbrr = nbr_input.text.toString()
        var nbr = nbrr.toInt();
        println(this.secretNumber)
        println(nbr)
        tentative.add(nbr)
        println(tentative)

        if(nbr  == this.secretNumber) {
            val intent=Intent(this,MainActivity::class.java)
            val builder = AlertDialog.Builder(this)
            mediaPlayer.start()
            builder.setTitle("Bien")
            builder.setMessage("Tu as réussi en $attempts essais.")
            builder.setPositiveButton("Okay") { dialog, which ->
                dialog.dismiss()
                startActivity(intent)

            }
            val dialog = builder.create()
            dialog.show()
        }
        else{
            if(nbr>this.secretNumber)
            {
                Toast.makeText(this, "Plus Petit", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"Plus Grand",Toast.LENGTH_SHORT).show()
            }



            val tentativeText = tentative.joinToString(" / ")
            println(tentativeText)
            nbr_essayer.setText(tentativeText)

        }




    }
}