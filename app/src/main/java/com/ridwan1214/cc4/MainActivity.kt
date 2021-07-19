package com.ridwan1214.cc4

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity(), CallBack {

    private var pilihanPlayer: String? = null
    private var pilihanCom: String? = null
    private val result by lazy { findViewById<ImageView>(R.id.ivVersus) }
    private val btnBatu by lazy { findViewById<ImageView>(R.id.ivBatu) }
    private val btnKertas by lazy { findViewById<ImageView>(R.id.ivKertas) }
    private val btnGunting by lazy { findViewById<ImageView>(R.id.ivGunting) }
    private val btnComBatu by lazy { findViewById<ImageView>(R.id.ivComBatu) }
    private val btnComKertas by lazy { findViewById<ImageView>(R.id.ivComKertas) }
    private val btnComGunting by lazy { findViewById<ImageView>(R.id.ivComGunting) }
    private val btnReset by lazy { findViewById<ImageView>(R.id.ivReset) }

    private val controller = Controller(this)

    private var finish: Boolean = false

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playerPicked = mutableMapOf(
            R.id.ivBatu to "batu",
            R.id.ivKertas to "kertas",
            R.id.ivGunting to "gunting"
        )

        btnReset.setOnClickListener { reset() }

        playerPicked.forEach { (i, value) ->
            run {
                findViewById<ImageView>(i).setOnClickListener {
                    if (!finish) {
                        it.isClickable = false
                        it.backgroundTintList =
                            ContextCompat.getColorStateList(this@MainActivity, R.color.softblue)
                        pilihanPlayer = value
                        pilihanCom = computerPick()
                        controller.logicGame(pilihanPlayer, pilihanCom)
                        finish = true
                    }
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun computerPick(): String {
        val option = arrayOf("batu", "kertas", "gunting")
        val comPick = option.random()
        when (comPick) {
            "batu" -> btnComBatu.backgroundTintList =
                ContextCompat.getColorStateList(this@MainActivity, R.color.softblue)
            "kertas" -> btnComKertas.backgroundTintList =
                ContextCompat.getColorStateList(this@MainActivity, R.color.softblue)
            "gunting" -> btnComGunting.backgroundTintList =
                ContextCompat.getColorStateList(this@MainActivity, R.color.softblue)
        }
        return comPick
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun reset() {
        btnGunting.isClickable = true
        btnGunting.backgroundTintList =
            ContextCompat.getColorStateList(this@MainActivity, R.color.white)
        btnComGunting.backgroundTintList =
            ContextCompat.getColorStateList(this@MainActivity, R.color.white)
        btnComBatu.backgroundTintList =
            ContextCompat.getColorStateList(this@MainActivity, R.color.white)
        btnBatu.isClickable = true
        btnBatu.backgroundTintList =
            ContextCompat.getColorStateList(this@MainActivity, R.color.white)
        btnComBatu.backgroundTintList =
            ContextCompat.getColorStateList(this@MainActivity, R.color.white)
        btnKertas.isClickable = true
        btnKertas.backgroundTintList =
            ContextCompat.getColorStateList(this@MainActivity, R.color.white)
        btnComKertas.backgroundTintList =
            ContextCompat.getColorStateList(this@MainActivity, R.color.white)
        pilihanPlayer = ""
        pilihanCom = ""
        result.setImageResource(R.drawable.vs)
        finish = false
    }

    override fun check(hasil: String) {
        when (hasil) {
            "1" -> result.setImageResource(R.drawable.p1menang)
            "2" -> result.setImageResource(R.drawable.p2menang)
            "3" -> result.setImageResource(R.drawable.draw)
        }
        Log.d("Player1","Pemain memilih $pilihanPlayer")
        Log.d("Com","Computer memilih $pilihanCom")
        Log.d("hasil","hasilnya $hasil")
        Log.d("hasil","1= Pemain1 Menang, 2= Com Menang, 3= Draw")
    }
}