package com.bksoftware.sellingweb.repository.product;

import com.bksoftware.sellingweb.entities.product.Product;
import com.bksoftware.sellingweb.entities.product.ProductDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Integer> {



    ProductDetails findById(int id);

    ProductDetails findByProduct(Product product);
}
