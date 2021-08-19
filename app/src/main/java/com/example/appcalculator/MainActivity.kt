package com.example.appcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.appcalculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var numberTextViews = listOf<TextView>()
    private var actionTextViews = listOf<TextView>()
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val view = binding.root
        setContentView(view)
        initViews()
    }
    private fun initViews() {
        with(binding) {
            numberTextViews = listOf(textOne, textTwo, textThree, textFour, textFive, textSix, textSeven, textEight, textNine, textZero, textDot)
            actionTextViews = listOf(textPlus, textSub, textMulti, textDiv)
            textClear.setOnClickListener(this@MainActivity)
            textEquals.setOnClickListener(this@MainActivity)
            textBack.setOnClickListener(this@MainActivity)
        }
        numberTextViews.forEach { it.setOnClickListener(this) }
        actionTextViews.forEach { it.setOnClickListener(this) }
    }

    override fun onClick(v: View?) = with(binding) {
        when (v){
            textOne -> showExpression(getString(R.string.text_1))
            textTwo -> showExpression(getString(R.string.text_2))
            textThree -> showExpression(getString(R.string.text_3))
            textFour -> showExpression(getString(R.string.text_4))
            textFive -> showExpression(getString(R.string.text_5))
            textSix -> showExpression(getString(R.string.text_6))
            textSeven -> showExpression(getString(R.string.text_7))
            textEight -> showExpression(getString(R.string.text_8))
            textNine -> showExpression(getString(R.string.text_9))
            textZero -> showExpression(getString(R.string.text_0))
            textDot -> showExpression(getString(R.string.text_dot))

            textPlus -> showExpression(getString(R.string.text_plus))
            textSub -> showExpression(getString(R.string.text_sub))
            textMulti -> showExpression(getString(R.string.text_mul))
            textDiv -> showExpression(getString(R.string.text_div))

            textClear -> clearExpression()
            textBack -> backExpression()
            textEquals -> showResult()
        }
    }

    private fun showExpression(string: String){
        with(binding){
            textDisplay.append(string)
        }
    }

    private fun clearExpression(){
        with(binding){
            textDisplay.text = ""
        }
    }
    private fun backExpression(){
        with(binding){
            val expression = textDisplay.text.toString()
            if (expression.isNotEmpty()){
                textDisplay.text = expression.substring(0, expression.length - 1)
            }

        }
    }
    private fun showResult(){
        with(binding){
            val expression = ExpressionBuilder(textDisplay.text.toString()).build()
            val result = expression.evaluate()

            textDisplay.text = result.toString()

        }
    }

}


