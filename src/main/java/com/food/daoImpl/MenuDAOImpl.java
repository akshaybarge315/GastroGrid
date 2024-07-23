package com.food.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.food.dao.MenuDAO;
import com.food.model.Menu;
import com.food.util.ConnectionUtil;

public class MenuDAOImpl implements MenuDAO {
	
	private static Connection connection = null;
    private static PreparedStatement prepareStatement = null;
    private static Statement statement = null;
    private static ResultSet res = null;

    private static final String INSERT_QUERY = "INSERT INTO menu (restaurantId, itemName, description, price, rating, isAvailable, imagePath) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE menu SET restaurantId = ?, itemName = ?, description = ?, price = ?, rating = ?, isAvailable = ?, imagePath = ? WHERE menuId = ?";
    private static final String DELETE_QUERY = "DELETE FROM menu WHERE menuId = ?";
    private static final String SELECT_QUERY = "SELECT * FROM menu WHERE menuId = ?";
    private static final String SELECT_ALL_BY_RESTAURANT_QUERY = "SELECT * FROM menu WHERE restaurantId = ?";

    public MenuDAOImpl() {
        connection = ConnectionUtil.getConnection();
    }

	@Override
	public void addMenu(Menu menu) {
		try {
            prepareStatement = connection.prepareStatement(INSERT_QUERY);
            prepareStatement.setInt(1, menu.getRestaurantId());
            prepareStatement.setString(2, menu.getItemName());
            prepareStatement.setString(3, menu.getDescription());
            prepareStatement.setDouble(4, menu.getPrice());
            prepareStatement.setBoolean(5, menu.isAvailable());
            prepareStatement.setString(6, menu.getImagePath());
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public Menu getMenu(int menuId) {
		try {
            prepareStatement = connection.prepareStatement(SELECT_QUERY);
            prepareStatement.setInt(1, menuId);
            res = prepareStatement.executeQuery();
            
            if (res.next()) {
                return new Menu(
                    res.getInt("menuId"),
                    res.getInt("restaurantId"),
                    res.getString("itemName"),
                    res.getString("description"),
                    res.getDouble("price"),
                    res.getBoolean("isAvailable"),
                    res.getString("imagePath")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}

	@Override
	public void updateMenu(Menu menu) {
		try {
            prepareStatement = connection.prepareStatement(UPDATE_QUERY);
            prepareStatement.setInt(1, menu.getRestaurantId());
            prepareStatement.setString(2, menu.getItemName());
            prepareStatement.setString(3, menu.getDescription());
            prepareStatement.setDouble(4, menu.getPrice());
            prepareStatement.setBoolean(5, menu.isAvailable());
            prepareStatement.setString(6, menu.getImagePath());
            prepareStatement.setInt(7, menu.getMenuId());
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void deleteMenu(int menuId) {
		try {
            prepareStatement = connection.prepareStatement(DELETE_QUERY);
            prepareStatement.setInt(1, menuId);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public List<Menu> getAllMenuByRestaurant(int restaurantId) {
		List<Menu> menus = new ArrayList<>();
        
        try {
            prepareStatement = connection.prepareStatement(SELECT_ALL_BY_RESTAURANT_QUERY);
            prepareStatement.setInt(1, restaurantId);
            res = prepareStatement.executeQuery();
            while (res.next()) {
                menus.add(new Menu(
                    res.getInt("menuId"),
                    res.getInt("restaurantId"),
                    res.getString("itemName"),
                    res.getString("description"),
                    res.getDouble("price"),
                    res.getBoolean("isAvailable"),
                    res.getString("imagePath")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menus;
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
