package com.vietquoc.ptb2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vietquoc.ptb2.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val result = intent.getStringExtra("RESULT")
        binding.txtResult.text = result

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}
