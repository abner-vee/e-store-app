package app.order.services;

import app.order.dto.CartDto;
import app.order.vo.CartVO;

public interface OrderService{
    CartVO findCartById(Integer id);
    CartVO saveCart(CartDto cartDto);
    void deleteCartById(Integer id);
    CartVO findCartByUserId(Integer userId);
    CartVO findCartByUserIdAndProductId(Integer userId, Integer productId);
    void deleteByUserId(Integer userId);
    void deleteByUserIdAndProductId(Integer userId, Integer productId);
    CartVO saveCartProducts(CartDto cartDto);
    void deleteCartProductsById(Integer id);
    CartVO findCartProductsByCartId(Integer cartId);
    void deleteByCartId(Integer cartId);

}
