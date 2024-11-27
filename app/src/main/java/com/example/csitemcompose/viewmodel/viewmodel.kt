package com.example.csitemcompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csitemcompose.model.dao.ItemDao
import com.example.csitemcompose.model.entity.Item
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(private val itemDao: ItemDao) : ViewModel() {

    val items: StateFlow<List<Item>> = itemDao.getAllItems()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val totalValue: StateFlow<Double> = itemDao.getTotalValue()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0.0
        )

    fun addItem(name: String, description: String, value: Double) {
        viewModelScope.launch {
            val newItem = Item(name = name, description = description, value = value)
            itemDao.insertItem(newItem)
        }
    }

    fun updateItem(item: Item) {
        viewModelScope.launch {
            itemDao.updateItem(item)
        }
    }

    fun deleteItem(item: Item) {
        viewModelScope.launch {
            itemDao.deleteItem(item)
        }
    }
}

