package com.example.csitemcompose.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.csitemcompose.model.database.AppDatabase
import com.example.csitemcompose.model.entity.Item
import com.example.csitemcompose.ui.theme.CsItemComposeTheme
import com.example.csitemcompose.viewmodel.MainViewModel
import com.example.csitemcompose.viewmodel.MainViewModelFactory


@Composable
fun ItemRow(item: Item, onEdit: (Item) -> Unit, onDelete: (Item) -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        tonalElevation = 4.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = item.name, style = MaterialTheme.typography.titleMedium)
                Text(text = item.description, style = MaterialTheme.typography.bodyMedium)
                Text(text = "Valor: R$ ${item.value}", style = MaterialTheme.typography.bodyMedium)
            }

            Row {
                IconButton(onClick = { onEdit(item) }) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Editar")
                }
                IconButton(onClick = { onDelete(item) }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Deletar")
                }
            }
        }
    }
}




@Composable
fun MainScreen(viewModel: MainViewModel) {
    val items by viewModel.items.collectAsState()
    val totalValue by viewModel.totalValue.collectAsState()
    var itemName by remember { mutableStateOf("") }
    var itemDescription by remember { mutableStateOf("") }
    var itemValue by remember { mutableStateOf("") }
    var editingItem by remember { mutableStateOf<Item?>(null) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Campo de entrada
        TextField(
            value = itemName,
            onValueChange = { itemName = it },
            label = { Text(if (editingItem == null) "Nome do Item" else "Editando: ${editingItem!!.name}") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        TextField(
            value = itemDescription,
            onValueChange = { itemDescription = it },
            label = { Text("Descrição do Item") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        TextField(
            value = itemValue,
            onValueChange = { itemValue = it },
            label = { Text("Valor da Arma") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )
        Button(
            onClick = {
                if (itemName.isNotBlank() && itemDescription.isNotBlank() && itemValue.isNotBlank()) {
                    val value = itemValue.toDoubleOrNull()
                    if (value != null) {
                        if (editingItem == null) {
                            viewModel.addItem(itemName, itemDescription, value)
                        } else {
                            viewModel.updateItem(editingItem!!.copy(name = itemName, description = itemDescription, value = value))
                            editingItem = null
                        }
                        itemName = ""
                        itemDescription = ""
                        itemValue = ""
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (editingItem == null) "Adicionar Item" else "Atualizar Item")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Exibir valor total
        Text(
            text = "Valor total das armas: R$ ${String.format("%.2f", totalValue)}",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Lista de itens
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(top = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items) { item ->
                ItemRow(
                    item = item,
                    onEdit = {
                        itemName = it.name
                        itemDescription = it.description
                        itemValue = it.value.toString()
                        editingItem = it
                    },
                    onDelete = { viewModel.deleteItem(it) }
                )
            }
        }
    }
}




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configurar o DAO e ViewModel
        val database = AppDatabase.getDatabase(applicationContext)
        val itemDao = database.itemDao()
        val viewModelFactory = MainViewModelFactory(itemDao)
        val mainViewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        setContent {
            CsItemComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(mainViewModel)
                }
            }
        }
    }
}
