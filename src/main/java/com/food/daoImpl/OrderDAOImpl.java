package com.food.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.food.dao.OrderDAO;
import com.food.model.Order;
import com.food.util.ConnectionUtil;

public class OrderDAOImpl implements OrderDAO {
	
	private static Connection connection = null;
    private static PreparedStatement prepareStatement = null;
    private static Statement statement = null;
    private static ResultSet res = null;

    private static final String INSERT_QUERY = "INSERT INTO orderTable (orderId, userId, restaurantId, totalAmount, status, address, paymentMode) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE orderTable SET userId = ?, restaurantId = ?, totalAmount = ?, status = ?, address=?, paymentMode = ? WHERE orderId = ?";
    private static final String DELETE_QUERY = "DELETE FROM orderTable WHERE orderId = ?";
    private static final String SELECT_QUERY = "SELECT * FROM orderTable WHERE orderId = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM orderTable WHERE userId = ?";

    public OrderDAOImpl() {
    	connection = ConnectionUtil.getConnection();
    }

	@Override
	public void addOrder(Order order) {
		try {
            prepareStatement = connection.prepareStatement(INSERT_QUERY);
            prepareStatement.setInt(1, order.getOrderId());
            prepareStatement.setInt(2, order.getUserId());
            prepareStatement.setInt(3, order.getRestaurantId());
            prepareStatement.setDouble(4, order.getTotalAmount());
            prepareStatement.setString(5, order.getStatus());
            prepareStatement.setString(6, order.getAddress());
            prepareStatement.setString(7, order.getPaymentMode());
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public Order getOrder(int orderId) {
        try {
            prepareStatement = connection.prepareStatement(SELECT_QUERY);
            prepareStatement.setInt(1, orderId);
            res = prepareStatement.executeQuery();
            
            if (res.next()) {
            	return new Order(
	            		res.getInt("orderId"),
	                    res.getInt("userId"),
	                    res.getInt("restaurantId"),
	                    res.getDate("orderDate"),
	                    res.getDouble("totalAmount"),
	                    res.getString("status"),
	                    res.getString("address"),
	                    res.getString("paymentMode")
	            );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}

	@Override
	public void updateOrder(Order order) {
		try {
            prepareStatement = connection.prepareStatement(UPDATE_QUERY);
            prepareStatement.setInt(1, order.getUserId());
            prepareStatement.setInt(2, order.getRestaurantId());
            prepareStatement.setDouble(3, order.getTotalAmount());
            prepareStatement.setString(4, order.getStatus());
            prepareStatement.setString(5, order.getAddress());
            prepareStatement.setString(6, order.getPaymentMode());
            prepareStatement.setInt(7, order.getOrderId());
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void deleteOrder(int orderId) {
		try {
            prepareStatement = connection.prepareStatement(DELETE_QUERY);
            prepareStatement.setInt(1, orderId);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public List<Order> getAllOrdersByUser(int userId) {
		List<Order> orders = new ArrayList<>();
		
        try {
            prepareStatement = connection.prepareStatement(SELECT_ALL_QUERY);
            prepareStatement.setInt(1, userId);
            res = prepareStatement.executeQuery();
            while (res.next()) {
                orders.add(new Order(
	            		res.getInt("orderId"),
	                    res.getInt("userId"),
	                    res.getInt("restaurantId"),
	                    res.getDate("orderDate"),
	                    res.getDouble("totalAmount"),
	                    res.getString("status"),
	                    res.getString("address"),
	                    res.getString("paymentMode")
	            ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
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
