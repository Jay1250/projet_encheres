package org.trocencheres.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ServletTestSession", urlPatterns = "/ServletTestSession")
public class ServletTestSession extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int compt = 0;
        if(session.getAttribute("compt") != null) {
            compt = (int)session.getAttribute("compt");
        }
        compt += 1;
        session.setAttribute("compt", compt);

        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/displaySession.jsp");
        rd.forward(request,response);
    }
}
