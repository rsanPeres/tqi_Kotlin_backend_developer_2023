package br.com.jumarket.stock.api.controller

import br.com.jumarket.stock.application.service.ISaleProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/saleProducts")
class SaleProductController(
    @Autowired val service : ISaleProductService
) {
    @PutMapping("/{name}")
    fun saleProduct( @PathVariable name: String, @RequestParam(value = "qtd") qtd : Int) : Int{
        return service.saleProduct(name, qtd)
    }

    @GetMapping("/{name}")
    fun inCart(@PathVariable name: String, @RequestParam(value = "qtd") qtd : Int) : Boolean{
        return service.inCart(name, qtd)
    }

    @GetMapping("/available/{name}")
    fun qtdProductAvailable(@PathVariable name: String, @RequestParam(value = "qtd") qtd : Int) : Int{
        return service.qtdProductAvailable(name, qtd)
    }
}