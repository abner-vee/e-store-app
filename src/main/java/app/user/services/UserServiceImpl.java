package app.user.services;

import app.admin.dto.AdminDto;
import app.admin.model.Admin;
import app.exceptions.DbException;
import app.exceptions.InvalidCredentialException;
import app.exceptions.UserNotFoundException;
import app.user.dao.UserDao;
import app.user.dao.UserDaoImpl;
import app.user.dto.UserDto;
import app.user.model.User;
import app.user.vo.UserVO;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
private final UserDao userDao;

    public UserServiceImpl() throws SQLException {
        this.userDao = new UserDaoImpl();
    }

    @Override
    public UserVO login(String email, String password) {
        User user= userDao.findByEmail(email);
        if (user != null){
            if (user.getPassword().equals(password)){
                return UserVO.builder()
                        .id(user.getId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .createdAt(user.getCreatedAt().toString())
                        .updatedAt(user.getUpdatedAt().toString())
                        .build();
            }else {
                throw new InvalidCredentialException("Your password is incorrect");
            }
        }else {
            throw new UserNotFoundException("User not found");

        }
    }
    @Override
    public UserVO logout(String email, String password) {
        return null;
    }

    @Override
    public UserVO addAdmin(AdminDto adminDto) {
        return null;
    }

    @Override
    public UserVO signup(UserDto userDto) {
        User user = User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
        user = userDao.save(user);
        return UserVO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

    @Override
    public UserVO findById(Integer userId) {
        User user = userDao.findById(userId);
        if (user != null){
            return UserVO.builder()
                    .id(user.getId())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .createdAt(user.getCreatedAt().toString())
                    .updatedAt(user.getUpdatedAt().toString())
                    .build();
        }else {
            throw new UserNotFoundException("User not found");
        }
    }
}
