package com.food.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.food.daoImpl.UserDAOImpl;
import com.food.model.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAOImpl userDAO;
       
    public LoginServlet() {
        super();
    }
    
    public void init() {
        userDAO = new UserDAOImpl();
    }

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = userDAO.getUserByUsername(username);
        
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
        	HttpSession session = req.getSession();
        	session.setAttribute("user", user);
    		
            resp.sendRedirect(req.getContextPath() +"/index");
        } else {
            resp.sendRedirect("login.jsp?error=true");
        }
	}

}
