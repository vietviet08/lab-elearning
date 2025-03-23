package com.vietquoc.a6component

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.vietquoc.a6component.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MyAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MyAdapter
    private val items = mutableListOf("Item 1", "Item 2", "Item 3")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MyAdapter(items, this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.btnAdd.setOnClickListener {
            items.add("Item ${items.size + 1}")
            adapter.notifyItemInserted(items.size - 1)
        }
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, UpdateActivity::class.java)
        intent.putExtra("item_text", items[position])
        intent.putExtra("item_position", position)
        startActivityForResult(intent, REQUEST_UPDATE)
    }

    override fun onItemLongClick(position: Int) {
        AlertDialog.Builder(this)
            .setTitle("Xác nhận xóa")
            .setMessage("Bạn có chắc muốn xóa item này không?")
            .setPositiveButton("Xóa") { _, _ ->
                items.removeAt(position)
                adapter.notifyItemRemoved(position)
                Toast.makeText(this, "Đã xóa", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Hủy", null)
            .show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_UPDATE && resultCode == RESULT_OK) {
            val updatedText = data?.getStringExtra("updated_text")
            val position = data?.getIntExtra("item_position", -1)
            if (updatedText != null && position != null && position >= 0) {
                items[position] = updatedText
                adapter.notifyItemChanged(position)
            }
        }
    }

    companion object {
        const val REQUEST_UPDATE = 1
    }
}
