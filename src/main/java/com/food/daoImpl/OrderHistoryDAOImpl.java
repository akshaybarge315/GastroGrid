package com.food.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.food.dao.OrderHistoryDAO;
import com.food.model.OrderHistory;
import com.food.util.ConnectionUtil;

public class OrderHistoryDAOImpl implements OrderHistoryDAO {
	
	private static Connection connection = null;
    private static PreparedStatement prepareStatement = null;
    private static Statement statement = null;
    private static ResultSet res = null;

    private static final String INSERT_QUERY = "INSERT INTO orderHistory (userID, orderID, orderDate, totalAmount, status) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE orderHistory SET userID = ?, orderID = ?, orderDate = ?, totalAmount = ?, status = ? WHERE orderHistoryID = ?";
    private static final String DELETE_QUERY = "DELETE FROM orderHistory WHERE orderHistoryID = ?";
    private static final String SELECT_QUERY = "SELECT * FROM orderHistory WHERE orderHistoryID = ?";
    private static final String SELECT_ALL_BY_USER_QUERY = "SELECT * FROM orderHistory WHERE userID = ?";

    public OrderHistoryDAOImpl() {
        connection = ConnectionUtil.getConnection();
    }

	@Override
	public void addOrderHistory(OrderHistory orderHistory) {
		try {
            prepareStatement = connection.prepareStatement(INSERT_QUERY);
            prepareStatement.setInt(1, orderHistory.getUserId());
            prepareStatement.setInt(2, orderHistory.getOrderId());
            prepareStatement.setTimestamp(3, new java.sql.Timestamp(orderHistory.getOrderDate().getTime()));
            prepareStatement.setDouble(4, orderHistory.getTotalAmount());
            prepareStatement.setString(5, orderHistory.getStatus());
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public OrderHistory getOrderHistory(int orderHistoryId) {
		try {
            prepareStatement = connection.prepareStatement(SELECT_QUERY);
            prepareStatement.setInt(1, orderHistoryId);
            res = prepareStatement.executeQuery();

            if (res.next()) {
                return new OrderHistory(
                    res.getInt("orderHistoryID"),
                    res.getInt("userID"),
                    res.getInt("orderID"),
                    res.getTimestamp("orderDate"),
                    res.getDouble("totalAmount"),
                    res.getString("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}

	@Override
	public void updateOrderHistory(OrderHistory orderHistory) {
		try {
            prepareStatement = connection.prepareStatement(UPDATE_QUERY);
            prepareStatement.setInt(1, orderHistory.getUserId());
            prepareStatement.setInt(2, orderHistory.getOrderId());
            prepareStatement.setTimestamp(3, new java.sql.Timestamp(orderHistory.getOrderDate().getTime()));
            prepareStatement.setDouble(4, orderHistory.getTotalAmount());
            prepareStatement.setString(5, orderHistory.getStatus());
            prepareStatement.setInt(6, orderHistory.getOrderHistoryId());
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void deleteOrderHistory(int orderHistoryId) {
		try {
            prepareStatement = connection.prepareStatement(DELETE_QUERY);
            prepareStatement.setInt(1, orderHistoryId);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public List<OrderHistory> getOrderHistoriesByUser(int userId) {
		List<OrderHistory> orderHistories = new ArrayList<>();

        try {
            prepareStatement = connection.prepareStatement(SELECT_ALL_BY_USER_QUERY);
            prepareStatement.setInt(1, userId);
            res = prepareStatement.executeQuery();
            while (res.next()) {
                orderHistories.add(new OrderHistory(
                    res.getInt("orderHistoryID"),
                    res.getInt("userID"),
                    res.getInt("orderID"),
                    res.getTimestamp("orderDate"),
                    res.getDouble("totalAmount"),
                    res.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistories;
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
