package com.vietquoc.clv

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.vietquoc.clv.activities.AdvanceListView
import com.vietquoc.clv.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var lstStudent: ArrayList<String> = ArrayList<String>()
        lstStudent.add("Nguyễn Văn Tèo")
        lstStudent.add("Trần Chót")
        lstStudent.add("Nguyễn Thị Mít")

        val studentAdapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, lstStudent)

        binding.lvStudent.adapter = studentAdapter

        binding.btnAdd.setOnClickListener {
            val fullname = binding.edtName.text.toString().trim()
            if (TextUtils.isEmpty(fullname)) {
                Toast.makeText(
                    this, "Bạn chưa nhập dữ liệu!", Toast.LENGTH_SHORT
                ).show()
            } else {
                lstStudent.add(fullname)
                binding.edtName.setText("")
                studentAdapter.notifyDataSetChanged()
                Toast.makeText(this, "Thêm thành công !", Toast.LENGTH_SHORT).show()
            }
        }

        binding.lvStudent.setBackgroundColor(Color.parseColor("#ffff00"))

        var pos: Int = 0

        binding.lvStudent.setOnItemClickListener { parent, view, position, id ->
            binding.edtName.setText(lstStudent.get(position))
            pos = position
        }

        binding.lvStudent.setOnItemLongClickListener { parent, view, position, id ->
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Xác nhận xóa")
            alertDialog.setMessage("Bạn có muốn xóa không ?")
            alertDialog.setPositiveButton("Đồng ý", DialogInterface.OnClickListener { dialog, which ->
                lstStudent.remove(lstStudent.get(position))
                studentAdapter.notifyDataSetChanged()
                Toast.makeText(this, "Xóa thành công !", Toast.LENGTH_SHORT).show()
            })
            alertDialog.setNegativeButton("Không", DialogInterface.OnClickListener { dialog, which ->
                alertDialog.setCancelable(true)
            })
            alertDialog.create()
            alertDialog.show()
            return@setOnItemLongClickListener false
        }

        binding.btnAdvanceListView.setOnClickListener {
            val intent = Intent(this, AdvanceListView::class.java)
            startActivity(intent)
        }

        binding.btnUpdate.setOnClickListener {
            val fullname = binding.edtName.text.toString().trim()
            lstStudent.set(pos, fullname.toString())
            studentAdapter.notifyDataSetChanged()
            Toast.makeText(this, "Cập nhật thành công !", Toast.LENGTH_SHORT).show()
        }
    }
}