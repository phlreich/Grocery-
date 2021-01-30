package com.example.grocery.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.grocery.data.Receipt
import java.util.*

@Dao
interface ReceiptDao {

    @Query("SELECT * FROM receipt")
    fun getReceipts(): LiveData<List<Receipt>>

    @Query("SELECT * FROM receipt WHERE id=(:id)")
    fun getReceipt(id: UUID): LiveData<Receipt?>

    @Insert
    fun addReceipt(receipt: Receipt)
}