package br.com.jumarket.stock.domain.enummeration

enum class Category(val code: Int, val description: String) {
    CLEANING(5, "cleaning"),
    DRINK(3, "drink"),
    BOMBONIER(3, "bombonier"),
    SNACKS(5, "snacks"),
    TEACOFFEE(3, "teacoffee"),
    GRAINSCEREALS(2, "grainscereals"),
    OTHER(5, "other");

    fun getStatusStock(percent : Double) : StockStatus{
        return when{
            percent >= 0.25 -> StockStatus.HIGH
            percent >= 0.10 -> StockStatus.NORMAL
            percent >= code.toDouble()/100 -> StockStatus.LOW
            percent >= 0 -> StockStatus.CRITICAL
            else -> StockStatus.EXHAUSTED
        }
    }
}