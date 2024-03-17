package app.admin.dao;

import app.admin.model.Admin;

import java.util.List;

public interface AdminDao {
    Admin findById(int id);
    Admin findByEmail(String email);
    List<Admin> findAll();

    Admin save(Admin admin);
}
