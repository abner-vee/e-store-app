package app.admin.controller;

import app.admin.dto.AdminDto;
import app.admin.services.AdminService;
import app.admin.services.AdminServiceImpl;
import app.admin.vo.AdminVO;
import app.user.dto.UserDto;
import app.user.services.UserServiceImpl;
import app.user.vo.UserVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/adminsignup")
public class AdminSignupController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final AdminService adminService;

    public AdminSignupController() throws SQLException {
        this.adminService = new AdminServiceImpl();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        System.out.println("email: " + email);
        System.out.println("password: " + password);
        HttpSession httpSession = req.getSession();
        AdminDto adminDto = AdminDto.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .build();
        AdminVO adminVO = adminService.signup(adminDto);
        httpSession.setAttribute(adminVO.getEmail(), adminVO);
        httpSession.setAttribute("adminVO", adminVO);
        resp.sendRedirect("admin-dashboard.jsp");
    }
}
