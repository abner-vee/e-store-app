package app.order.dao;

import app.config.db.DbConfig;
import app.exceptions.DbException;
import app.order.model.Cart;
import app.order.model.CartProducts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CartProductDaoImpl implements CartProductDao{
    @Override
    public CartProducts save(CartProducts cartProducts) {
        try {
            final Connection conn= DbConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO cart_products (cartId, productId, quantity) VALUES (?,?,?)");
            ps.setInt(1, cartProducts.getCartId());
            ps.setDouble(2, cartProducts.getProductId());
            ps.setDouble(3, cartProducts.getQuantity());
            ps.executeUpdate();
            ps.close();
            conn.close();
            return findByCartIdAndProductId(cartProducts.getCartId(), cartProducts.getProductId());
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void deleteCartProductsById(Integer cartProductId) {
        try {
            final Connection conn= DbConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM cart_products WHERE id = ?");
            ps.setInt(1, cartProductId);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public CartProducts findByCartIdAndProductId(Integer cartId, Integer productId) {
        try {
            final Connection conn= DbConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM cart_products WHERE cartId = ? AND productId = ?");
            ps.setInt(1, cartId);
            ps.setInt(2, productId);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            CartProducts cartProducts = new CartProducts();
            DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            while (rs.next()) {
                cartProducts = CartProducts.builder()
                        .id(rs.getInt("id"))
                        .cartId(rs.getInt("cartId"))
                        .productId(rs.getInt("productId"))
                        .quantity(rs.getInt("quantity"))
                        .createdAt(LocalDateTime.parse(rs.getString("createdAt"),dtf))
                        .updatedAt(LocalDateTime.parse(rs.getString("updatedAt"),dtf))
                        .build();
            }
            ps.close();
            conn.close();
            return cartProducts;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void deleteByCartId(Integer cartId) {
        try {
            final Connection conn= DbConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM cart_products WHERE cartId = ?");
            ps.setInt(1, cartId);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void deleteByCartIdAndProductId(Integer cartId, Integer productId) {
        try {
            final Connection conn= DbConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM cart_products WHERE cartId = ? AND productId = ?");
            ps.setInt(1, cartId);
            ps.setInt(2, productId);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<CartProducts> findAllByCartId(Integer cartId) {
        try {
            final Connection conn= DbConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM cart_products WHERE cartId = ?");
            ps.setInt(1, cartId);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            List<CartProducts> cartProductList = new ArrayList<CartProducts>();
            DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            while (rs.next()) {
                CartProducts cartProduct = CartProducts.builder()
                        .id(rs.getInt("id"))
                        .cartId(rs.getInt("cartId"))
                        .productId(rs.getInt("productId"))
                        .quantity(rs.getInt("quantity"))
                        .createdAt(LocalDateTime.parse(rs.getString("createdAt"),dtf))
                        .updatedAt(LocalDateTime.parse(rs.getString("updatedAt"),dtf))
                        .build();
                cartProductList.add(cartProduct);
            }
            ps.close();
            conn.close();
            return cartProductList;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
}
