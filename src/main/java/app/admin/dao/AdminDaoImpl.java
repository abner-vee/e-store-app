package app.admin.dao;

import app.admin.enums.Role;
import app.admin.model.Admin;
import app.config.db.DbConfig;
import app.exceptions.DbException;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

public class AdminDaoImpl implements AdminDao{
//    private final Connection conn;

    public AdminDaoImpl() throws SQLException {
//        this.conn = DbConfig.getConnection();
    }

    @Override
    public Admin findById(int id) {
        try {
            final Connection conn= DbConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM admin WHERE id = ?");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            Admin admin = new Admin();
            DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            while (rs.next()){
                admin.setId(rs.getInt("id"));
                admin.setFirstName(rs.getString("firstName"));
                admin.setLastName(rs.getString("lastname"));
                admin.setEmail(rs.getString("email"));
                admin.setPassword(rs.getString("password"));
                admin.setRole(Role.valueOf(rs.getString("role")));
                admin.setCreatedAt(LocalDateTime.parse(rs.getString("createdAt"),dtf));
                admin.setUpdatedAt(LocalDateTime.parse(rs.getString("updatedAt"),dtf));
            }
            rs.close();
            ps.close();
            conn.close();
            return admin;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Admin findByEmail(String email) {
        try {
            final Connection conn= DbConfig.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM admin WHERE email = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            Admin admin = new Admin();
            DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            while (rs.next()){
                admin.setId(rs.getInt("id"));
                admin.setFirstName(rs.getString("firstName"));
                System.out.println(rs.getString("firstName"));
                admin.setLastName(rs.getString("lastname"));
                admin.setEmail(rs.getString("email"));
                admin.setPassword(rs.getString("password"));
                admin.setRole(Role.valueOf(rs.getString("role")));
                admin.setCreatedAt(LocalDateTime.parse(rs.getString("createdAt"),dtf));
                admin.setUpdatedAt(LocalDateTime.parse(rs.getString("updatedAt"),dtf));
            }
            rs.close();
            ps.close();
            conn.close();
            return admin;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Admin> findAll() {
        return null;
    }

    @Override
    public Admin save(Admin admin) {
        try {
            final Connection conn= DbConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO admin(firstName, lastName, role, email, password, createdAt, updatedAt) values(?, ?, ?, ?, ?, now(), now())");
            ps.setString(1, admin.getFirstName());
            ps.setString(2, admin.getLastName());
            ps.setString(3, String.valueOf(admin.getRole()));
            ps.setString(4, admin.getEmail());
            ps.setString(5, admin.getPassword());
            int result = ps.executeUpdate();
            ps.close();
            conn.close();
            if (result == 1) {
                return findByEmail(admin.getEmail());
            }else {
                throw new DbException("unable to save user data");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
}
