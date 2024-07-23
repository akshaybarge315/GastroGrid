package com.food.model;

import java.util.Date;

public class Order {
	private int orderId;
	private int userId;
	private int restaurantId;
	private Date orderDate;
	private Double totalAmount;
	private String status;
	private String address;
	private String paymentMode;
	
	public Order() {	}

	public Order(int orderId, int userId, int restaurantId, Date orderDate, Double totalAmount, String status, String address,
			String paymentMode) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.restaurantId = restaurantId;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.status = status;
		this.address = address;
		this.paymentMode = paymentMode;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getOrderId() {
		return orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Order [" + orderId + ", " + userId + ", " + restaurantId + ", "
				+ orderDate + ", " + totalAmount + ", " + status + ", " + paymentMode
				+ ", " + address + "]";
	}
	
}
