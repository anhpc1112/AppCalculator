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
            numberTextViews = listOf(tvOne, tvTwo, tvThree, tvFour, tvFive, tvSix, tvSeven, tvEight, tvNine, tvZero, tvDot)
            actionTextViews = listOf(tvPlus,tvSub, tvMulti, tvDiv)
            tvClear.setOnClickListener(this@MainActivity)
            tvEquals.setOnClickListener(this@MainActivity)
            tvBack.setOnClickListener(this@MainActivity)
        }
        numberTextViews.forEach { it.setOnClickListener(this) }
        actionTextViews.forEach { it.setOnClickListener(this) }
    }

    override fun onClick(v: View?) = with(binding) {
        when (v){
            tvOne -> showExpression(getString(R.string.text_1))
            tvTwo -> showExpression(getString(R.string.text_2))
            tvThree -> showExpression(getString(R.string.text_3))
            tvFour -> showExpression(getString(R.string.text_4))
            tvFive -> showExpression(getString(R.string.text_5))
            tvSix -> showExpression(getString(R.string.text_6))
            tvSeven -> showExpression(getString(R.string.text_7))
            tvEight -> showExpression(getString(R.string.text_8))
            tvNine -> showExpression(getString(R.string.text_9))
            tvZero -> showExpression(getString(R.string.text_0))
            tvDot -> showExpression(getString(R.string.text_dot))

            tvPlus -> showExpression(getString(R.string.text_plus))
            tvSub -> showExpression(getString(R.string.text_sub))
            tvMulti -> showExpression(getString(R.string.text_mul))
            tvDiv -> showExpression(getString(R.string.text_div))

            tvClear -> clearExpression()
            tvBack -> backExpression()

            tvEquals -> showResult()
        }
    }

    private fun showExpression(string: String){
        with(binding){
            tvDisplay.append(string)
        }
    }

    private fun clearExpression(){
        with(binding){
            tvDisplay.text = getString(R.string.text_emp)
        }
    }
    private fun backExpression(){
        with(binding){
            val expression = tvDisplay.text.toString()
            if (expression.isNotEmpty()){
                tvDisplay.text = expression.substring(0, expression.length - 1)
            }

        }
    }
    private fun showResult(){
        with(binding){
            val expression = ExpressionBuilder(tvDisplay.text.toString()).build()
            val result = expression.evaluate()
            tvDisplay.text = result.toString()

        }
    }

}


