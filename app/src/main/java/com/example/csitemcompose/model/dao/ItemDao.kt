package com.example.csitemcompose.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.csitemcompose.model.entity.Item
import kotlinx.coroutines.flow.Flow



@Dao
interface ItemDao {
    @Query("SELECT * FROM items")
    fun getAllItems(): Flow<List<Item>>

    @Insert
    suspend fun insertItem(item: Item)

    @Update
    suspend fun updateItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)

    // Soma dos valores de todas as armas
    @Query("SELECT SUM(value) FROM items")
    fun getTotalValue(): Flow<Double>
}
