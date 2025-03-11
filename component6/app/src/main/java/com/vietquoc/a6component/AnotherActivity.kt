package com.vietquoc.a6component

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vietquoc.a6component.databinding.ActivityAnotherBinding

class AnotherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnotherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnotherBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}