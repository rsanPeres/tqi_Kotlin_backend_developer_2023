package br.com.jumarket.stock.application.service.impl

import br.com.jumarket.stock.api.mappers.ProductMapper
import br.com.jumarket.stock.application.dto.ProductDto
import br.com.jumarket.stock.application.service.IProductService
import br.com.jumarket.stock.domain.enummeration.Category
import br.com.jumarket.stock.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate

@Service
class ProductService(
    @Autowired private var repository : ProductRepository,
) : IProductService {

    override fun getAll() : List<ProductDto>{
        return repository.findAll()
            .map { x -> ProductMapper.toDto(x) }
    }

    override fun getById(id : Long) : ProductDto{
        val product = repository.findById(id).get()
        return ProductMapper.toDto(product)
    }

    override fun getProductByName(name: String): List<ProductDto> {
        val product = repository.getByNameOrderByDueDate(name)
        product?.let {
            return it.map { x -> ProductMapper.toDto(x) }
        }
        return listOf(ProductDto(null, "", BigDecimal.ZERO, BigDecimal.ZERO, LocalDate.now(), LocalDate.now(), Category.OTHER))
    }

    override fun create(dto : ProductDto) : ProductDto{
        val newProduct = ProductMapper.toEntity(dto)
        repository.save(newProduct)

        return ProductMapper.toDto(newProduct)
    }

    override fun update(id : Long, dto : ProductDto) : ProductDto{
        if(getById(id).name.isNotEmpty()){
            val up = ProductMapper.toEntity(dto)
            up.id = id
            repository.save(up)
        }
        return dto
    }

    override fun delete(id : Long){
        repository.deleteById(id)
    }
}