package app.products.dao;

import app.products.enums.Category;
import app.products.model.Product;

import java.util.List;

public interface ProductDao {
    Product findById(int id);
    Product findByNameAndCategory(String name, String category);
    List<Product> findByCategory(String category);
    List<Product> findAll();

    Product save(Product product);

    void deleteById(Integer productId);

    Product update(Product product);
}
