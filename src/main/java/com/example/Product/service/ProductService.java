package com.example.Product.service;

import com.example.Product.dto.ProductSaveRequest;
import com.example.Product.dto.ProductUpdateRequest;
import com.example.Product.entity.Product;
import com.example.Product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Product> findAllProducts()
    {
        // 무한스크롤로 변경필요
        return productRepository.findAll();
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

    public ResponseEntity<Product> findProduct(Long productid)
    {
        try {
            Product product = productRepository.findByProductId(productid);
            return ResponseEntity.ok(product) ;
        }  catch(NullPointerException nullPointerException)
        {
            return ResponseEntity.notFound().build() ;
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
            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch(NullPointerException nullPointerException)
        {
            return ResponseEntity.notFound().build();
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }




}
