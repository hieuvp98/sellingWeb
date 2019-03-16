package com.bksoftware.sellingweb.service.product;


import com.bksoftware.sellingweb.entities.category.BigCategory;
import com.bksoftware.sellingweb.entities.product.Partner;
import com.bksoftware.sellingweb.entities.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public interface ProductService {

    Map<String, Long> findGuaranteeToPhone(int phone_number);

    Page<Product> findProductByName(String name, Pageable pageable);

    List<Product> findAllProduct();

    TreeMap<Integer,Partner> test();

    Sort sortData(String type);

    public Sort sortDataProduct(String type,String field) ;

    List<Product> findAll();

    Product findById(int id);

    boolean saveProduct(Product product);

    boolean deleteProduct(Product product);

    Page<Product> showProduct(int id, Pageable pageable);
    List<Product> findProductBySmall( int id);

    Page<Product> showProductByMedium(int id, Pageable pageable);

    List<Product> findProductByMedium( int id);

    Page<Product> showProductByBig(int id,Pageable pageable);

    Page<Product> showProductByBigBranch(int id,int branch,Pageable pageable);

    Page<Product> showProductByMediumBranch(int id,int branch,Pageable pageable);

    Page<Product> showProductBySmallBranch(int id,int branch,Pageable pageable);

    Page<Product> findProductByPrice(int id,int low,int high,Pageable pageable);

    Page<Product> findProductByPriceBranch(int id,int low,int high,int branch,Pageable pageable);

    Page<Product> showProductSale( Pageable pageable);

    List<Product> findAllProductByBigCategory(BigCategory bigCategory);


}
