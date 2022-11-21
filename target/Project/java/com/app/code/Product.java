package com.app.code;

import javax.persistence.*;
import java.util.Scanner;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "title")
    private String title;

    @Column(name = "ingredients")
    private String ingredients;

    @Column(name = "price")
    private int price;

    @Column(name = "weight")
    private int weight;

    public Product() { }

    public Product(String title) {
        this.title = title;
    }

    public Product(String title, String ingredients, int price, int weight) {
        this.title = title;
        this.ingredients = ingredients;
        this.price = price;
        this.weight = weight;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", title='" + title + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                '}';
    }
}
