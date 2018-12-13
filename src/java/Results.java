/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dell
 */
@WebServlet(urlPatterns = {"/Results"})
public class Results extends HttpServlet {
    static Connection con;
    static Statement st;
    static ResultSet res;
    static PreparedStatement pst;
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
        String usn1 = null;
        String name = null;
        String usn=null;
        String lusn=null;
        int sem = 0,m1 = 0,m2 = 0,m3 = 0;
        try (PrintWriter out = response.getWriter()) {
            try{
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/results","root","");
           usn=request.getParameter("usn");
           lusn=usn.toLowerCase();
          String query="select * from results where usn=?";
          pst=con.prepareStatement(query);
          pst.setString(1, lusn);
          res=pst.executeQuery();
          while(res.next()){
           usn1=res.getString("usn");
           name=res.getString("name");
           sem=res.getInt("sem");
           m1=res.getInt("sub1");
           m2=res.getInt("sub2");
           m3=res.getInt("sub3");
          }
            
            }
            catch (ClassNotFoundException | SQLException ex)
       {
            System.out.println("Error: "+ex);
        }
            if(lusn.equals(usn1)){
          out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Results</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1><u><center>Results</center></u> </h1>");
            //out.println("<h1> Results are " + request.getContextPath() + "</h1>");
            out.println("<div>");
           
           
            out.println("<p><b> <center>USN:"+usn1.toUpperCase()+"</center></b></p>");
            out.println("<p><b> <center>Name:"+name.toUpperCase()+"</center></b></p>");
            out.println("<p><b> <center>Semester:"+sem+"</center></b></p>");
             out.println("<p><b> <center>Marks1:"+m1+"</center></b></p>");
             out.println("<p><b> <center>Marks2:"+m2+"</center></b></p>");
             out.println("<p><b> <center>Marks3:"+m3+"</center></b></p>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>"); 
            }
            else
            {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Results</title>");            
            out.println("</head>");
            out.println("<h1><center>Please enter correct USN </center> </h1>");
            out.println("<body>");
            out.println("</body>");
            out.println("</html>"); 
            }
        }
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
    //@Override
    /*protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }*/

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
   //@Override
    /*public String getServletInfo() {
        return "Short description";
    }// </editor-fold>*/

}
