package com.example.Product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Table(name = "comment")
@Entity
@Getter
@Setter
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @Column(name = "user_email")
    private String user_email;

    @Column(name = "comment_detail")
    private String comment_detail;


    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    public Comment(String user_email, String comment_detail, Product product) {
        this.user_email = user_email;
        this.comment_detail = comment_detail;
        this.product = product;
    }
}
