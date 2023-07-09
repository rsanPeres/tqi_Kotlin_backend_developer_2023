package br.com.jumarket.stock.application.dto

import br.com.jumarket.stock.domain.enummeration.Category
import jakarta.persistence.*
import jakarta.validation.constraints.Positive
import java.math.BigDecimal
import java.time.LocalDate

data class ProductDto(
    val id : Long? = null,
    val name : String = "",
    val measure : BigDecimal = BigDecimal.ZERO,
    val unitPrice : BigDecimal = BigDecimal.ZERO,
    val arrival : LocalDate,
    val dueDate : LocalDate,
    val category: Category = Category.OTHER
) {
}