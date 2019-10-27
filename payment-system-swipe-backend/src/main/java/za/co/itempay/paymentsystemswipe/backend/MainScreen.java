/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.itempay.paymentsystemswipe.backend;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.sql.*;

import org.apache.log4j.*;
import org.apache.log4j.xml.DOMConfigurator;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.io.*;
import java.util.*;


@WebServlet(name = "MainScreen", urlPatterns = {"/MainScreen"})
public class MainScreen extends HttpServlet {


    static Logger logger = Logger.getLogger(MainScreen.class);
    public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static DateFormat filedateFormat = new SimpleDateFormat("yyyy-MM-dd HHmmss");
    public static Date date = new Date();


    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {

            Utils.Startup();


            if (request.getParameter("logoff") != null) {
                //session.invalidate();
                response.sendRedirect("index.jsp");
                return;
            }

            //String IP = request.getRemoteAddr();
            date = new Date();
            logger.debug(dateFormat.format(date));
            //logger.debug("IP: " + IP);

            //String IPRange = IP.substring(0, IP.lastIndexOf("."));
            //IPRange = IPRange + ".0/24";
            /*date = new Date();
            logger.debug(dateFormat.format(date));
            //logger.debug("IPRange: " + IPRange);*/

            ////String HostName = request.getRemoteHost();
            /*date = new Date();
            logger.debug(dateFormat.format(date));
            //logger.debug("HostName: " + HostName);*/
            //String MembershipNo = request.getParameter("MembershipNo");
            /*date = new Date();
            logger.debug(dateFormat.format(date));
            logger.debug("TerminalSerialNumber: " + TerminalSerialNumber);*/
            /*String surname = request.getParameter("Surname");
            String FirstName = request.getParameter("FirstName");
            String Password = request.getParameter("Password");*/

            date = new Date();
            logger.info(dateFormat.format(date));
            logger.info("MainScreen");

            String redirectURL = "/payment-system-swipe-web/secureUser/home.jsp";
            response.sendRedirect(redirectURL);
            
            
              
              /*String TermID = "";
            
              TermID = "";
            
            try
            {
                Statement stselect = con.createStatement();
                date = new Date();
                logger.debug(dateFormat.format(date));
                logger.debug("Query: select Beneficiary_no, User_name, PIN from T_Web_users ");
                //ResultSet rsselect = stselect.executeQuery("select POSSerialNo, MerchantNo from T_Map_Pos where BranchIPRange = '" + IPRange + "'");
                ResultSet rsselect = stselect.executeQuery("Query: select Beneficiary_no, User_name, PIN from T_Web_users where PIN = '" + Password + "'");
                while(rsselect.next())
                {
                    TermID = rsselect.getString(1);
                    String PIN = rsselect.getString(3);
                    date = new Date();
                    logger.debug(dateFormat.format(date));
                    logger.debug("TermID: " + TermID);
                    
                    out.println("<center><img src=\"ABLogo.jpg\" alt=\"Logo\"/></center>");
                    out.println("<hr><b>Welcome FuneralAssist24</b> ");
                    out.println("<center><button type=\"button\" onclick =\"document.location.href = '/FuneralAssist24/secureUser/home.jsp'\">Continue</button></center> <br /> ");
                    
                    String redirectURL = "/FuneralAssist24/secureUser/home.jsp";
                    response.sendRedirect(redirectURL);
                   
                    
              
                 
                }
                    
                                      
                   
            
            }
            catch(SQLException ex)
            {
              
             
                
                date = new Date();
                logger.fatal(dateFormat.format(date));
                logger.fatal("SQLException " + ex.getMessage());
                logger.fatal("With query: select Beneficiary_no, User_name, PIN from T_Web_users  where PIN = '" + Password + "'");
                String TGResponseCode = "05";
                String TGResponseMessage = "Database Error";
                
                
            }   
                
            
               if (TermID == "")
                       {        
            
            
                out.println("<hr><b> User Login Failed:</b> ");
                out.println("<center><button type=\"button\" onclick =\"document.location.href = '/FuneralAssist24/index.jsp'\">Back to Main</button></center> <br /> ");
                TermID = "";     
                       }*/

        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
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
