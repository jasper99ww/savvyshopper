package com.example.savvyshopper.repository

import com.example.savvyshopper.database.room.database.database.Item
import com.example.savvyshopper.database.room.database.database.ItemDao
import com.example.savvyshopper.database.room.database.database.ListDao
import com.example.savvyshopper.database.room.database.database.ShoppingList

class Repository (
    private val listDao: ListDao,
    private val itemDao: ItemDao
) {
    val getItemsWithList = listDao.getItemsWithList()

    fun getItemWithList(id:Int) = listDao.getItemWithListFilteredById(id)
    fun getItemsWithListFilteredById(id: Int) = listDao.getItemsWithListFilteredById(id)

    suspend fun insertList(shoppingList: ShoppingList){
        listDao.insertShoppingList(shoppingList)
    }

    suspend fun insertItem(item: Item): Long {
        return itemDao.insertAndGetId(item)
    }

    suspend fun deleteItem(item: Item) {
        itemDao.delete(item)
    }

    suspend fun updateItem(item: Item) {
        itemDao.update(item)
    }

    suspend fun getLastInsertId(): Long {
        return itemDao.getLastInsertId()
    }
}