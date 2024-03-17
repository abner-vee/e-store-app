package app.user.controller;

import app.admin.services.AdminServiceImpl;
import app.admin.vo.AdminVO;
import app.user.services.UserService;
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

@WebServlet("/user-auth")
public class UserAuthController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final UserService userService;

    public UserAuthController() throws SQLException {
        this.userService = new UserServiceImpl();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        System.out.println("email: " + email);
        System.out.println("password: " + password);
        HttpSession httpSession = req.getSession();
        UserVO loggedInUser = (UserVO) httpSession.getAttribute(email);
        if (loggedInUser != null) {
            resp.sendRedirect("index.jsp");
        } else {
            UserVO userVO = userService.login(email, password);
            httpSession.setAttribute(userVO.getEmail(), userVO);
            httpSession.setAttribute("userVO", userVO);
            resp.sendRedirect("index.jsp");
        }
    }
}
