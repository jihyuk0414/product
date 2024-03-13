package com.example.Product.repository;

import com.example.Product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByProductId(Long id);


    String findUserEmailByProductId(Long productid);

    @Modifying
    @Query("update Product p set p.product_name = :productname, " +
            "p.price = :price, p.Pmessage = :pmessage," +
            "p.create_at = :createat," +
            "p.category_id = :categoryid," +
            "p.sold_out = :soldout, p.user_email = :useremail " +
            "where p.product_id = :productid")
    void updateProduct(@Param("productId") Long productid,
                       @Param("productName") String productname,
                       @Param("price") int price,
                       @Param("pMessage") String pmessage,
                       @Param("createAt") Date createat,
                       @Param("categoryId") int categoryid,
                       @Param("soldOut") boolean soldout,
                       @Param("userEmail") String useremail); //이미지는 변경 필요


}
