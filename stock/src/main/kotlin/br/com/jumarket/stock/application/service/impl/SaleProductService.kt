package br.com.jumarket.stock.application.service.impl

import br.com.jumarket.stock.application.service.ISaleProductService
import br.com.jumarket.stock.domain.entity.Product
import br.com.jumarket.stock.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.Optional

@Service
class SaleProductService(
    @Autowired val repoProduct : ProductRepository
) : ISaleProductService {
    override fun saleProduct(name: String, qtd : Int) : Int{
        val available = qtdProductAvailable(name, qtd)
        if(available > 0){
            val prod = repoProduct.getByNameOrderByDueDate(name)
            val prodsRemoved = removeFromStock(available, prod!!)
            for (productUpdate : Product in prodsRemoved){
                repoProduct.save(productUpdate)
            }
        }
        return available
    }

    override fun inCart(name: String, qtd: Int) : Boolean{
        return qtdProductAvailable(name, qtd) > 0
    }

    override fun qtdProductAvailable(name : String, qtd : Int) : Int{
        val qtdProduct = repoProduct.countProductByName(name)
        return if(qtd < qtdProduct){
            qtd
        }else{
            qtdProduct.toInt()
        }
    }

    private fun removeFromStock(qtd : Int,products : List<Product>) : List<Product>{
        var count = 0
        var j = 0
        val prods = mutableListOf<Product>()
        while (count <= qtd){
            if(!haveProd(products[j])){
                prods.add(products[j])
                products[j].updateDeparture()
                j++
            }else{
                products[j].removeFromStock()
                count++
            }
            if(!prods.contains(products[j])){
                prods.add(products[j])
            }
        }
        return prods
    }

    private fun haveProd(product : Product) : Boolean{
        return product.measure > BigDecimal.ZERO
    }
}