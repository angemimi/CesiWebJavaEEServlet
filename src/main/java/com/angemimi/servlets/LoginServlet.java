package com.angemimi.servlets;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.angemimi.dao.SessionDAO;
import com.angemimi.dao.UserDAO;
import com.angemimi.model.Session;
import com.angemimi.model.User;
import com.angemimi.storage.StorageSession;
import com.angemimi.storage.StorageUsers;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Map<String, String> users;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		
		User user = UserDAO.getInstance().getUser(username);
		
		if(!user.isValid(username, pwd)){
			request.setAttribute("error", "Invalid username and/or password");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else {
			// Validité des identifiant
			// Creations des cookie
			Cookie cookieAuth = new Cookie("auth",UUID.randomUUID().toString());
			
			Session sess = new Session(cookieAuth.getName(), cookieAuth.getValue());
			SessionDAO.getInstance().create(sess); //Creation de la session dans la base de donnée
			
			response.addCookie(cookieAuth);
			System.out.println("Cookie added");
			response.sendRedirect("protected");
		}
	}

}
