package app.user.dao;

import app.admin.enums.Role;
import app.admin.model.Admin;
import app.config.db.DbConfig;
import app.exceptions.DbException;
import app.user.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class UserDaoImpl implements UserDao {
//    private final Connection conn;

    public UserDaoImpl() throws SQLException {
//        this.conn = DbConfig.getConnection();
    }

    @Override
    public User findById(int id) {
        try {
            final Connection conn= DbConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM user WHERE id = ?");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            User user = new User();
            DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            while (rs.next()){
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setCreatedAt(LocalDateTime.parse(rs.getString("createdAt"),dtf));
                user.setUpdatedAt(LocalDateTime.parse(rs.getString("updatedAt"),dtf));
            }
            rs.close();
            ps.close();
            conn.close();
            return user;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public User findByEmail(String email) {
        try {
            final Connection conn= DbConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM user WHERE email = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            User user = new User();
            DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            while (rs.next()){
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("firstName"));
                System.out.println(rs.getString("firstName"));
                user.setLastName(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setCreatedAt(LocalDateTime.parse(rs.getString("createdAt"),dtf));
                user.setUpdatedAt(LocalDateTime.parse(rs.getString("updatedAt"),dtf));
            }
            rs.close();
            ps.close();
            conn.close();
            return user;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User save(User user) {
        try {
            final Connection conn= DbConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO user(firstName, lastName, email, password, createdAt, updatedAt) values(?, ?, ?, ?, now(), now())");
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            int result = ps.executeUpdate();
            ps.close();
            conn.close();
           if (result == 1) {
               return findByEmail(user.getEmail());
           }else {
               throw new DbException("unable to save user data");
           }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
}
