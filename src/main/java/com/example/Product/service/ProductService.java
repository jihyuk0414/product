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

    public ResponseEntity saveProduct(ProductSaveRequest productSaveRequest,String jwt) {
        try {
            String writeuseremail = getEmailService.getemail(jwt);
            Product saveproduct = new Product(productSaveRequest.getProductname(),
                    productSaveRequest.getPrice(),productSaveRequest.getPmessage(),
                    productSaveRequest.getCreateat(),productSaveRequest.getCategoryid(),
                    productSaveRequest.isSoldout(),writeuseremail) ;
            log.info("pmessage {}", productSaveRequest.getPmessage());
            productRepository.save(saveproduct);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch(Exception e) {
            log.info("saveis only ok");
            return ResponseEntity.badRequest().build();
        }
    }

    public List<Product> getallProduct()
    {
        return productRepository.findAll();
    }

    public ResponseEntity deleteProduct(Long productid)
    {
        Product DeleteProduct = productRepository.findByProductId(productid);
        try{
            productRepository.delete(DeleteProduct);
            return ResponseEntity.ok().build() ;
        } catch (Exception e)
        {
            return ResponseEntity.badRequest().build();
        }
    }

    public Product findoneProduct(Long productid)
    {
        return productRepository.findByProductId(productid);
    }

    public ResponseEntity updateProduct(Long productid, ProductUpdateRequest productUpdateRequest)
    {
        try {
            Product basicproduct = productRepository.findByProductId(productid);
            String basicemail = basicproduct.getUseremail();
            String writeuseremail = getEmailService.getemail(productUpdateRequest.getJwt());
            log.info(writeuseremail);
            //이메일 쓴사람이랑 똑같은지 확인
            if (writeuseremail.equalsIgnoreCase(basicemail))
            {
                log.info("compareok") ;
                productRepository.updateProduct(productid,
                        productUpdateRequest.getProductname(),
                        productUpdateRequest.getPrice(),
                        productUpdateRequest.getPmessage(),
                        productUpdateRequest.getCreateat(),
                        productUpdateRequest.getCategoryid(),
                        productUpdateRequest.isSoldout(),basicemail);

                return ResponseEntity.status(HttpStatus.CREATED).build();

            }
            else
            {
                //이메일 검증 실패.
                log.info("different person with who write") ;
                return ResponseEntity.badRequest().build();
            }

        } catch(Exception e) {
            log.info("saveis only ok");

            return ResponseEntity.badRequest().build();
        }

    }




}
