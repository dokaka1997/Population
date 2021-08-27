/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.CountryEntity;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.WorldPopulationService;

/**
 *
 * @author Dao Van Do
 */
public class GetAllCountries extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        WorldPopulationService populationService = new WorldPopulationService();

        String name = "";
        int pageIndex = request.getParameter("index") == null ? 0
                : Integer.parseInt(request.getParameter("index"));
        int startIndex = pageIndex * 10;
        int endIndex = startIndex + 10;
        List<CountryEntity> ces = new ArrayList<>();
        if (request.getParameter("search") != null && !request.getParameter("search").equals("") ) {
            name = request.getParameter("search");
            ces.add(populationService.getPopulationByCountryName(name));
        } else {
            ces = populationService.getAllPopulationByCountry(startIndex, endIndex);
        }
        request.setAttribute("ces", ces);
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("maxPage", 10);
        request.getRequestDispatcher("ListContries.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
