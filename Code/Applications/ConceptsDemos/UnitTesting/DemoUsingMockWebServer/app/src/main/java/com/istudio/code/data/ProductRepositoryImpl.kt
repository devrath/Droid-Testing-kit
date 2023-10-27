package com.istudio.code.data

import com.istudio.code.domain.AnalyticsLogger
import com.istudio.code.domain.CustomProduct
import com.istudio.code.domain.LogParam
import com.istudio.code.domain.Product
import com.istudio.code.domain.ProductRepository
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.CancellationException

class ProductRepositoryImpl(
    private val productApi: ProductApi,
    private val analyticsLogger: AnalyticsLogger
): ProductRepository {

    override suspend fun purchaseProducts(products: List<Product>): Result<Unit> {
        return try {

            // Observe that we cannot pass the custom Product in constructor since the functionality does not provide it
            val custProduct = CustomProduct(id = 1, name = "Power Ranger", price = 10.9)
            println(custProduct.name)

            productApi.purchaseProducts(
                products = ProductsDto(products)
            )
            Result.success(Unit)
        } catch (e: HttpException) {
            analyticsLogger.logEvent(
                "http_error",
                LogParam("code", e.code()),
                LogParam("message", e.message()),
            )
            Result.failure(e)
        } catch(e: IOException) {
            analyticsLogger.logEvent(
                "io_error",
                LogParam("message", e.message.toString())
            )
            Result.failure(e)
        } catch (e: Exception) {
            if(e is CancellationException) throw e
            Result.failure(e)
        }
    }

    override suspend fun cancelPurchase(purchaseId: String): Result<Unit> {
        TODO("Not yet implemented")
    }
}