package app.admin.services;

import app.admin.dao.AdminDao;
import app.admin.dao.AdminDaoImpl;
import app.admin.dto.AdminDto;
import app.admin.enums.Role;
import app.admin.model.Admin;
import app.admin.vo.AdminVO;
import app.user.model.User;
import app.user.vo.UserVO;

import java.sql.SQLException;

public class AdminServiceImpl implements AdminService{
private final AdminDao adminDao;

    public AdminServiceImpl() throws SQLException {
        this.adminDao = new AdminDaoImpl();
    }

    @Override
    public AdminVO login(String email, String password) {
        Admin admin = adminDao.findByEmail(email);
        if (admin != null){
            if (admin.getPassword().equals(password)){
                return AdminVO.builder()
                        .id(admin.getId())
                        .firstName(admin.getFirstName())
                        .lastName(admin.getLastName())
                        .email(admin.getEmail())
                        .role(admin.getRole().name())
                        .createdAt(admin.getCreatedAt().toString())
                        .updatedAt(admin.getUpdatedAt().toString())
                        .build();
            }
        }
        return null;
    }

    @Override
    public AdminVO logout(String email, String password) {
        return null;
    }

    @Override
    public AdminVO addAdmin(AdminDto adminDto) {
        return null;
    }

    @Override
    public AdminVO signup(AdminDto adminDto) {
        Admin admin = Admin.builder()
                .firstName(adminDto.getFirstName())
                .role(Role.ADMIN)
                .lastName(adminDto.getLastName())
                .email(adminDto.getEmail())
                .password(adminDto.getPassword())
                .build();
        admin = adminDao.save(admin);
        return AdminVO.builder()
                .id(admin.getId())
                .role(String.valueOf(admin.getRole()))
                .firstName(admin.getFirstName())
                .lastName(admin.getLastName())
                .email(admin.getEmail())
                .build();

    }
}
