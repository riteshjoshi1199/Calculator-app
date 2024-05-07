package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.calculator.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var currentSymbol: Char = ' '

    private var firstValue = Double.NaN
    private var secondValue = 0.0
    private lateinit var decimalFormat: DecimalFormat
    private lateinit var inputDisplay: TextView
    private lateinit var outputDisplay: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        decimalFormat = DecimalFormat("#.##########")
        inputDisplay = findViewById(R.id.input)
        outputDisplay = findViewById(R.id.output)

        binding.apply {
            btn0.setOnClickListener { inputDisplay.append("0") }
            btn1.setOnClickListener { inputDisplay.append("1") }
            btn2.setOnClickListener { inputDisplay.append("2") }
            btn3.setOnClickListener { inputDisplay.append("3") }
            btn4.setOnClickListener { inputDisplay.append("4") }
            btn5.setOnClickListener { inputDisplay.append("5") }
            btn6.setOnClickListener { inputDisplay.append("6") }
            btn7.setOnClickListener { inputDisplay.append("7") }
            btn8.setOnClickListener { inputDisplay.append("8") }
            btn9.setOnClickListener { inputDisplay.append("9") }

            add.setOnClickListener {
                allCalculations()
                currentSymbol = '+'
                outputDisplay.text = "${decimalFormat.format(firstValue)}+"
                inputDisplay.text = null
            }

            subtract.setOnClickListener {
                allCalculations()
                currentSymbol = '-'
                outputDisplay.text = "${decimalFormat.format(firstValue)}-"
                inputDisplay.text = null
            }

            multiply.setOnClickListener {
                allCalculations()
                currentSymbol = '*'
                outputDisplay.text = "${decimalFormat.format(firstValue)}x"
                inputDisplay.text = null
            }

           division.setOnClickListener {
                allCalculations()
                currentSymbol = '/'
                outputDisplay.text = "${decimalFormat.format(firstValue)}/"
                inputDisplay.text = null
            }

            percent.setOnClickListener {
                allCalculations()
                currentSymbol = '%'
                outputDisplay.text = "${decimalFormat.format(firstValue)}%"
                inputDisplay.text = null
            }

           btnPoint.setOnClickListener { inputDisplay.append(".") }

            clear.setOnClickListener {
                if (inputDisplay.text.isNotEmpty()) {
                    inputDisplay.text = inputDisplay.text.dropLast(1)
                } else {
                    firstValue = Double.NaN
                    secondValue = Double.NaN
                    inputDisplay.text = ""
                    outputDisplay.text = ""
                }
            }

            off.setOnClickListener { finish() }

            equal.setOnClickListener {
                allCalculations()
                outputDisplay.text = decimalFormat.format(firstValue)
                firstValue = Double.NaN
                currentSymbol = ' '
            }
        }
    }

    private fun allCalculations() {
        if (!firstValue.isNaN()) {
            secondValue = binding.input.text.toString().toDouble()
            binding.input.text = null

            firstValue = when (currentSymbol) {
                '+' -> firstValue + secondValue
                '-' -> firstValue - secondValue
                '*' -> firstValue * secondValue
                '/' -> firstValue / secondValue
                '%' -> firstValue % secondValue
                else -> firstValue
            }
        } else {
            try {
                firstValue = binding.input.text.toString().toDouble()
            } catch (e: Exception) {
                // Handle exception
            }
        }
    }
}
