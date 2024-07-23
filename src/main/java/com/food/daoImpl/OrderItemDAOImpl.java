package com.food.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.food.dao.OrderItemDAO;
import com.food.model.OrderItem;
import com.food.util.ConnectionUtil;

public class OrderItemDAOImpl implements OrderItemDAO {
	
	private static Connection connection = null;
    private static PreparedStatement prepareStatement = null;
    private static Statement statement = null;
    private static ResultSet res = null;

    private static final String INSERT_QUERY = "INSERT INTO orderItem (orderId, menuId, quantity, totalPrice) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE orderItem SET orderId = ?, menuId = ?, quantity = ?, totalPrice = ? WHERE orderItemId = ?";
    private static final String DELETE_QUERY = "DELETE FROM orderItem WHERE orderItemId = ?";
    private static final String SELECT_QUERY = "SELECT * FROM orderItem WHERE orderItemId = ?";
    private static final String SELECT_ALL_BY_ORDER_QUERY = "SELECT * FROM orderItem WHERE orderId = ?";

    public OrderItemDAOImpl() {
        connection = ConnectionUtil.getConnection();
    }

	@Override
	public void addOrderItem(OrderItem orderItem) {
		try {
            prepareStatement = connection.prepareStatement(INSERT_QUERY);
            prepareStatement.setInt(1, orderItem.getOrderId());
            prepareStatement.setInt(2, orderItem.getMenuId());
            prepareStatement.setInt(3, orderItem.getQuantity());
            prepareStatement.setDouble(4, orderItem.getItemTotal());
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public OrderItem getOrderItem(int orderItemId) {
		try {
            prepareStatement = connection.prepareStatement(SELECT_QUERY);
            prepareStatement.setInt(1, orderItemId);
            res = prepareStatement.executeQuery();
            
            if (res.next()) {
                return new OrderItem(
                    res.getInt("orderItemId"),
                    res.getInt("orderId"),
                    res.getInt("menuId"),
                    res.getInt("quantity"),
                    res.getDouble("itemTotal")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}

	@Override
	public void updateOrderItem(OrderItem orderItem) {
		try {
            prepareStatement = connection.prepareStatement(UPDATE_QUERY);
            prepareStatement.setInt(1, orderItem.getOrderId());
            prepareStatement.setInt(2, orderItem.getMenuId());
            prepareStatement.setInt(3, orderItem.getQuantity());
            prepareStatement.setDouble(4, orderItem.getItemTotal());
            prepareStatement.setInt(5, orderItem.getOrderItemId());
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void deleteOrderItem(int orderItemId) {
		try {
            prepareStatement = connection.prepareStatement(DELETE_QUERY);
            prepareStatement.setInt(1, orderItemId);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public List<OrderItem> getOrderItemsByOrder(int orderId) {
		List<OrderItem> orderItems = new ArrayList<>();
        
        try {
            prepareStatement = connection.prepareStatement(SELECT_ALL_BY_ORDER_QUERY);
            prepareStatement.setInt(1, orderId);
            res = prepareStatement.executeQuery();
            while (res.next()) {
                orderItems.add(new OrderItem(
                    res.getInt("orderItemId"),
                    res.getInt("orderId"),
                    res.getInt("menuId"),
                    res.getInt("quantity"),
                    res.getDouble("itemTotal")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItems;
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
