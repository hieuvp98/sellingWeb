package com.bksoftware.sellingweb.service_impl.product;

import com.bksoftware.sellingweb.entities.category.BigCategory;
import com.bksoftware.sellingweb.entities.category.MediumCategory;
import com.bksoftware.sellingweb.entities.category.SmallCategory;
import com.bksoftware.sellingweb.entities.product.BuyForm;
import com.bksoftware.sellingweb.entities.product.BuyFormHasProduct;
import com.bksoftware.sellingweb.entities.product.Product;
import com.bksoftware.sellingweb.repository.category.MediumCategoryRepository;
import com.bksoftware.sellingweb.repository.category.SmallCategoryRepository;
import com.bksoftware.sellingweb.repository.product.BuyFormHasProductRepository;
import com.bksoftware.sellingweb.repository.product.BuyFormRepository;
import com.bksoftware.sellingweb.repository.product.ProductDetailsRepository;
import com.bksoftware.sellingweb.repository.product.ProductRepository;
import com.bksoftware.sellingweb.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ProductService_Impl implements ProductService {

    private static final Logger LOGGER = Logger.getLogger(ProductService_Impl.class.getName());


    private final ProductRepository productRepository;
    private final BuyFormHasProductRepository buyFormHasProductRepository;
    private final BuyFormRepository buyFormRepository;
    private final MediumCategoryRepository mediumCategoryRepository;
    private final SmallCategoryRepository smallCategoryRepository;

    public ProductService_Impl(ProductRepository productRepository, ProductDetailsRepository productDetailsRepository, BuyFormHasProductRepository buyFormHasProductRepository, BuyFormRepository buyFormRepository, MediumCategoryRepository mediumCategoryRepository, SmallCategoryRepository smallCategoryRepository) {
        this.productRepository = productRepository;

        this.buyFormHasProductRepository = buyFormHasProductRepository;
        this.buyFormRepository = buyFormRepository;
        this.mediumCategoryRepository = mediumCategoryRepository;
        this.smallCategoryRepository = smallCategoryRepository;
    }

    public Page<Product> findAllProduct(Pageable pageable) {
        try {
            return productRepository.findByStatus(true, pageable);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-product-error : {0}", ex.getMessage());
        }
        return null;
    }


    public Map<String, Long> findGuaranteeToPhone(int phone_number) {
        Map<String, Long> mapProduct = new HashMap<>();
        try {
            List<BuyForm> buyForms = buyFormRepository.findAllByPhoneNumber(phone_number);
            List<BuyFormHasProduct> buyFormHasProducts = new ArrayList<>();
            buyForms.forEach(buyForm -> buyFormHasProducts.addAll(buyFormHasProductRepository.findAllByBuyFormId(buyForm.getId())));
            buyFormHasProducts.forEach(buyFormHasProduct -> {
                LocalDate date_now = LocalDate.now();
                LocalDate date_buy = buyFormHasProduct.getSoldDate();
                Product product = productRepository.findById(buyFormHasProduct.getProductId());
                long used_time = ChronoUnit.DAYS.between(date_buy, date_now);
                long guarantee = product.getProductDetails().getGuarantee();
                long day_lefts = guarantee - used_time;
                mapProduct.put(product.getName(), day_lefts);
            });
            return mapProduct;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-guarantee-by-phone-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public Page<Product> findProductByName(String name, Pageable pageable) {
        try {
            return productRepository.findByName(name, pageable);

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-product-by-name-error : {0}", ex.getMessage());
        }
        return null;
    }

    public List<Product> findProductByNamePage(String name) {
        try {
            return productRepository.findByNamePage(name);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-product-by-name-page-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public Page<Product> findNewProducts(Pageable pageable) {
        try {
            return productRepository.findNewProducts(pageable);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-new-products-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public Page<Product> findHotProducts(Pageable pageable) {
        try {
            return productRepository.findHotProducts(pageable);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-hot-products-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public Sort sortData(String type) {
        Sort sortable = null;
        if (type.equals("ASC")) {
            sortable = Sort.by("view").ascending();
        }
        if (type.equals("DESC")) {
            sortable = Sort.by("view").descending();
        }
        return sortable;
    }

    @Override
    public Sort sortDataProduct(String type, String field) {
        Sort sortable = null;
        if (type.equals("ASC")) {
            sortable = Sort.by(field).ascending();
        }
        if (type.equals("DESC")) {
            sortable = Sort.by(field).descending();
        }
        return sortable;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }


    @Override
    public Product findById(int id) {
        Product product = productRepository.findById(id);
        if (product.isStatus()) return product;
        return null;
    }

    @Override
    public boolean saveProduct(Product product) {
        try {
            productRepository.save(product);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-product-error: {0}", ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteProduct(Product product) {
        try {
            product.setStatus(false);
            System.out.println("status: " + product.isStatus());
            productRepository.save(product);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-product-error : {0}", ex.getMessage());
            return false;
        }
    }

    @Override
    public Page<Product> showProductByBigCategory(int id, Pageable pageable) {

        try {
            return productRepository.showProduct(id, pageable);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "show-product-error : {0}", ex.getMessage());

        }
        return null;
    }

    @Override
    public List<Product> showProductByBigCategoryPage(int id) {

        try {
            return productRepository.showProduct(id);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "show-product-By-Big-page-error : {0}", ex.getMessage());

        }
        return null;
    }

    @Override
    public List<Product> showProductByMediumCategoryPage(int id) {
        return productRepository.findProductByMedium(id);
    }

    @Override
    public List<Product> showProductBySmallCategoryPage(int id) {
        return productRepository.findProductBySmall(id);
    }

    @Override
    public List<Product> findProductBySmall(int id) {
        try {
            return productRepository.findProductBySmall(id);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "error", ex.getMessage());
            return null;
        }
    }

    @Override
    public Page<Product> showProductByMedium(int id, Pageable pageable) {

        try {
            return productRepository.showProductByMedium(id, pageable);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "show-product-error : {0}", ex.getMessage());

        }
        return null;
    }

    @Override
    public List<Product> findProductByMedium(int id) {
        try {
            return productRepository.findProductByMedium(id);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "error", ex.getMessage());
            return null;
        }
    }

    @Override
    public Page<Product> showProductByBig(int id, Pageable pageable) {

        try {
            return productRepository.showProductByBig(id, pageable);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "show-product-error : {0}", ex.getMessage());

        }
        return null;
    }


    @Override
    public Page<Product> showProductByBigBranch(int id, int branch, Pageable pageable) {

        try {
            return productRepository.showProductByBigBranch(id, branch, pageable);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "show-product-error : {0}", ex.getMessage());

        }
        return null;
    }

    @Override
    public Page<Product> showProductByMediumBranch(int id, int branch, Pageable pageable) {

        try {
            return productRepository.showProductByMediumBranch(id, branch, pageable);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "show-product-error : {0}", ex.getMessage());

        }
        return null;
    }

    @Override
    public Page<Product> showProductBySmallBranch(int id, int branch, Pageable pageable) {

        try {
            return productRepository.showProductSmallBranch(id, branch, pageable);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "show-product-small-branch-error : {0}", ex.getMessage());

        }
        return null;
    }

    @Override
    public Page<Product> findProductByPrice(int id, int low, int high, Pageable pageable) {

        try {
            return productRepository.findProductByPrice(id, low, high, pageable);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "show-product-error : {0}", ex.getMessage());

        }
        return null;
    }

    @Override
    public Page<Product> findProductByPriceBranch(int id, int low, int high, int branch, Pageable pageable) {

        try {
            return productRepository.findProductByPriceBranch(id, low, high, branch, pageable);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "show-product-error : {0}", ex.getMessage());

        }
        return null;
    }

    @Override
    public Page<Product> showProductSale(Pageable pageable) {
        try {
            return productRepository.showProductSale(pageable);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "show-product-sale-error : {0}", ex.getMessage());

        }
        return null;
    }

    @Override
    public List<Product> findAllProductByBigCategory(BigCategory bigCategory) {
        List<MediumCategory> mediumCategories = mediumCategoryRepository.findAllByBigCategory(bigCategory);
        List<SmallCategory> smallCategories = new ArrayList<>();
        mediumCategories.forEach(mediumCategory -> smallCategories.addAll(smallCategoryRepository.findAllByMediumCategory(mediumCategory)));
        List<Product> products = new ArrayList<>();
        smallCategories.forEach(smallCategory -> products.addAll(productRepository.findAllBySmallCategory(smallCategory)));
        if (products.isEmpty())
            LOGGER.log(Level.SEVERE, "found 0 product");
        return products;
    }


}