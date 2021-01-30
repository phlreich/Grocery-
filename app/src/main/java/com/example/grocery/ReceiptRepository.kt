package com.example.grocery

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.grocery.data.Receipt
import com.example.grocery.database.ReceiptDatabase
import java.lang.IllegalStateException
import java.util.*
import java.util.concurrent.Executors

private const val DATABASE_NAME = "receipt-database"

class ReceiptRepository private constructor(context: Context){

    private val database : ReceiptDatabase = Room.databaseBuilder(
        context.applicationContext,
        ReceiptDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val receiptDao = database.receiptDao()
    private val executor = Executors.newSingleThreadExecutor()

    fun getReceipts(): LiveData<List<Receipt>> = receiptDao.getReceipts()

    fun getReceipt(id: UUID): LiveData<Receipt?> = receiptDao.getReceipt(id)

    fun addReceipt(receipt: Receipt) {
        executor.execute {
            receiptDao.addReceipt(receipt)
        }
    }

    companion object {

        private var INSTANCE: ReceiptRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = ReceiptRepository(context)
            }
        }

        fun get(): ReceiptRepository {
            return INSTANCE ?:
            throw IllegalStateException("ReceiptRepository must be initialized")
        }
    }
}