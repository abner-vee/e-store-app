package app.order.dao;

import app.order.model.Cart;
import app.order.model.CartProducts;

import java.util.List;

public interface CartDao {
    Cart findById(Integer id);
    List<Cart> findAll();
    Cart save(Cart cart);
    void deleteCartById(Integer cartId);
    Cart findByUserId(Integer userId);
    Cart findByUserIdAndProductId(Integer userId, Integer productId);
    void deleteByUserId(Integer userId);
    void deleteByUserIdAndProductId(Integer userId, Integer productId);
    CartProducts save(CartProducts cartProducts);
    List<CartProducts> findAllByCartId(Integer cartId);
    void deleteCartProductsById(Integer cartProductId);
    CartProducts findByCartIdAndProductId(Integer cartId, Integer productId);
    void deleteByCartId(Integer cartId);
    void deleteByCartIdAndProductId(Integer cartId, Integer productId);
}
