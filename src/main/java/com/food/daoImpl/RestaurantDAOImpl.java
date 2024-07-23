package com.food.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.food.dao.RestaurantDAO;
import com.food.model.Restaurant;
import com.food.util.ConnectionUtil;

public class RestaurantDAOImpl implements RestaurantDAO {
	
	private static Connection connection = null;
    private static PreparedStatement prepareStatement = null;
    private static Statement statement = null;
    private static ResultSet res = null;

    private static final String INSERT_QUERY = "INSERT INTO restaurant (name, cuisineType, deliveryTime, address, phone, adminUserId, rating, isActive, imagePath) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE restaurant SET name = ?, cuisineType = ?, deliveryTime = ?, address = ?, phone = ?, adminUserId = ?, rating = ?, isActive = ?, imagePath = ? WHERE restaurantId = ?";
    private static final String DELETE_QUERY = "DELETE FROM restaurant WHERE restaurantId = ?";
    private static final String SELECT_QUERY = "SELECT * FROM restaurant WHERE restaurantId = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM restaurant";

    public RestaurantDAOImpl() {
        connection = ConnectionUtil.getConnection();
    }

	@Override
	public void addRestaurant(Restaurant restaurant) {
		try {
            prepareStatement = connection.prepareStatement(INSERT_QUERY);
            prepareStatement.setString(1, restaurant.getName());
            prepareStatement.setString(2, restaurant.getCuisineType());
            prepareStatement.setInt(3, restaurant.getDeliveryTime());
            prepareStatement.setString(4, restaurant.getAddress());
            prepareStatement.setString(5, restaurant.getPhone());
            prepareStatement.setInt(6, restaurant.getAdminUserId());
            prepareStatement.setDouble(7, restaurant.getRating());
            prepareStatement.setBoolean(8, restaurant.isActive());
            prepareStatement.setString(9, restaurant.getImagePath());
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public Restaurant getRestaurant(int restaurantId) {
		try {
            prepareStatement = connection.prepareStatement(SELECT_QUERY);
            prepareStatement.setInt(1, restaurantId);
            res = prepareStatement.executeQuery();
            
            if (res.next()) {
                return new Restaurant(
                    res.getInt("restaurantId"),
                    res.getString("name"),
                    res.getString("cuisineType"),
                    res.getInt("deliveryTime"),
                    res.getString("address"),
                    res.getString("phone"),
                    res.getInt("adminUserId"),
                    res.getDouble("rating"),
                    res.getBoolean("isActive"),
                    res.getString("imagePath")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return null;
	}

	@Override
	public void updateRestaurant(Restaurant restaurant) {
		try {
            prepareStatement = connection.prepareStatement(UPDATE_QUERY);
            prepareStatement.setString(1, restaurant.getName());
            prepareStatement.setString(2, restaurant.getCuisineType());
            prepareStatement.setInt(3, restaurant.getDeliveryTime());
            prepareStatement.setString(4, restaurant.getAddress());
            prepareStatement.setString(5, restaurant.getPhone());
            prepareStatement.setInt(6, restaurant.getAdminUserId());
            prepareStatement.setDouble(7, restaurant.getRating());
            prepareStatement.setBoolean(8, restaurant.isActive());
            prepareStatement.setString(9, restaurant.getImagePath());
            prepareStatement.setInt(10, restaurant.getRestaurantId());
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void deleteRestaurant(int restaurantId) {
		try {
            prepareStatement = connection.prepareStatement(DELETE_QUERY);
            prepareStatement.setInt(1, restaurantId);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public List<Restaurant> getAllRestaurant() {
		List<Restaurant> restaurants = new ArrayList<>();
        
        try {
            prepareStatement = connection.prepareStatement(SELECT_ALL_QUERY);
            res = prepareStatement.executeQuery();
            while (res.next()) {
                restaurants.add(new Restaurant(
                    res.getInt("restaurantId"),
                    res.getString("name"),
                    res.getString("cuisineType"),
                    res.getInt("deliveryTime"),
                    res.getString("address"),
                    res.getString("phone"),
                    res.getInt("adminUserId"),
                    res.getDouble("rating"),
                    res.getBoolean("isActive"),
                    res.getString("imagePath")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurants;
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
