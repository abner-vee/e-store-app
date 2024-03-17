package app.order.dao;

import app.order.model.Cart;
import app.order.model.CartProducts;

import java.util.List;

public interface CartProductDao{
    CartProducts save(CartProducts cartProducts);
    void deleteCartProductsById(Integer cartProductId);
    CartProducts findByCartIdAndProductId(Integer cartId, Integer productId);
    void deleteByCartId(Integer cartId);
    void deleteByCartIdAndProductId(Integer cartId, Integer productId);
    List<CartProducts> findAllByCartId(Integer cartId);

}
