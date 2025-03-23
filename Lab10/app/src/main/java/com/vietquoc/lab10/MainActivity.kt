package com.vietquoc.lab10

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vietquoc.lab10.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel = CalculatorViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.result.observe(this) { result ->
            binding.txtResult.text = "Kết quả: $result"
        }

        binding.btnSum.setOnClickListener {
            val num1 = binding.edtNumber1.text.toString().toIntOrNull() ?: 0
            val num2 = binding.edtNumber2.text.toString().toIntOrNull() ?: 0
            viewModel.calculateSum(num1, num2)
        }

        binding.btnDifference.setOnClickListener {
            val num1 = binding.edtNumber1.text.toString().toIntOrNull() ?: 0
            val num2 = binding.edtNumber2.text.toString().toIntOrNull() ?: 0
            viewModel.calculateDifference(num1, num2)
        }
    }
}
