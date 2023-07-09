package br.com.jumarket.stock.api.mappers

interface IProductMapper<Product, ProductDto> {
    fun toDto(product : Product) : ProductDto
    fun toEntity(dto :ProductDto) : Product
}