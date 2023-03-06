package com.example.coco_spring.Controller.Sale;

import com.example.coco_spring.Entity.ProductSaleDTO;
import com.example.coco_spring.Repository.ProductRepository;
import com.example.coco_spring.Repository.SaleRepository;
import com.example.coco_spring.Service.Sale.SaleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/sales")
public class SaleController {
    SaleService saleService ;
    SaleRepository saleRepository;
    ProductRepository productRepository ;


    @GetMapping("/{productId}")
    public ResponseEntity<Double> getTotalRevenueByProduct(@PathVariable("productId") Long productId) {
        Double totalRevenue = saleService.getTotalRevenueByProduct(productId);
        if (totalRevenue == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(totalRevenue);
    }
    @GetMapping("/top-selling-products")
    public ResponseEntity<List<ProductSaleDTO>> getTopSellingProducts(@RequestParam("n") int n) {
        List<ProductSaleDTO> topProducts = saleService.getTopSellingProducts(n);
        return ResponseEntity.ok(topProducts);
    }



}
