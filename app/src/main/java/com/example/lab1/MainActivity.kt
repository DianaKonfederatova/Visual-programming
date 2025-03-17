package com.example.lab1

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : ComponentActivity() {
    private var log_tag: String = "MY_LOG_TAG"
    private lateinit var textViewInput: EditText
    private lateinit var textViewResult: TextView
    private lateinit var buttonAdd: Button
    private lateinit var buttonSubtract: Button
    private lateinit var buttonDivision: Button
    private lateinit var buttonMultiplication: Button
    private lateinit var buttonEquals: Button
    private var firstOperand: Double?=null
    private var secondOperand: Double?=null
    private var currentOperator: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.calculator)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        textViewInput = findViewById(R.id.textView)
        textViewResult = findViewById(R.id.textView2)
        buttonAdd = findViewById(R.id.button1)
        buttonSubtract = findViewById(R.id.button2)
        buttonDivision = findViewById(R.id.button3)
        buttonMultiplication = findViewById(R.id.button4)
        buttonEquals = findViewById(R.id.button5)
    }

    // onStart() – вызывается перед тем, как Activity будет видно пользователю
    override fun onStart() {
        super.onStart()
        Log.d(log_tag, "onStart method")
    }
    // onResume() – вызывается перед тем как будет доступно для активности пользователя (взаимодействие)
    override fun onResume() {
        super.onResume()
        Log.d(log_tag, "onResume method")

        buttonAdd.setOnClickListener{
            val inputText = textViewInput.text.toString()
            if (inputText.isNotEmpty()) {
                try {
                    firstOperand = inputText.toDouble()
                    currentOperator = "+"
                    textViewInput.text.clear()
                }
                catch (e: NumberFormatException) {
                    Log.e(log_tag, "NumberFormatException", e)
                    textViewResult.text = "введенные данные неверны"
                }
            }
        }

        buttonSubtract.setOnClickListener{
            val inputText = textViewInput.text.toString()
            if (inputText.isNotEmpty()){
                try {
                    firstOperand = inputText.toDouble()
                    currentOperator="-"
                    textViewInput.text.clear()
                }
                catch (e: NumberFormatException) {
                    Log.e(log_tag, "NumberFormatException", e)
                    textViewResult.text = "введенные данные неверны"
                }
            }
        }

        buttonMultiplication.setOnClickListener{
            val inputText = textViewInput.text.toString()
            if (inputText.isNotEmpty()){
                try {
                    firstOperand = inputText.toDouble()
                    currentOperator = "*"
                    textViewInput.text.clear()
                }
                catch (e: NumberFormatException) {
                    Log.e(log_tag, "NumberFormatException", e)
                    textViewResult.text = "введенные данные неверны"
                }
            }
        }

        buttonDivision.setOnClickListener{
            val inputText = textViewInput.text.toString()
            if (inputText.isNotEmpty()){
                try {
                    firstOperand = inputText.toDouble()
                    currentOperator = "/"
                    textViewInput.text.clear()
                }
                catch (e: NumberFormatException) {
                    Log.e(log_tag, "NumberFormatException", e)
                    textViewResult.text = "введенные данные неверны"
                }
            }
        }

        buttonEquals.setOnClickListener{
            val inputText = textViewInput.text.toString()
            if (inputText.isNotEmpty()){
                if (firstOperand!=null && currentOperator!=""){
                    try{
                        secondOperand = inputText.toDouble()
                    }
                    catch (e: NumberFormatException){
                        Log.e(log_tag, "NumberFormatException", e)
                        textViewResult.text = "введенные данные неверны"
                    }
                }
                if (firstOperand!=null && currentOperator!="" && secondOperand!=null){
                    try{
                        var result:Double? = null
                        when (currentOperator){
                            "+" -> result = firstOperand!! + secondOperand!!
                            "-" -> result = firstOperand!! - secondOperand!!
                            "*" -> result = firstOperand!! * secondOperand!!
                            "/" -> {
                                if (secondOperand == 0.0) {
                                    textViewResult.text = "Деление на ноль"
                                    Log.w(log_tag, "Деление на ноль")
                                    return@setOnClickListener
                                } else {
                                    result = firstOperand!! / secondOperand!!
                                }
                            }
                        }
                        textViewResult.text = result.toString()
                        firstOperand = null
                        secondOperand = null
                        currentOperator = ""
                        textViewInput.text.clear()
                    } catch (e: Exception) {
                        Log.e(log_tag, "NumberFormatException", e)
                        textViewResult.text = "Ошибка"
                    }
                }

            }
        }
    }
}

