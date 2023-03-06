package com.example.coco_spring.Service.Sale;

import com.example.coco_spring.Entity.ProductSaleDTO;

import java.util.List;

public interface ISaleService {
    public Double getTotalRevenueByProduct(Long productId) ;
    public List<ProductSaleDTO> getTopSellingProducts(int n) ;


}
