package org.trocencheres.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.trocencheres.beans.Vente;

/**
 * Servlet implementation class ServletCreerCompte
 */
@WebServlet("/ServletCreerCompte")
public class ServletCreerCompte extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCreerCompte() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//int credit;
		String pseudo = request.getParameter("pseudo");
		String nom =request.getParameter("nom");
		String prenom=request.getParameter("prenom"); 
		String email=request.getParameter("email");
		String telephone=request.getParameter("telephone"); 
		String rue=request.getParameter("rue");
		String codePostal=request.getParameter("codepostal"); 
		String ville=request.getParameter("ville");
		String motDePasse=request.getParameter("motdepasse");
		boolean administrateur=false;
		
	}

}
