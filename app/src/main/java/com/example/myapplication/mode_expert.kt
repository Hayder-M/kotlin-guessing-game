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
import java.util.*

import android.os.CountDownTimer
import android.os.Handler


class mode_expert : AppCompatActivity() {
    lateinit var mediaPlayer: MediaPlayer
    var secretNumber = 0
    var attempts = 0
    var gameEnded = false
    lateinit var temps:TextView
    lateinit var timer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mode_expert)
        temps=findViewById(R.id.tempss)

        mediaPlayer=MediaPlayer.create(this,R.raw.applaudissement)

        val random = Random()
        secretNumber = random.nextInt(100)

        val countDownTimer = object : CountDownTimer(20000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                temps.text = "$secondsRemaining"
            }

            override fun onFinish() {
                temps.text = "Terminé!"
            }
        }

        countDownTimer.start()

        val intent = Intent(this, MainActivity::class.java)
        val handler = Handler()
        handler.postDelayed({
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Temps écoulé a la prochaine")
            builder.setPositiveButton("OK") { dialog, which ->
                dialog.dismiss()
                startActivity(intent)
            }
            val dialog = builder.create()
            dialog.show()

        }, 20000)


    }

        fun verifier_exp(view: View) {

            println(secretNumber)

            val nbr_input: EditText = findViewById(R.id.nbr)

            val nbr = nbr_input.text.toString().toInt()

            attempts++

            if (nbr == secretNumber) {

                val builder = AlertDialog.Builder(this)

                mediaPlayer.start()

                builder.setTitle("Bien")
                builder.setMessage("Tu as réussi en $attempts essais.")
                builder.setPositiveButton("Okay") { dialog, which ->
                    dialog.dismiss()
                }
                val dialog = builder.create()
                dialog.show()

                val intent = Intent(this, MainActivity::class.java)
                val handler = Handler()
                handler.postDelayed({
                    startActivity(intent)
                    finish()
                }, 1500)
            } else {
                if(nbr>this.secretNumber)
                {
                    Toast.makeText(this, "Plus Petit", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this,"Plus Grand",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

