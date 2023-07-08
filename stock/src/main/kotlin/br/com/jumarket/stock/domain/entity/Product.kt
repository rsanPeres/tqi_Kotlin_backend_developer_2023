package br.com.jumarket.stock.domain.entity

import br.com.jumarket.stock.domain.enummeration.Category
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate

@Entity
data class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)val id : Long? = null,
    @Column(nullable = false) val name : String = "",
    @Column(nullable = false) val measure : BigDecimal = BigDecimal.ZERO,
    @Column(nullable = false) val unitPrice : BigDecimal = BigDecimal.ZERO,
    @Column(nullable = false) val arrival : LocalDate,
    @Column(nullable = false) val dueDate : LocalDate,
    @Enumerated val category: Category = Category.OTHER
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Product) return false

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "Product(id=$id, categoria=$category, nome='$name', preco=$unitPrice, quantidade=$measure, vencimento=$dueDate)"
    }
}