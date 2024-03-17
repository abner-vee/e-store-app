package app.user.controller;

import app.user.dto.UserDto;
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

@WebServlet("/signup")
public class SignUPController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final UserService userService;

    public SignUPController() throws SQLException {
        this.userService = new UserServiceImpl();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        System.out.println("email: " + email);
        System.out.println("password: " + password);
        HttpSession httpSession = req.getSession();
        UserDto userDto = UserDto.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .build();
        UserVO userVO = userService.signup(userDto);
        httpSession.setAttribute(userVO.getEmail(), userVO);
        httpSession.setAttribute("userVO", userVO);
        resp.sendRedirect("index.jsp");
    }
}
