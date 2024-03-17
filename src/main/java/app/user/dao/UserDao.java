package app.user.dao;

import app.admin.model.Admin;
import app.user.model.User;
import app.user.vo.UserVO;

import java.util.List;

public interface UserDao {
    User findById(int id);
    User findByEmail(String email);
    List<User> findAll();

    User save(User user);
}
