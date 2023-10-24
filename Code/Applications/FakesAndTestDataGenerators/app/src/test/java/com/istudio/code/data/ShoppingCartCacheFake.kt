package com.istudio.code.data

import com.istudio.code.domain.Product
import com.istudio.code.domain.ShoppingCartCache

class ShoppingCartCacheFake : ShoppingCartCache {

    private var items = emptyList<Product>()

    override fun saveCart(items: List<Product>) {
        this.items = items
    }

    override fun loadCart(): List<Product> {
        return items
    }

    override fun clearCart() {
        items = emptyList()
    }
}