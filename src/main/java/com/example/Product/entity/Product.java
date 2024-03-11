package com.example.Product.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Table(name = "product")
@Entity
@Data
@RequiredArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId ;

    @Column(name = "product_name")
    private String product_name;

    @Column(name = "price")
    private int price ;

//    @Column(name = "image1")
//    private String image1;
//
//    @Column(name = "image2")
//    private String image2;
//    @Column(name = "image3")
//    private String image3;
//    @Column(name = "image4")
//    private String image4;
//    @Column(name = "image5")
//    private String image5;

    @Column(name = "Pmessage")
    private String pmessage;

    @Column(name = "create_at")
    private Date create_at;

    @Column(name = "category_id")
    private int category_id;

    @Column(name = "sold_out")
    private boolean sold_out;

    @Column(name = "user_email")
    private String user_email;


    public Product(String product_name, int price, String pmessage, Date create_at, int category_id, boolean sold_out, String user_email) {
        this.product_name = product_name;
        this.price = price;
        this.pmessage = pmessage;
        this.create_at = create_at;
        this.category_id = category_id;
        this.sold_out = sold_out;
        this.user_email = user_email;
    }



}
