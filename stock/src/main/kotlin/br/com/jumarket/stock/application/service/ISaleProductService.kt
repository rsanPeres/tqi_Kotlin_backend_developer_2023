package br.com.jumarket.stock.application.service

interface ISaleProductService {
    fun saleProduct(name: String, qtd : Int) : Int
    fun inCart(name: String, qtd: Int) : Boolean
    fun qtdProductAvailable(name : String, qtd : Int) : Int
}