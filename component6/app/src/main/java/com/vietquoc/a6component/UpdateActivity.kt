package com.vietquoc.a6component


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vietquoc.a6component.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private var itemPosition: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemText = intent.getStringExtra("item_text")
        itemPosition = intent.getIntExtra("item_position", -1)

        binding.editText.setText(itemText)

        binding.btnSave.setOnClickListener {
            val updatedText = binding.editText.text.toString()
            val resultIntent = Intent().apply {
                putExtra("updated_text", updatedText)
                putExtra("item_position", itemPosition)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
