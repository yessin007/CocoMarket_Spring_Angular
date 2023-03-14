package com.example.coco_spring.Service.Sale;

import com.example.coco_spring.Entity.Product;
import com.example.coco_spring.Entity.ProductSaleDTO;
import com.example.coco_spring.Entity.Sale;
import com.example.coco_spring.Repository.SaleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service

@AllArgsConstructor
public class SaleService implements ISaleService{
    SaleRepository saleRepository;
    @Override
    public Double getTotalRevenueByProduct(Long productId) {
        return saleRepository.findTotalRevenueByProduct(productId);
    }

    @Override
    public List<ProductSaleDTO> getTopSellingProducts(int n) {
        List<Sale> topSales = saleRepository.findAllByOrderByQuantityDesc().stream()
                .limit(n)
                .collect(Collectors.toList());
        List<ProductSaleDTO> topProducts = new ArrayList<>();
        for (Sale sale : topSales) {
            Product product = sale.getProduct();
            ProductSaleDTO productDTO = new ProductSaleDTO(product.getProductId(), product.getProductName(), product.getDescription(), product.getPrice(), product.getProductCategory());
            topProducts.add(productDTO);
        }
        return topProducts;
    }


}
