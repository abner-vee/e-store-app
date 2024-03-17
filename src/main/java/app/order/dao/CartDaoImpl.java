package app.order.dao;

import app.config.db.DbConfig;
import app.exceptions.DbException;
import app.order.model.Cart;
import app.order.model.CartProducts;
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

public class CartDaoImpl implements CartDao{
    @Override
    public Cart findById(Integer id) {
        try {
            final Connection conn= DbConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM cart WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Cart cart = new Cart();
            DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            while (rs.next()){
                cart.setId(rs.getInt("id"));
                cart.setUserId(Integer.valueOf(rs.getString("userId")));
                cart.setTotalPrice(Double.parseDouble(rs.getString("totalPrice")));
                cart.setCreatedAt(LocalDateTime.parse(rs.getString("createdAt"),dtf));
                cart.setUpdatedAt(LocalDateTime.parse(rs.getString("updatedAt"),dtf));
            }
            rs.close();
            ps.close();
            conn.close();
            return cart;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Cart> findAll() {
        try{
            final Connection conn= DbConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM cart");
            ResultSet rs = ps.executeQuery();
            List<Cart> cartList = new ArrayList<>();
            DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            while (rs.next()){
                Cart cart = new Cart();
                cart.setId(rs.getInt("id"));
                cart.setUserId(Integer.valueOf(rs.getString("userId")));
                cart.setTotalPrice(Double.parseDouble(rs.getString("price")));
                cart.setCreatedAt(LocalDateTime.parse(rs.getString("createdAt"),dtf));
                cart.setUpdatedAt(LocalDateTime.parse(rs.getString("updatedAt"),dtf));
                cartList.add(cart);
            }
            rs.close();
            ps.close();
            conn.close();
            return cartList;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Cart save(Cart cart) {
        try {
            final Connection conn= DbConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO cart (userId, totalPrice) VALUES (?,?)");
            ps.setInt(1, cart.getUserId());
            ps.setDouble(2, cart.getTotalPrice());
            ps.executeUpdate();
            ps.close();
            conn.close();
            return cart;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void deleteCartById(Integer cartId) {
        try {
            final Connection conn= DbConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM cart WHERE id = ?");
            ps.setInt(1, cartId);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Cart findByUserId(Integer userId) {
        try {
            final Connection conn= DbConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM cart WHERE userId = ?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            Cart cart = new Cart();
            DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            while (rs.next()){
                cart.setId(rs.getInt("id"));
                cart.setUserId(Integer.valueOf(rs.getString("userId")));
                cart.setTotalPrice(Double.parseDouble(rs.getString("totalPrice")));
                cart.setCreatedAt(LocalDateTime.parse(rs.getString("createdAt"),dtf));
                cart.setUpdatedAt(LocalDateTime.parse(rs.getString("updatedAt"),dtf));
            }
            rs.close();
            ps.close();
            conn.close();
            return cart;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Cart findByUserIdAndProductId(Integer userId, Integer productId) {
        try {
            final Connection conn= DbConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM cart WHERE userId = ? AND productId = ?");
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ResultSet rs = ps.executeQuery();
            Cart cart = new Cart();
            DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            while (rs.next()){
                cart.setId(rs.getInt("id"));
                cart.setUserId(Integer.valueOf(rs.getString("userId")));
                cart.setTotalPrice(Double.parseDouble(rs.getString("totalPrice")));
                cart.setCreatedAt(LocalDateTime.parse(rs.getString("createdAt"),dtf));
                cart.setUpdatedAt(LocalDateTime.parse(rs.getString("updatedAt"),dtf));
            }
            rs.close();
            ps.close();
            conn.close();
            return cart;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void deleteByUserId(Integer userId) {
        try {
            final Connection conn= DbConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM cart WHERE userId = ?");
            ps.setInt(1, userId);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void deleteByUserIdAndProductId(Integer userId, Integer productId) {
        try {
            final Connection conn= DbConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM cart WHERE userId = ? AND productId = ?");
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public CartProducts save(CartProducts cartProducts) {
        try {
            final Connection conn= DbConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO cart_products (cartId, productId, quantity) VALUES (?,?,?)");
            ps.setInt(1, cartProducts.getCartId());
            ps.setInt(2, cartProducts.getProductId());
            ps.setInt(3, cartProducts.getQuantity());
            ps.executeUpdate();
            ps.close();
            conn.close();
            return cartProducts;
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
            ResultSet rs = ps.executeQuery();
            List<CartProducts> cartProductsList = new ArrayList<>();
            while (rs.next()){
                CartProducts cartProducts = new CartProducts();
                cartProducts.setId(rs.getInt("id"));
                cartProducts.setCartId(rs.getInt("cartId"));
                cartProducts.setProductId(rs.getInt("productId"));
                cartProducts.setQuantity(rs.getInt("quantity"));
                cartProductsList.add(cartProducts);
            }
            rs.close();
            ps.close();
            conn.close();
            return cartProductsList;
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
            ResultSet rs = ps.executeQuery();
            CartProducts cartProducts = new CartProducts();
            while (rs.next()){
                cartProducts.setId(rs.getInt("id"));
                cartProducts.setCartId(rs.getInt("cartId"));
                cartProducts.setProductId(rs.getInt("productId"));
                cartProducts.setQuantity(rs.getInt("quantity"));
            }
            rs.close();
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
}
