package com.food.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.food.dao.MenuDAO;
import com.food.daoImpl.MenuDAOImpl;
import com.food.model.Cart;
import com.food.model.CartItem;
import com.food.model.Menu;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CartServlet() {
        super();
    }

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		
		if(cart == null) {
			cart = new Cart();
//			session.setAttribute("cart", cart);
		}
		
		String action = req.getParameter("action");
		if(action.equals("add")) {
			addItemToCart(req, cart);
		}else if(action.equals("update")) {
			updateItemCart(req, cart);
		}else if(action.equals("remove")) {
			removeItemFromCart(req, cart);
		}
//		System.out.println(cart);
		
		session.setAttribute("cart", cart);
		resp.sendRedirect("cart.jsp");
	}
	
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.getRequestDispatcher("cart.jsp").forward(req, resp);
//	}
	
	private void addItemToCart(HttpServletRequest req,Cart cart){
		int itemId = Integer.parseInt(req.getParameter("itemId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		
		MenuDAO menuDAO = new MenuDAOImpl();
		Menu menuItem = menuDAO.getMenu(itemId);
		
		HttpSession session = req.getSession();
		session.setAttribute("restaurantId", menuItem.getRestaurantId());
		
		if(menuItem != null) {
			CartItem item = new CartItem(
					menuItem.getMenuId(),
					menuItem.getRestaurantId(),
					menuItem.getItemName(),
					quantity,
					menuItem.getPrice(),
					menuItem.getImagePath()
					);
			cart.addItem(item);
		}
	}
	
	private void updateItemCart(HttpServletRequest req,Cart cart){
		int itemId = Integer.parseInt(req.getParameter("itemId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		cart.updateItem(itemId, quantity);
	}
	
	private void removeItemFromCart(HttpServletRequest req,Cart cart){
		int itemId = Integer.parseInt(req.getParameter("itemId"));
		cart.removeItem(itemId);
	}
	
}
