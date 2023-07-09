package br.com.jumarket.stock.api.controller

import br.com.jumarket.stock.application.dto.ProductDto
import br.com.jumarket.stock.application.service.IProductService
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class ProductController(
    @Autowired val service : IProductService
) {

    @GetMapping
    fun getAll() : List<ProductDto>{
        return service.getAll()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable @NotNull id : Long) : ResponseEntity<ProductDto>{
        val product = service.getById(id)

        return ResponseEntity.status(HttpStatus.OK).body(product)
    }

    @PostMapping
    fun create(@RequestBody @Valid dto : ProductDto) : ResponseEntity<ProductDto>{
        val product = service.create(dto)

        return ResponseEntity.status(HttpStatus.CREATED).body(product)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable @NotNull id : Long, @RequestBody @Valid dto : ProductDto) : ResponseEntity<ProductDto>{
        val product = service.update(id, dto)

        return ResponseEntity.status(HttpStatus.OK).body(product)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable @NotNull id : Long) : ResponseEntity<ProductDto>{
        service.delete(id)
        return ResponseEntity.noContent().build<ProductDto?>()
    }
}