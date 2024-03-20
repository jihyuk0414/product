package com.example.Product.controller;


import com.example.Product.dto.CommentSaveRequest;
import com.example.Product.dto.ProductSaveRequest;
import com.example.Product.dto.ProductUpdateRequest;
import com.example.Product.entity.Product;
import com.example.Product.service.CommentService;
import com.example.Product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService ;

    // 게시글 작성 - return으로 받은객체 그대로
    @CrossOrigin
    @PostMapping("/product/create")
    public ResponseEntity saveProduct(@RequestBody ProductSaveRequest productSaveRequest)
    {
        return productService.addProduct(productSaveRequest,productSaveRequest.getJwt()) ;
    }


     // 게시글 검색 (상품명) -> 모든 상품명에 따른 객체들 리스트로 return
     // 무한스크롤로 변경
     @CrossOrigin
     @GetMapping("/product/list")
     public List<Product> getAllProducts()
     {
         return productService.findAllProducts();
     }

     //게시글 1개 검색
    @CrossOrigin
    @GetMapping("/product/{productid}")
    public ResponseEntity<Product> getProduct(@PathVariable("productid") Long productid)
    {
        return productService.findProduct(productid);
    }

//  // 게시글 검색 (이메일)
//

    // 게시글 삭제 /(뭐달라할지 고민 필요)
    @CrossOrigin
    @DeleteMapping("/product/delete/{productid}")
    public ResponseEntity deleteProduct(@PathVariable("productid") Long productid)
    {
        return productService.deleteProduct(productid);
    }

    // 게시글 수정
    @CrossOrigin
    @PutMapping("/product/update/{productid}")
    public ResponseEntity changeProduct(@PathVariable("productid") Long productid,
                                        @RequestBody ProductUpdateRequest productUpdateRequest)
    {
        return productService.updateProduct(productid,productUpdateRequest) ;

    }





}
