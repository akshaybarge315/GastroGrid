package com.food.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.food.dao.MenuDAO;
import com.food.daoImpl.MenuDAOImpl;
import com.food.model.Menu;

@WebServlet("/menu")
public class ManuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ManuServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String restaurantID = req.getParameter("restaurantID");
		
		
		if (restaurantID != null && !restaurantID.isEmpty()) {
			MenuDAO menu = new MenuDAOImpl();
			
			int resID = Integer.parseInt(restaurantID);
			
			List<Menu> allMenu = menu.getAllMenuByRestaurant(resID);
			
			req.setAttribute("allMenus", allMenu);
			
			req.getRequestDispatcher("/menu.jsp").forward(req, resp);
			
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Restaurant ID is required");
        }
	}

}
