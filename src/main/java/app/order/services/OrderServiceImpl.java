package app.order.services;

import app.order.dao.CartDao;
import app.order.dao.CartDaoImpl;
import app.order.dao.CartProductDao;
import app.order.dao.CartProductDaoImpl;
import app.order.dto.CartDto;
import app.order.model.Cart;
import app.order.model.CartProducts;
import app.order.vo.CartProductVO;
import app.order.vo.CartVO;
import app.products.dao.ProductDao;
import app.products.dao.ProductDaoImpl;
import app.products.model.Product;
import app.products.services.ProductService;
import app.products.services.ProductServiceImpl;
import app.products.vo.ProductVO;
import app.user.dao.UserDao;
import app.user.dao.UserDaoImpl;
import app.user.model.User;
import app.user.services.UserService;
import app.user.services.UserServiceImpl;
import app.user.vo.UserVO;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService{
    private final CartDao cartDao;
    private final CartProductDao cartProductDao;
    private final ProductDao productDao;
    private final UserDao userDao;
    private final UserService userService;
    private final ProductService productService;


    public OrderServiceImpl() throws SQLException {
        this.productService = new ProductServiceImpl();
        this.userService = new UserServiceImpl();
        this.userDao = new UserDaoImpl();
        this.productDao = new ProductDaoImpl();
        this.cartDao = new CartDaoImpl();
        this.cartProductDao = new CartProductDaoImpl();
    }

    @Override
    public CartVO findCartById(Integer id) {
        Cart cart = cartDao.findById(id);
       List<CartProducts>  cartProducts = cartProductDao.findAllByCartId(id);
       List<CartProductVO> productList = cartProducts.stream().map(cartProduct -> {
           ProductVO productVO = productService.findById(cartProduct.getProductId());
              return CartProductVO.builder()
                     .quantity(cartProduct.getQuantity())
                     .productVO(productVO)
                     .build();
       }).collect(Collectors.toList());
        UserVO userVO = userService.findById(cart.getUserId());
        return CartVO.builder()
                .id(cart.getId())
                .totalPrice(cart.getTotalPrice())
                .user(userVO)
                .products(productList)
                .build();
    }

    @Override
    public CartVO saveCart(CartDto cartDto) {
        System.out.println(cartDto);
        Cart cart = cartDao.findByUserId(cartDto.getUserId());
        if (cart == null){
            Cart cartVO = cartDao.save(Cart.builder()
                    .userId(cartDto.getUserId())
                    .totalPrice(cartDto.getTotalPrice())
                    .build());
            System.out.println(cartVO);
            CartProducts cartProducts = CartProducts.builder()
                    .cartId(cartVO.getId())
                    .productId(cartDto.getId())
                    .quantity(cartDto.getQuantity())
                    .build();
            System.out.println(cartProducts);
            CartProducts savedCartProducts = cartProductDao.save(cartProducts);
            System.out.println(savedCartProducts);
            return findCartById(cartVO.getId());
        }
        Cart cartVO = cartDao.findByUserId(cartDto.getUserId());
        System.out.println(cart);
        CartProducts cartProducts = CartProducts.builder()
                .cartId(cartVO.getId())
                .productId(cartDto.getId())
                .quantity(cartDto.getQuantity())
                .build();
        System.out.println(cartProducts);
        System.out.println("--------------------------------");
        CartProducts savedCartProducts = cartProductDao.save(cartProducts);
        System.out.println(savedCartProducts);
        return findCartById(cart.getId());
    }

    @Override
    public void deleteCartById(Integer id) {

    }

    @Override
    public CartVO findCartByUserId(Integer userId) {
        Cart cart = cartDao.findByUserId(userId);
        List<CartProducts>  cartProducts = cartProductDao.findAllByCartId(cart.getId());
        List<CartProductVO> productList = cartProducts.stream().map(cartProduct -> {
            ProductVO productVO = productService.findById(cartProduct.getProductId());
            return CartProductVO.builder()
                    .quantity(cartProduct.getQuantity())
                    .productVO(productVO)
                    .build();
        }).collect(Collectors.toList());
UserVO userVO = userService.findById(cart.getUserId());
        return CartVO.builder()
                .id(cart.getId())
                .totalPrice(cart.getTotalPrice())
                .user(userVO)
                .products(productList)
                .build();
    }

    @Override
    public CartVO findCartByUserIdAndProductId(Integer userId, Integer productId) {
        return null;
    }

    @Override
    public void deleteByUserId(Integer userId) {

    }

    @Override
    public void deleteByUserIdAndProductId(Integer userId, Integer productId) {

    }

    @Override
    public CartVO saveCartProducts(CartDto cartDto) {
        return null;
    }

    @Override
    public void deleteCartProductsById(Integer id) {

    }

    @Override
    public CartVO findCartProductsByCartId(Integer cartId) {
        return null;
    }

    @Override
    public void deleteByCartId(Integer cartId) {

    }
}
