package com.example.coco_spring.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Sale")
public class Sale implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "product_id")
        private Product product;

        @ManyToOne
        @JoinColumn(name = "store_id")
        private Store store;

        private LocalDate saleDate;

        private int quantity;

        private double price;

        public Sale(Sale productSale) {
                this.product = productSale.getProduct();
                this.saleDate = productSale.getSaleDate();
                this.quantity = productSale.getQuantity();
        }



}
