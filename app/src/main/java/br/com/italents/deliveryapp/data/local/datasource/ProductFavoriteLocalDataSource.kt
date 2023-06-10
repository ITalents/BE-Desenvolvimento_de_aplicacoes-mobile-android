package br.com.italents.deliveryapp.data.local.datasource

import br.com.italents.deliveryapp.data.local.dao.ProductFavoriteDao
import br.com.italents.deliveryapp.data.local.entity.ProductFavoriteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductFavoriteLocalDataSource constructor(private val productFavoriteDao: ProductFavoriteDao) {

    suspend fun getAllProductsFavorite(): List<ProductFavoriteEntity> {
        val result: List<ProductFavoriteEntity>

        withContext(Dispatchers.IO) {
            result = productFavoriteDao.getAllProductsFavorite()
        }

        return result
    }

    suspend fun insertProductFavorite(productFavoriteEntity: ProductFavoriteEntity) {
        withContext(Dispatchers.IO) {
            productFavoriteDao.insertProductFavorite(productFavoriteEntity)
        }
    }

    suspend fun deleteProductFavorite(productFavoriteEntity: ProductFavoriteEntity) {
        withContext(Dispatchers.IO) {
            productFavoriteDao.deleteProductFavorite(productFavoriteEntity)
        }
    }
}