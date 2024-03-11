//package com.example.Product.service;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import java.util.Optional;
//
//import com.example.Product.entity.Product;
//import com.example.Product.repository.ProductRepository;
//import org.junit.Before;
//import org.junit.Test;
//
//public class CommentServiceTest {
//
//    private ProductRepository productRepository;
//
//    @Before
//    public void setup() {
//        productRepository = mock(ProductRepository.class);
//    }
//
//    @Test
//    public void testFindByProductId() {
//        // 테스트용 데이터 생성
//        Product product = new Product();
//        product.setProductid(1);
//        product.setProduct_name("Test Product");
//
//        // ProductRepository가 메서드를 호출할 때 반환할 결과 설정
//        when(productRepository.findByProductid(1)).thenReturn(product);
//
//        // 테스트 실행
//        Product foundProduct = productRepository.findByProductid(1);
//
//        // 결과 검증
//        assertEquals(1, foundProduct.getProductid());
//        assertEquals("Test Product", foundProduct.getProduct_name());
//    }
//}
