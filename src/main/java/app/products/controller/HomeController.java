package app.products.controller;//copy and paste to replace the content ur HomeController:
//        package app.products.controller;

import app.products.services.ProductService;
import app.products.services.ProductServiceImpl;
import app.products.vo.ProductVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(value = "/home")
public class HomeController extends HttpServlet{
    private final ProductService productService;

    public HomeController() throws SQLException {
        this.productService = new ProductServiceImpl();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        List<ProductVO> productVOList = productService.getAllProducts();

        if(productVOList != null && !productVOList.isEmpty()){
            httpSession.setAttribute("products", productVOList);
            req.setAttribute("products",productVOList);
            System.out.println(productVOList.get(0).getName());
            System.out.println("There are " + productVOList.size() + " products");
//            resp.sendRedirect("index.jsp");

        }
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}