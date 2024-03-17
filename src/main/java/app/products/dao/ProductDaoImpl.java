package app.products.dao;

import app.admin.enums.Role;
import app.admin.model.Admin;
import app.config.db.DbConfig;
import app.exceptions.DbException;
import app.products.enums.Category;
import app.products.enums.Status;
import app.products.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao{
//    private final Connection conn;

    public ProductDaoImpl() throws SQLException {
//        this.conn = DbConfig.getConnection();
    }


    @Override
    public Product findById(int id) {
        try {
            final Connection conn= DbConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM product WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Product product = new Product();
            DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            while (rs.next()){
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setBrand(rs.getString("brand"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setSize(rs.getString("size"));
                product.setModel(rs.getString("model"));
                product.setColor(rs.getString("color"));
                product.setWeight(rs.getString("weight"));
                product.setExpiryDate(rs.getString("expiryDate"));
                product.setRegNo(rs.getString("regNo"));
                product.setVendor(rs.getString("vendor"));
                product.setStatus(Status.valueOf(rs.getString("status")));
                product.setImage(rs.getString("image"));
                product.setCategory(Category.valueOf(rs.getString("category")));
                product.setCreatedAt(LocalDateTime.parse(rs.getString("createdAt"),dtf));
                product.setUpdatedAt(LocalDateTime.parse(rs.getString("updatedAt"),dtf));
            }
            rs.close();
            ps.close();
            conn.close();
            return product;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Product findByNameAndCategory(String name, String category) {
        try {
            final Connection conn= DbConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM product WHERE name = ? and category = ?");
            ps.setString(1, name);
            ps.setString(2, category);
            ResultSet rs = ps.executeQuery();
            Product product = new Product();
            DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            while (rs.next()){
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setBrand(rs.getString("brand"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setSize(rs.getString("size"));
                product.setModel(rs.getString("model"));
                product.setColor(rs.getString("color"));
                product.setWeight(rs.getString("weight"));
                product.setExpiryDate(rs.getString("expiryDate"));
                product.setRegNo(rs.getString("regNo"));
                product.setVendor(rs.getString("vendor"));
                product.setStatus(Status.valueOf(rs.getString("status")));
                product.setImage(rs.getString("image"));
                product.setCategory(Category.valueOf(rs.getString("category")));
                product.setCreatedAt(LocalDateTime.parse(rs.getString("createdAt"),dtf));
                product.setUpdatedAt(LocalDateTime.parse(rs.getString("updatedAt"),dtf));
            }
            rs.close();
            ps.close();
            conn.close();
            return product;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Product> findByCategory(String category) {
        try {
            final Connection conn= DbConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM product WHERE category = ?");
            ps.setString(1, category);
            ResultSet rs = ps.executeQuery();
            List<Product> listOfProduct = new ArrayList<>();
            DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            while (rs.next()){
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setBrand(rs.getString("brand"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setSize(rs.getString("size"));
                product.setModel(rs.getString("model"));
                product.setColor(rs.getString("color"));
                product.setWeight(rs.getString("weight"));
                product.setExpiryDate(rs.getString("expiryDate"));
                product.setRegNo(rs.getString("regNo"));
                product.setVendor(rs.getString("vendor"));
                product.setStatus(Status.valueOf(rs.getString("status")));
                product.setImage(rs.getString("image"));
                product.setCategory(Category.valueOf(rs.getString("category")));
                product.setCreatedAt(LocalDateTime.parse(rs.getString("createdAt"),dtf));
                product.setUpdatedAt(LocalDateTime.parse(rs.getString("updatedAt"),dtf));
                listOfProduct.add(product);
            }
            rs.close();
            ps.close();
            conn.close();
            return listOfProduct;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Product> findAll() {
        try {
            final Connection conn= DbConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM product");
            ResultSet rs = ps.executeQuery();
            List<Product> listOfProduct = new ArrayList<>();
            DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            while (rs.next()){
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setBrand(rs.getString("brand"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setSize(rs.getString("size"));
                product.setModel(rs.getString("model"));
                product.setColor(rs.getString("color"));
                product.setWeight(rs.getString("weight"));
                product.setExpiryDate(rs.getString("expiryDate"));
                product.setRegNo(rs.getString("regNo"));
                product.setVendor(rs.getString("vendor"));
                product.setStatus(Status.valueOf(rs.getString("status")));
                product.setImage(rs.getString("image"));
                product.setCategory(Category.valueOf(rs.getString("category")));
                product.setCreatedAt(LocalDateTime.parse(rs.getString("createdAt"),dtf));
                product.setUpdatedAt(LocalDateTime.parse(rs.getString("updatedAt"),dtf));
                listOfProduct.add(product);
            }
            rs.close();
            ps.close();
            conn.close();
            return listOfProduct;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Product save(Product product) {
        try {
            final Connection conn= DbConfig.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO product(name, price, quantity, size, color, status, image, category, createdAt, updatedAt) values(?, ?, ?, ?, ?, ?, ?, ?, now(), now())");
            ps.setString(1, product.getName());
            ps.setString(2, String.valueOf(product.getPrice()));
            ps.setString(3, String.valueOf(product.getQuantity()));
            ps.setString(4, product.getSize());
            ps.setString(5, product.getColor());
            ps.setString(6, Status.AVAILABLE.name());
            ps.setString(7, product.getImage());
            ps.setString(8, product.getCategory().name());
            int result = ps.executeUpdate();
            ps.close();
            conn.close();
            if (result == 1) {
                return findByNameAndCategory(product.getName(), String.valueOf(product.getCategory()));
            }else {
                throw new DbException("unable to save user data");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer productId) {
        try {
            final Connection conn= DbConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM e_store_db.product WHERE id = ?");
            ps.setInt(1, productId);
            boolean result = ps.executeUpdate() > 0;
            System.out.println("success value:::" + result);
            ps.close();
            conn.close();
            return;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Product update(Product product) {
        try {
            final Connection conn= DbConfig.getConnection();

            PreparedStatement ps = conn.prepareStatement("UPDATE product SET name = ?, price = ?, quantity = ?, size = ?, color = ?, status = ?, image = ?, category = ?, updatedAt = now() WHERE id = ?");
            ps.setString(1, product.getName());
            ps.setString(2, String.valueOf(product.getPrice()));
            ps.setString(3, String.valueOf(product.getQuantity()));
            ps.setString(4, product.getSize());
            ps.setString(5, product.getColor());
            ps.setString(6, Status.AVAILABLE.name());
            ps.setString(7, product.getImage());
            ps.setString(8, product.getCategory().name());
            ps.setInt(9, product.getId());
            int result = ps.executeUpdate();
            ps.close();
            conn.close();
            if (result == 1) {
                return findByNameAndCategory(product.getName(), String.valueOf(product.getCategory()));
            }else {
                throw new DbException("unable to save user data");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
}
