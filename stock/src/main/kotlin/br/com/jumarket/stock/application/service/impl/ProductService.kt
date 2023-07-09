package br.com.jumarket.stock.application.service.impl

import br.com.jumarket.stock.application.dto.ProductDto
import br.com.jumarket.stock.application.service.IProductService
import br.com.jumarket.stock.domain.entity.Product
import br.com.jumarket.stock.repository.ProductRepository
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService(
    @Autowired private var repository : ProductRepository,
    @Autowired private var mapper: ModelMapper
) : IProductService {

    override fun getAll() : List<ProductDto>{
        return repository.findAll()
            .map { x -> mapper.map(x, ProductDto::class.java) }
    }

    override fun getById(id : Long) : ProductDto{
        return mapper.map(repository.findById(id), ProductDto::class.java)
    }

    override fun create(dto : ProductDto) : ProductDto{
        val newProduct = mapper.map(dto, Product::class.java)
        repository.save(newProduct)

        return mapper.map(newProduct, ProductDto::class.java)
    }

    override fun update(id : Long, dto : ProductDto) : ProductDto{
        if(getById(id).name.isNotEmpty()){
            val up = mapper.map(dto, Product::class.java)
            up.id = id
            repository.save(up)
        }
        return dto
    }

    override fun delete(id : Long){
        repository.deleteById(id)
    }
}