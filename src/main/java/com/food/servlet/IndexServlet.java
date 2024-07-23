package com.food.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.food.dao.MenuDAO;
import com.food.dao.RestaurantDAO;
import com.food.daoImpl.MenuDAOImpl;
import com.food.daoImpl.RestaurantDAOImpl;
import com.food.model.Menu;
import com.food.model.Restaurant;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public IndexServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RestaurantDAO restaurantDAOImpl = new RestaurantDAOImpl();
		MenuDAO menu = new MenuDAOImpl();
		
		List<Restaurant> allRestaurant = restaurantDAOImpl.getAllRestaurant();
		List<Menu> allMenu = menu.getAllMenuByRestaurant(103);

		req.setAttribute("restaurants", allRestaurant);
		req.setAttribute("allMenus", allMenu);
		
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
		
		
		
	}

}
