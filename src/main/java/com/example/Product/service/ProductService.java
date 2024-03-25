package com.example.Product.service;

import com.example.Product.dto.ProductResponse;
import com.example.Product.dto.ProductSaveRequest;
import com.example.Product.dto.ProductUpdateRequest;
import com.example.Product.entity.Product;
import com.example.Product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository ;

    private final GetEmailService getEmailService;

    public ResponseEntity addProduct(ProductSaveRequest productSaveRequest,String jwt) {
        try {
            String writeuseremail = getEmailService.getEmail(jwt);
            Product saveproduct = new Product(productSaveRequest.getProduct_name(),
                    productSaveRequest.getPrice(),productSaveRequest.getProduct_detail(),
                    productSaveRequest.getCreate_at(),productSaveRequest.getCategory_id(),
                    productSaveRequest.isSold_out(),writeuseremail) ;
            log.info("pmessage {}", productSaveRequest.getProduct_detail());
            productRepository.save(saveproduct);
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public Page<ProductResponse> findProductPage (int page)
    {
        Pageable pageable = PageRequest.of(page, 2, Sort.by(Sort.Direction.ASC, "productId"));
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage.map(product ->
                ProductResponse.builder()
                        .product_id(product.getProductId())
                        .product_name(product.getProductname())
                        .price(product.getPrice())
                        .product_detail(product.getPmessage())
                        .create_at(product.getCreateat())
                        .category_id(product.getCategoryid())
                        .sold_out(product.isSoldout())
                        .user_email(product.getUseremail())
                        .build()
        );

    }

    public ResponseEntity deleteProduct(Long productid)
    {
        try{
            Product DeleteProduct = productRepository.findByProductId(productid);
            productRepository.delete(DeleteProduct);
            return ResponseEntity.ok().build() ;
        } catch(NullPointerException nullPointerException)
        {
            return ResponseEntity.notFound().build();
        } catch (Exception e)
        {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<ProductResponse> findProduct(Long productid)
    {
        try {
            Product product = productRepository.findByProductId(productid);
            ProductResponse productResponse = product.toProductResponseDto() ;
            log.info("{}", productResponse.getProduct_id()) ;
            return ResponseEntity.ok(productResponse) ;
        }  catch(Exception e)
        {
            return ResponseEntity.badRequest().build() ;
        }
    }

    public ResponseEntity updateProduct(Long productid, ProductUpdateRequest productUpdateRequest)
    {
        try {
            Product basicproduct = productRepository.findByProductId(productid);
            String basicemail = basicproduct.getUseremail();
            productRepository.updateProduct(productid,
                        productUpdateRequest.getProduct_name(),
                        productUpdateRequest.getPrice(),
                        productUpdateRequest.getProduct_detail(),
                        productUpdateRequest.getCreate_at(),
                        productUpdateRequest.getCategory_id(),
                        productUpdateRequest.isSold_out(),basicemail);
            return ResponseEntity.ok().build();

        } catch(NullPointerException nullPointerException)
        {
            return ResponseEntity.notFound().build();
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }




}
