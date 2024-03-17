package app.products.services;

import app.products.dao.ProductDao;
import app.products.dao.ProductDaoImpl;
import app.products.dto.ProductDto;
import app.products.enums.Category;
import app.products.model.Product;
import app.products.vo.ProductVO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;

    public ProductServiceImpl() throws SQLException {
        this.productDao = new ProductDaoImpl();
    }

    @Override
    public List<ProductVO> getAllProducts() {
        List<Product> productList = productDao.findAll();
        List<ProductVO> productVOs = new ArrayList<>();
        for (Product product : productList){
           ProductVO productVO = ProductVO.builder()
                   .id(product.getId())
                   .name(product.getName())
                   .brand(product.getBrand())
                   .price(product.getPrice())
                   .quantity(product.getQuantity())
                   .size(product.getSize())
                   .model(product.getModel())
                   .color(product.getColor())
                   .weight(product.getWeight())
                   .expiryDate(product.getExpiryDate())
                   .regNo(product.getRegNo())
                   .vendor(product.getVendor())
                   .status(product.getBrand())
                   .image(product.getImage())
                   .category(String.valueOf(product.getCategory()))
                   .createdAt(String.valueOf(product.getCreatedAt()))
                   .updatedAt(String.valueOf(product.getUpdatedAt()))
                   .build();
           productVOs.add(productVO);
        }
        return productVOs;
    }

    @Override
    public List<ProductVO> getProductByCategory(String category) {
        return null;
    }

    @Override
    public ProductVO getProductById(int id) {
        Product product = productDao.findById(id);
            ProductVO productVO = ProductVO.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .brand(product.getBrand())
                    .price(product.getPrice())
                    .quantity(product.getQuantity())
                    .size(product.getSize())
                    .model(product.getModel())
                    .color(product.getColor())
                    .weight(product.getWeight())
                    .expiryDate(product.getExpiryDate())
                    .regNo(product.getRegNo())
                    .vendor(product.getVendor())
                    .status(product.getBrand())
                    .image(product.getImage())
                    .category(String.valueOf(product.getCategory()))
                    .createdAt(String.valueOf(product.getCreatedAt()))
                    .updatedAt(String.valueOf(product.getUpdatedAt()))
                    .build();
           return productVO;
    }

    @Override
    public ProductVO addProduct(ProductDto productDto) {
        Product product = Product.builder()
                .name(productDto.getName())
                .color(productDto.getColor())
                .category(Category.valueOf(productDto.getCategory()))
                .image(productDto.getImage())
                .quantity(productDto.getQuantity())
                .price(productDto.getPrice())
                .size(productDto.getSize())
                .build();
        product = productDao.save(product);
        return ProductVO.builder()
                .id(product.getId())
                .name(product.getName())
                .color(product.getColor())
                .category(String.valueOf(product.getCategory()))
                .image(product.getImage())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .size(product.getSize())
                .build();
    }

    @Override
    public void deleteProductById(Integer productId) {
        System.out.println("i'm here 1");
        productDao.deleteById(productId);
        return;
    }

    @Override
    public ProductVO updateProduct(ProductDto productDto) {
        Product product = Product.builder()
                .name(productDto.getName())
                .color(productDto.getColor())
                .category(Category.valueOf(productDto.getCategory()))
                .image(productDto.getImage())
                .quantity(productDto.getQuantity())
                .price(productDto.getPrice())
                .size(productDto.getSize())
                .build();
        product = productDao.update(product);
        return ProductVO.builder()
                .id(product.getId())
                .name(product.getName())
                .color(product.getColor())
                .category(String.valueOf(product.getCategory()))
                .image(product.getImage())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .size(product.getSize())
                .build();
    }

    @Override
    public ProductVO findById(Integer productId) {
        Product product = productDao.findById(productId);
        return ProductVO.builder()
                .id(product.getId())
                .name(product.getName())
                .color(product.getColor())
                .category(String.valueOf(product.getCategory()))
                .image(product.getImage())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .size(product.getSize())
                .build();
    }
}
