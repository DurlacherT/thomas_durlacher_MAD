package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.random.Random
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myRandomValues = (0..9).shuffled().take(4)
        println(myRandomValues)

        binding.calculateButton.setOnClickListener{ checkGuess(myRandomValues) }
    }

    private fun checkGuess(myRandomValues: List<out Int>)  {

        val input = binding.costOfService.text.toList()
        val myGuess = input.map { it.digitToInt() }
        if (myGuess.size != 4) {
            return
        }

        var n = 0
        var m = 0
        var j = 0
        for (i in myRandomValues) {
            if (i == myGuess[j]) {
                m++
            }
            j++
        }
        for (i in myRandomValues) {
            for (k in myGuess) {
                if (i == k) {
                    n++
                    break
                }
            }
        }

        binding.tipResult.text = getString(R.string.tip_amount, Pair(n, m))

        if (m == 4) {
            moveTaskToBack(true);
            exitProcess(-1)
        }
    return
    }
}



