package app.order.controller;

import app.admin.services.AdminService;
import app.order.dto.CartDto;
import app.order.services.OrderService;
import app.order.services.OrderServiceImpl;
import app.order.vo.CartVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/order")
public class OrderController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final OrderService orderService;

    public OrderController( ) throws SQLException {
        this.orderService = new OrderServiceImpl();
    }


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = Integer.parseInt(req.getParameter("userId"));
        Integer productId = Integer.parseInt(req.getParameter("productId"));
        Integer quantity = Integer.parseInt(req.getParameter("quantity"));
        System.out.println("userId: " + userId);
        System.out.println("productId: " + productId);
        System.out.println("quantity: " + quantity);
        CartDto cartDto = CartDto.builder()
                .userId(userId)
                .id(productId)
                .quantity(quantity)
                .build();
        orderService.saveCart(cartDto);
        CartVO cartVO = orderService.findCartByUserId(userId);
        req.setAttribute("cart", cartVO);
        HttpSession session = req.getSession();
        session.setAttribute("cart", cartVO);
        req.getRequestDispatcher("index.jsp").forward(req, resp);


    }


    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
