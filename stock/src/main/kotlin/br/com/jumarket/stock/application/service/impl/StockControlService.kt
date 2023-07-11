package br.com.jumarket.stock.application.service.impl

import br.com.jumarket.stock.application.service.IStockControlService
import br.com.jumarket.stock.domain.enummeration.Category
import br.com.jumarket.stock.domain.enummeration.StockStatus
import br.com.jumarket.stock.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StockControlService(
    @Autowired private var repository : ProductRepository,
) : IStockControlService {

    override fun getQtdAllProducts() : Map<Category, Long> {
        val categoryAndQtd = mutableMapOf<Category, Long>()
        for(category in Category.values()){
            categoryAndQtd[category] = repository.getQtdCategory(category.toString())
        }
        return categoryAndQtd
    }
    override fun getQtdProductInStock(name: String): Long {
        return repository.countProductByName(name)
    }

    override fun getMinVolumeByCategoryInStock(name: String): Boolean {
        val status = getStatusStock(name)
        return status.compareTo(StockStatus.CRITICAL) == 0
    }

    override fun getMaxVolumeByCategoryInStock(name: String): Boolean {
        val status = getStatusStock(name)
        return status.compareTo(StockStatus.HIGH) == 0
    }

    override fun getStatusStock(name: String): StockStatus {
        val percentage = getPercentage(name)
        return Category.valueOf(name).getStatusStock(percentage)
    }

    private fun getPercentage(category: String): Double {
        val inStock = repository.getQtdCategory(category.uppercase())
        val total = repository.countProducts()
        return  (inStock.toDouble()/total.toDouble()) * 100
    }
}