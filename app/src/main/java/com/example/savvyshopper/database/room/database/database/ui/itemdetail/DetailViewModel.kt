package com.example.savvyshopper.database.room.database.database.ui.itemdetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.savvyshopper.Category
import com.example.savvyshopper.Utils
import com.example.savvyshopper.database.room.database.database.Graph
import com.example.savvyshopper.database.room.database.database.Item
import com.example.savvyshopper.database.room.database.database.ShoppingList
import com.example.savvyshopper.repository.Repository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailViewModel
constructor(
        private val itemId:Int,
        private val repository: Repository = Graph.repository
):ViewModel() {

    var state by mutableStateOf(DetailState())
        private set

    init {
        addListItem()
        if (itemId != -1){
            viewModelScope.launch {
                repository.getItemWithList(itemId).collectLatest {
                    state = state.copy(
                        item = it.item.itemName,
                        quantity = it.item.quantity,
                        price = it.item.price,
                        category = Utils.category.find { c ->
                            c.id == it.shoppingList.id
                        } ?: Category()
                    )
                }
            }
        }
    }

    init {
        state = if (itemId != -1) {
            state.copy(isUpdatingItem = true)
        } else {
            state.copy(isUpdatingItem = false)
        }
    }

    val isFieldNotEmpty: Boolean
        // price is optional, user does not have to provide it
        get() = state.item.isNotEmpty() && state.quantity.isNotEmpty()

    fun onItemChange(newValue: String){
        state = state.copy(item = newValue)
    }

    fun onPriceChange(newValue: String){
        state = state.copy(price = newValue)
    }

    fun onQuantityChange(newValue: String){
        state = state.copy(quantity = newValue)
    }

    fun onCategoryChange(newValue: Category){
        state = state.copy(category = newValue)
    }

    private fun addListItem(){
        viewModelScope.launch {
            Utils.category.forEach {
                repository.insertList(
                    ShoppingList(
                        id = it.id,
                        name = it.title
                    )
                )
            }
        }
    }

    fun addShoppingItem(){
        viewModelScope.launch {
            repository.insertItem(
                Item(
                    itemName = state.item,
                    listIdForeignKey = state.category.id,
                    price = state.price,
                    quantity = state.quantity,
                    isChecked = false
                )
            )
        }
    }

    fun updateShoppingItem(id:Int){
        viewModelScope.launch {
            repository.updateItem(
                Item(
                    itemName = state.item,
                    listIdForeignKey = state.category.id,
                    price = state.price,
                    quantity = state.quantity,
                    isChecked = false,
                    id = id
                )
            )
        }
    }

}

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(private val id: Int):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(itemId = id) as T
    }
}

data class DetailState(
    val item: String = "",
    val price: String = "",
    val quantity: String = "",
    val isUpdatingItem:Boolean = false,
    val category: Category = Category()
)