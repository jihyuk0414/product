package com.example.Product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class CommentSaveRequest {


    public CommentSaveRequest(){
    }


    private String commentdetail;

    private Long productid ;

    private String jwt;

}
