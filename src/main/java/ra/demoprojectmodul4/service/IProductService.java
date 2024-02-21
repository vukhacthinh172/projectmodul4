package ra.demoprojectmodul4.service;

import ra.demoprojectmodul4.dto.ProductDto;
import ra.demoprojectmodul4.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAllOrderBy();
    void save(ProductDto productDto);
    // void delete(Integer id);
    Product findById(Integer id);
    List<Product> findProductByProductNameContaining(String name);
    void hideShowProduct(Integer id);
}
