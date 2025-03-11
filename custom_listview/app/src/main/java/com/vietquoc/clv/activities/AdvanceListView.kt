package com.vietquoc.clv.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vietquoc.clv.R
import com.vietquoc.clv.adapter.StudentAdapter
import com.vietquoc.clv.databinding.ActivityAdvanceListViewBinding
import com.vietquoc.clv.model.Student

class AdvanceListView : AppCompatActivity() {
    private lateinit var binding: ActivityAdvanceListViewBinding
    private var lstStudent = ArrayList<Student>()

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdvanceListViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        if (lstStudent.isEmpty()) {
            lstStudent.add(Student(1, "Lê Thị Mơ"))
            lstStudent.add(Student(2, "Nguyễn Văn Mệt"))
            lstStudent.add(Student(3, "Phạm Văn Đói"))
            val studentAdapter = StudentAdapter(this, R.layout.row_student, lstStudent)
            binding.lvStudentNew.adapter = studentAdapter
        }
        binding.btnAddStudent.setOnClickListener {
            val myIntent = Intent(this, AddStudentActivity::class.java)
            myIntent.putExtra("lstStudent", lstStudent)
            startActivityForResult(myIntent, 200)
        }
    }

    @Suppress("DEPRECATION")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 200) {
            if (resultCode == Activity.RESULT_OK) {
                val bundle = data!!.extras
                lstStudent = bundle!!.getSerializable("lstStudent") as ArrayList<Student>
                val studentAdapter = StudentAdapter(this, R.layout.row_student, lstStudent)
                binding.lvStudentNew.adapter = studentAdapter
            }
        }   
    }
}