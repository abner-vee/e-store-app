package app.products.controller;

import app.products.dto.ProductDto;
import app.products.services.ProductService;
import app.products.services.ProductServiceImpl;
import app.products.vo.ProductVO;
import app.user.dto.UserDto;
import app.user.vo.UserVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(value = "/product")
public class ProductController extends HttpServlet {
    private final ProductService productService;

    public ProductController() throws SQLException {
        this.productService = new ProductServiceImpl();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String id = sb.toString();
        System.out.println("an id: " + id);
        HttpSession httpSession = req.getSession();
        List<ProductVO> productVOList = productService.getAllProducts();

        if (productVOList != null && !productVOList.isEmpty()) {
            httpSession.setAttribute("products", productVOList);
            req.setAttribute("products", productVOList);
            System.out.println(productVOList.get(0).getName());
            System.out.println("There are " + productVOList.size() + " products");
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        name,price,quantity,size,color,image,category--%>
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        String quantity = req.getParameter("quantity");
        String size = req.getParameter("size");
        String color = req.getParameter("color");
        String image = req.getParameter("image");
        String category = req.getParameter("category");
        HttpSession httpSession = req.getSession();
        ProductDto productDto = ProductDto.builder()
                .name(name)
                .price(Double.parseDouble(price))
                .quantity(Integer.parseInt(quantity))
                .size(size)
                .color(color)
                .image(image)
                .category(category)
                .build();
        ProductVO productVO = productService.addProduct(productDto);
        httpSession.setAttribute("productVO", productVO);
        List<ProductVO> listOfProducts = productService.getAllProducts();
        System.out.println("the number of products are: {}" + listOfProducts.size());
        httpSession.setAttribute("products", listOfProducts);
        resp.sendRedirect("admin-dashboard.jsp");
    }


    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String params = sb.toString();
        String stringId = params.split("&")[0].substring(3);
        String stringType = params.split("&")[1].substring(5);
        int id = Integer.parseInt(stringId);
        HttpSession httpSession = req.getSession();
        System.out.println("product debug");
        if (stringType.equals("fetching")){
            System.out.println("i am fetching");
            ProductVO productVO = productService.getProductById(id);
            System.out.println(productVO);
            httpSession.setAttribute("productVO", productVO);
        }else {
            System.out.println("checking");
            String name = req.getParameter("name");
            String price = req.getParameter("price");
            String quantity = req.getParameter("quantity");
            String size = req.getParameter("size");
            String color = req.getParameter("color");
            String image = req.getParameter("image");
            String category = req.getParameter("category");
            ProductDto productDto = ProductDto.builder()
                    .name(name)
                    .price(Double.parseDouble(price))
                    .quantity(Integer.parseInt(quantity))
                    .size(size)
                    .color(color)
                    .image(image)
                    .category(category)
                    .build();
            ProductVO productVO = productService.updateProduct(productDto);
            httpSession.setAttribute("productVO", productVO);
            List<ProductVO> listOfProducts = productService.getAllProducts();
            System.out.println("the number of products for update: {}" + listOfProducts.size());
            httpSession.setAttribute("products", listOfProducts);
            resp.sendRedirect("admin-dashboard.jsp");
        }
        System.out.println("an id: " + id);
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String id = sb.toString().substring(3);
        System.out.println("id from fe: " + id);
        Integer productId = Integer.valueOf(id);
        System.out.println("this is a product id: " + productId);
        productService.deleteProductById(productId);
        HttpSession httpSession = req.getSession();
        List<ProductVO> listOfProducts = productService.getAllProducts();
        System.out.println("the number of products are: {}" + listOfProducts.size());
        httpSession.setAttribute("products", listOfProducts);
        resp.sendRedirect("admin-dashboard.jsp");
    }
}
