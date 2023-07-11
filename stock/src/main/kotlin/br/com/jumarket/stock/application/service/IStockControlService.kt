package br.com.jumarket.stock.application.service

import br.com.jumarket.stock.domain.enummeration.Category
import br.com.jumarket.stock.domain.enummeration.StockStatus

interface IStockControlService {
    fun getQtdAllProducts() : Map<Category, Long>
    fun getQtdProductInStock(name : String) : Long
    fun getMinVolumeByCategoryInStock(name : String) : Boolean
    fun getMaxVolumeByCategoryInStock(name : String) : Boolean
    fun getStatusStock(name : String) : StockStatus
}