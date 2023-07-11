package br.com.jumarket.stock.repository

import br.com.jumarket.stock.domain.entity.Product
import br.com.jumarket.stock.domain.enummeration.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.name = :name ORDER BY p.dueDate ASC")
    fun getByNameOrderByDueDate(@Param("name") name: String?): List<Product>?

    @Query("SELECT sum(p.measure) FROM Product p WHERE p.name = :name")
    fun countProductByName(@Param("name") name :String) : Long

    @Query("SELECT sum(p.measure) FROM Product p WHERE p.category = :category")
    fun getQtdCategory(category: String): Long

    @Query("SELECT sum(p.measure) FROM Product p")
    fun countProducts(): Long

}