package com.food.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.food.dao.UserDAO;
import com.food.model.User;
import com.food.util.ConnectionUtil;

public class UserDAOImpl implements UserDAO {
	
	private static Connection connection = null;
    private static PreparedStatement prepareStatement = null;
    private static Statement statement = null;
    private static ResultSet res = null;

    private static final String INSERT_QUERY = "INSERT INTO user (username, password, email, phone, role) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE user SET username = ?, password = ?, email = ?, phone = ?, role = ? WHERE userId = ?";
    private static final String DELETE_QUERY = "DELETE FROM user WHERE userId = ?";
    private static final String SELECT_QUERY = "SELECT * FROM user WHERE userId = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM user";
    private static final String SELECT_BY_USERNAME_QUERY = "SELECT * FROM user WHERE username = ?";

    public UserDAOImpl() {
    	connection = ConnectionUtil.getConnection();
    }

	@Override
	public void addUser(User user) {
		try {
            prepareStatement = connection.prepareStatement(INSERT_QUERY);
            prepareStatement.setString(1, user.getUsername());
            prepareStatement.setString(2, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            prepareStatement.setString(3, user.getEmail());
            prepareStatement.setString(4, user.getPhone());
            prepareStatement.setString(5, user.getRole());
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public User getUser(int userId) {
		try {
            prepareStatement = connection.prepareStatement(SELECT_QUERY);
            prepareStatement.setInt(1, userId);
            res = prepareStatement.executeQuery();
            
            if (res.next()) {
            	return new User(
	            		res.getInt("userId"),
	                    res.getString("username"),
	                    res.getString("password"),
	                    res.getString("email"),
	                    res.getString("phone"),
	                    res.getString("role")
	            );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}
	
	@Override
	public User getUserByUsername(String username) {
        try {
            prepareStatement = connection.prepareStatement(SELECT_BY_USERNAME_QUERY);
            prepareStatement.setString(1, username);
            res = prepareStatement.executeQuery();

            if (res.next()) {
                return new User(
                    res.getInt("userId"),
                    res.getString("username"),
                    res.getString("password"),
                    res.getString("email"),
                    res.getString("phone"),
                    res.getString("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

	@Override
	public void updateUser(User user) {
		try {
            prepareStatement = connection.prepareStatement(UPDATE_QUERY);
            prepareStatement.setString(1, user.getUsername());
            prepareStatement.setString(2, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            prepareStatement.setString(3, user.getEmail());
            prepareStatement.setString(4, user.getPhone());
            prepareStatement.setString(5, user.getRole());
            prepareStatement.setInt(6, user.getUserId());
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void deleteUser(int userId) {
		try {
            prepareStatement = connection.prepareStatement(DELETE_QUERY);
            prepareStatement.setInt(1, userId);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public List<User> getAllUser() {
		List<User> users = new ArrayList<>();
		
        try {
            prepareStatement = connection.prepareStatement(SELECT_ALL_QUERY);
            res = prepareStatement.executeQuery();
            while (res.next()) {
                users.add(new User(
	            		res.getInt("userId"),
	                    res.getString("username"),
	                    res.getString("password"),
	                    res.getString("email"),
	                    res.getString("phone"),
	                    res.getString("role")
	            ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
	}

	@Override
    public void finalize() throws Throwable {
        if (res != null) {
            res.close();
        }
        if (prepareStatement != null) {
            prepareStatement.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
	
}
