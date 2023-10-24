package com.istudio.code.domain

interface ShoppingCartCache {
    fun saveCart(items: List<Product>)
    fun loadCart(): List<Product>
    fun clearCart()
}