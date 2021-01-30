package com.example.grocery

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.grocery.data.Receipt


class ReceiptListActivity : AppCompatActivity() {


    private val receiptListViewModel: ReceiptListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipt_list)


        val receiptRecyclerView: RecyclerView = findViewById(R.id.receipt_recycler_view)
        val receiptAdapter = ReceiptAdapter()

        var receipts = emptyList<Receipt>()

        receiptRecyclerView.adapter = receiptAdapter

        receiptListViewModel.receiptListLiveData.observe(this, {
            it?.let {
                receiptAdapter.submitList(it as MutableList<Receipt>)
            }
        })




    }

}



/* PLS IGNORE THIS LEFTOVER DISCARDED STUFF


    private fun updateUI() {
        val receipts = receiptListViewModel.receipts
        adapter = ReceiptAdapter(receipts)
        receiptRecyclerView.adapter = adapter
    }


    private inner class ReceiptHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = itemView.findViewById(R.id.receipt_title)
        val dateTextView: TextView = itemView.findViewById(R.id.receipt_date)
    }

    private inner class ReceiptAdapter(var receipts: List<Receipt>) : RecyclerView.Adapter<ReceiptHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : ReceiptHolder {
            val view = layoutInflater.inflate(R.layout.list_item_receipt, parent, false)
            return ReceiptHolder(view)
        }
        override fun getItemCount() = receipts.size
        override fun onBindViewHolder(holder: ReceiptHolder, position: Int) {
            val receipt = receipts[position]
            holder.apply {
                titleTextView.text = receipt.title
                dateTextView.text = receipt.date.toString()
            }
        }
    }




}

 */