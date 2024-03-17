package app.products.services;

import app.products.dto.ProductDto;
import app.products.vo.ProductVO;

import java.util.List;

public interface ProductService {
    List<ProductVO> getAllProducts();
    List<ProductVO> getProductByCategory(String category);
    ProductVO getProductById(int id);

    ProductVO addProduct(ProductDto productDto);

    void deleteProductById(Integer productId);

    ProductVO updateProduct(ProductDto productDto);

    ProductVO findById(Integer productId);
}
