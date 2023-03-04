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
 //test

    private fun checkGuess(myRandomValues: List<out Int>): Pair<Int, Int>  {

        var input = binding.costOfService.text.toList()
        var  myGuess = input.map { it.digitToInt() }

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

    return Pair(n, m)
    }


}



