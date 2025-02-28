package com.vietquoc.ptb2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.vietquoc.ptb2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSolve.setOnClickListener {
            val a =
                binding.edtA.text
                    .toString()
                    .toDoubleOrNull()
            val b =
                binding.edtB.text
                    .toString()
                    .toDoubleOrNull()
            val c =
                binding.edtC.text
                    .toString()
                    .toDoubleOrNull()

            val result: String? = solve(a, b, c)

            if (result != null) {
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("RESULT", result)
                startActivity(intent)
            }
        }

        binding.btnClear.setOnClickListener {
            binding.edtA.text.clear()
            binding.edtB.text.clear()
            binding.edtC.text.clear()
        }
    }

    fun solve(
        a: Double?,
        b: Double?,
        c: Double?,
    ): String? {
        if (a == null || b == null || c == null) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ a, b, c", Toast.LENGTH_SHORT).show()
            return null
        }

        if (a == 0.0) {
            Toast.makeText(this, "Giá trị a phải khác 0", Toast.LENGTH_SHORT).show()
            return null
        }

        val delta = b * b - 4 * a * c
        val result =
            when {
                delta > 0 -> {
                    val x1 = (-b + Math.sqrt(delta)) / (2 * a)
                    val x2 = (-b - Math.sqrt(delta)) / (2 * a)
                    "x1 = $x1 \nx2 = $x2"
                }
                delta == 0.0 -> {
                    val x = -b / (2 * a)
                    "Nghiệm kép: x = $x"
                }
                else -> "Phương trình vô nghiệm"
            }

        return result
    }
}
