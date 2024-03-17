package app.admin.controller;

import app.admin.services.AdminService;
import app.admin.services.AdminServiceImpl;
import app.admin.vo.AdminVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value = "/admin")
public class AdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final AdminService adminService;

    public AdminController() throws SQLException {
        this.adminService = new AdminServiceImpl();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("admin-login.jsp");
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        System.out.println("email: " + email);
        System.out.println("password: " + password);
        HttpSession httpSession = req.getSession();
        String loggedInUser = (String) httpSession.getAttribute(email);
        if (loggedInUser != null) {
            resp.sendRedirect("admin-dashboard.jsp");
        } else {
            AdminVO adminVO = adminService.login(email, password);
            httpSession.setAttribute("email", adminVO.getEmail());
            httpSession.setAttribute("adminVO", adminVO);
            resp.sendRedirect("admin-dashboard.jsp");
        }
    }
}
