package com.food.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.food.dao.RestaurantDAO;
import com.food.daoImpl.RestaurantDAOImpl;
import com.food.model.Restaurant;

@WebServlet("/restaurant")
public class RestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RestaurantServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RestaurantDAO restaurantDAOImpl = new RestaurantDAOImpl();
		
		List<Restaurant> allRestaurant = restaurantDAOImpl.getAllRestaurant();
		
		req.setAttribute("restaurants", allRestaurant);
		
		req.getRequestDispatcher("/restaurant.jsp").forward(req, resp);
	}

}
