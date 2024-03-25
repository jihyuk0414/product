package com.example.Product.controller;


import com.example.Product.dto.CommentSaveRequest;
import com.example.Product.dto.ProductResponse;
import com.example.Product.dto.ProductSaveRequest;
import com.example.Product.dto.ProductUpdateRequest;
import com.example.Product.entity.Product;
import com.example.Product.service.CommentService;
import com.example.Product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name ="상품 게시판" , description = "상품 관련 API")
public class ProductController {

    private final ProductService productService ;

    // 게시글 작성 - return으로 받은객체 그대로
    @Operation(summary = "상품 게시글 작성")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상품 게시글 작성 성공"),
            @ApiResponse(responseCode = "400", description = "상품 게시글 작성 중 문제 발생")
    })
    @CrossOrigin
    @PostMapping("/product")
    public ResponseEntity saveProduct(@RequestBody ProductSaveRequest productSaveRequest)
    {
        return productService.addProduct(productSaveRequest,productSaveRequest.getJwt()) ;
    }


     // 페이징 형태로 변경
    //entitty에 담아서 보내야 할까요 . .

    @Operation(summary = "상품 게시글 페이지 조회")
    @GetMapping("/product/page/{page}")
    public Page<ProductResponse> getProductPage(@PathVariable("page") int page)
    {
        return productService.findProductPage(page) ;
    }
     //게시글 1개 검색
     @Operation(summary = "상품 게시글 상세 조회")
     @ApiResponses(value = {
             @ApiResponse(responseCode = "200", description = "상품 게시글 조회 성공"),
             @ApiResponse(responseCode = "404", description = "NULL 관련 문제 발생"),
             @ApiResponse(responseCode = "400", description = "상품 게시글 조회 중 문제 발생")
     })
    @CrossOrigin
    @GetMapping("/product/{productid}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("productid") Long productid)
    {
        return productService.findProduct(productid);
    }

//  // 게시글 검색 (이메일)
//

    // 게시글 삭제 /(뭐달라할지 고민 필요)
    @Operation(summary = "상품 게시글 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상품 게시글 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "NULL 관련 문제 발생"),
            @ApiResponse(responseCode = "400", description = "상품 게시글 삭정 중 문제 발생")
    })
    @CrossOrigin
    @DeleteMapping("/product/{productid}")
    public ResponseEntity deleteProduct(@PathVariable("productid") Long productid)
    {
        return productService.deleteProduct(productid);
    }

    // 게시글 수정
    @Operation(summary = "상품 게시글 업데이트")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상품 게시글 변경 성공"),
            @ApiResponse(responseCode = "404", description = "NULL 관련 문제 발생"),
            @ApiResponse(responseCode = "400", description = "상품 게시글 변경 중 문제 발생")
    })
    @CrossOrigin
    @PutMapping("/product/{productid}")
    public ResponseEntity changeProduct(@PathVariable("productid") Long productid,
                                        @RequestBody ProductUpdateRequest productUpdateRequest)
    {
        return productService.updateProduct(productid,productUpdateRequest) ;

    }





}
