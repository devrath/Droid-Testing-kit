package com.istudio.code.data

import com.istudio.code.domain.Product
import com.istudio.code.domain.ProductRepository

class ProductRepositoryFake : ProductRepository {

    private val purchasedProducts  = mutableListOf<Product>()

    // Using this we can set if the error is thrown in the fake and test the fake and test those scenarios
    var shouldReturnError = false

    override suspend fun purchaseProducts(products: List<Product>): Result<Unit> {
        purchasedProducts.addAll(products)
        if(shouldReturnError){
            return Result.failure(Exception())
        }else{
            return Result.success(Unit)
        }
    }

    override suspend fun cancelPurchase(purchaseId: String): Result<Unit> {
        purchasedProducts.removeIf{
            it.id == purchaseId.toInt()
        }
        if(shouldReturnError){
            return Result.failure(Exception())
        }else{
            return Result.success(Unit)
        }
    }
}