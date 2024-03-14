package com.example.Product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProductUpdateRequest {

    public ProductUpdateRequest(){
    }

    private String productname;

    private int price ;

    private List image;
//
//    private String image2;
//
//    private String image3;
//
//    private String image4;
//
//    private String image5;

    private String pmessage;

    private Date createat;

    private int categoryid;

    private boolean soldout;

    private String jwt;


}