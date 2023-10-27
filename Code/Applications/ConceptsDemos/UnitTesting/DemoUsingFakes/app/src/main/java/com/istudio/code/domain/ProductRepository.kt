package com.istudio.code.domain

interface ProductRepository {
    suspend fun purchaseProducts(products: List<Product>): Result<Unit>
    suspend fun cancelPurchase(purchaseId: String): Result<Unit>
}