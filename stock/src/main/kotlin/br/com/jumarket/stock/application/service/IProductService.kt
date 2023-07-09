package br.com.jumarket.stock.application.service

import br.com.jumarket.stock.application.dto.ProductDto

interface IProductService {
    fun getAll() : List<ProductDto>
    fun getById(id : Long) : ProductDto
    fun create(dto : ProductDto) : ProductDto
    fun update(id : Long, dto : ProductDto) : ProductDto
    fun delete(id : Long)
}