package com.example.fooddelivery.model;


import com.example.fooddelivery.command.ProductCommand;
import com.example.fooddelivery.enums.FoodType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Product extends AbstractEntity{

    @Enumerated(EnumType.STRING)
    private FoodType type;

    @ManyToOne(optional = false)
    private OrderEntity order;

    private BigDecimal price;

    public Product(){

    }

    public Product(FoodType type) {
        this.type = type;
    }
    public static Product createOne(final ProductCommand productCommand){
        final Product product = new Product();
        product.type = FoodType.valueOf(productCommand.getType());

        if(product.type == FoodType.TACOS){
            product.price = BigDecimal.valueOf(500.00);
        }else
            product.price = BigDecimal.valueOf(800.00);

        return product;
    }
    public void update(final ProductCommand productCommand){
        this.type = FoodType.valueOf(productCommand.getType());
    }

    public void linkToOrder(OrderEntity order) {
        this.order = order;
    }

    @Override
    public void delete() {
        super.delete();
    }
}
