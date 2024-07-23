package com.food.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.food.dao.OrderDAO;
import com.food.daoImpl.OrderDAOImpl;
import com.food.model.Cart;
import com.food.model.CartItem;
import com.food.model.Order;
import com.food.model.User;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private OrderDAO orderDAO;
    
    @Override
    public void init() throws ServletException {
    	orderDAO = new OrderDAOImpl();
    }

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		Cart cart = (Cart) session.getAttribute("cart");
		User user = (User) session.getAttribute("user");
		
		if(cart == null) {
//			System.out.println("Cart is null");
			resp.sendRedirect(req.getContextPath() +"/restaurant");
		}else if(cart.getItems().isEmpty()){
//			System.out.println("Cart item is null");
			resp.sendRedirect(req.getContextPath() +"/restaurant");
		}else if(user == null) {
//			System.out.println("User is null");
			resp.sendRedirect("login.jsp");
		}else {
				String paymentMode = req.getParameter("paymentMode");
				String address = req.getParameter("address");
				
				final AtomicLong counter = new AtomicLong(System.currentTimeMillis()); // to Generate the unique order id
				
				Order order = new Order();
				order.setOrderId(counter.hashCode());
				order.setUserId(user.getUserId());
				order.setRestaurantId((int) session.getAttribute("restaurantId"));
				order.setOrderDate(new Date());
				order.setPaymentMode(paymentMode);
				order.setStatus("Pending");
				order.setAddress(address);
				
				double totalAmount = 0;
				for(CartItem item : cart.getItems().values()) {
					totalAmount += item.getPrice() * item.getQuantity();
				}
				
				order.setTotalAmount(totalAmount);
				
				orderDAO.addOrder(order);
				
//				session.removeAttribute("cart");
				session.setAttribute("order", order);
				
				resp.sendRedirect("orderConfirmation.jsp");
				
		}
	}

}
