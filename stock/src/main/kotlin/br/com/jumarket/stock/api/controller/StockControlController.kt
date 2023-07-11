package br.com.jumarket.stock.api.controller

import br.com.jumarket.stock.application.service.IStockControlService
import br.com.jumarket.stock.domain.enummeration.Category
import br.com.jumarket.stock.domain.enummeration.StockStatus
import jakarta.validation.constraints.NotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/stockControl")
class StockControlController(
    @Autowired val service : IStockControlService
) {
    @GetMapping("/all")
    fun getQtdAll() : Map<Category, Long> {
        return service.getQtdAllProducts()
    }

    @GetMapping("/{name}")
    fun qtdProductByName(@PathVariable @NotNull name : String) : Long {
        return service.getQtdProductInStock(name)
    }

    @GetMapping("minVol/{category}")
    fun minVolumeByCategory(@PathVariable @NotNull category : String) : Boolean{
        return service.getMinVolumeByCategoryInStock(category)
    }

    @GetMapping("maxVol/{category}")
    fun maxVolumeByCategory(@PathVariable @NotNull category : String) : Boolean{
        return service.getMaxVolumeByCategoryInStock(category)
    }

    @GetMapping("status/{category}")
    fun statusByCategory(@PathVariable @NotNull category : String) : StockStatus{
        return service.getStatusStock(category)
    }
}