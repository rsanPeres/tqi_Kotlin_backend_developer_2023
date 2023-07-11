package br.com.jumarket.stock.api.mappers

import br.com.jumarket.stock.application.dto.ProductDto
import br.com.jumarket.stock.domain.entity.Product

object ProductMapper : IProductMapper<Product, ProductDto> {
    override fun toDto(product: Product): ProductDto {
        return ProductDto(
            product.id,
            product.name,
            product.measure,
            product.unitPrice,
            product.arrival,
            product.departure,
            product.dueDate,
            product.category)
    }

    override fun toEntity(dto: ProductDto): Product {
        return Product(
            dto.id,
            dto.name,
            dto.measure,
            dto.unitPrice,
            dto.arrival,
            dto.departure,
            dto.dueDate,
            dto.category)
    }

}