/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.itempay.paymentsystemswipe.backend;

//import java.io.IOException;
//import java.io.PrintWriter;

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

/**
 * @author Administrator
 */
@WebServlet(name = "DependantEditSave", urlPatterns = {"/DependantEditSave"})
public class DependantEditSave extends HttpServlet {


    static Logger logger = Logger.getLogger(DependantEditSave.class);
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

            String User = request.getRemoteUser();
            date = new Date();
            logger.info(dateFormat.format(date));
            logger.info("DependantEditSave accessed by: " + User);
            String Beneficiary_No = "";
            String Beneficiary_Name = "";
            String Beneficiary_WebSite = "";
            String Beneficiary_Slogan = "";

            try {
                Statement stselect = Utils.TGDBcon.createStatement();
                date = new Date();
                logger.debug(dateFormat.format(date));
                logger.debug("Query: select Beneficiary_No from Users where User_Name = '" + User + "'");
                ResultSet rsselect = stselect.executeQuery("select Beneficiary_No from Users where User_Name = '" + User + "'");
                while (rsselect.next()) {
                    Beneficiary_No = rsselect.getString(1);
                    date = new Date();
                    logger.debug(dateFormat.format(date));
                    logger.debug("Beneficiary_No: " + Beneficiary_No);
                }
            } catch (SQLException ex) {
                date = new Date();
                logger.fatal(dateFormat.format(date));
                logger.fatal("SQLException " + ex.getMessage());
                logger.fatal("With query: select Beneficiary_No from Users where User_Name = '" + User + "'");
            }

            try {
                Statement stselect = Utils.TGDBcon.createStatement();
                date = new Date();
                logger.debug(dateFormat.format(date));
                logger.debug("Query: select Beneficiary_Name, Beneficiary_WebSite, Beneficiary_Slogan from Users A, T_BP_Beneficiaries B where A.Beneficiary_No = B.Beneficiary_No and User_Name = '" + User + "'");
                ResultSet rsselect = stselect.executeQuery("select Beneficiary_Name, Beneficiary_WebSite, Beneficiary_Slogan from Users A, T_BP_Beneficiaries B where A.Beneficiary_No = B.Beneficiary_No and User_Name = '" + User + "'");
                while (rsselect.next()) {
                    Beneficiary_Name = rsselect.getString(1);
                    date = new Date();
                    logger.debug(dateFormat.format(date));
                    logger.debug("Beneficiary_Name: " + Beneficiary_Name);
                    Beneficiary_WebSite = rsselect.getString(2);
                    logger.debug("Beneficiary_WebSite: " + Beneficiary_WebSite);
                    Beneficiary_Slogan = rsselect.getString(3);
                    logger.debug("Beneficiary_Slogan: " + Beneficiary_Slogan);
                }
            } catch (SQLException ex) {
                date = new Date();
                logger.fatal(dateFormat.format(date));
                logger.fatal("SQLException " + ex.getMessage());
                logger.fatal("With query: select Beneficiary_Name, Beneficiary_WebSite, Beneficiary_Slogan from Users A, T_BP_Beneficiaries B where A.Beneficiary_No = B.Beneficiary_No and User_Name = '" + User + "'");
            }


            //String IP = request.getRemoteAddr();
            date = new Date();
            logger.debug(dateFormat.format(date));
            //logger.debug("IP: " + IP);

            //String IPRange = IP.substring(0, IP.lastIndexOf("."));
            //IPRange = IPRange + ".0/24";
            date = new Date();
            logger.debug(dateFormat.format(date));
            //logger.debug("IPRange: " + IPRange);

            //String HostName = request.getRemoteHost();
            date = new Date();
            logger.debug(dateFormat.format(date));
            //logger.debug("HostName: " + HostName);
            String FirstName = request.getParameter("FirstName");
            date = new Date();
            logger.debug(dateFormat.format(date));
            logger.debug("FirstName: " + FirstName);
            String SurName = request.getParameter("SurName");
            logger.debug("SurName: " + SurName);
            String Initials = request.getParameter("Initials");
            logger.debug("Initials: " + Initials);
            String Title = request.getParameter("Title");
            logger.debug("Title: " + Title);
            String Sex = request.getParameter("Sex");
            logger.debug("Sex: " + Sex);
            String Marital_Status = request.getParameter("Marital_Status");
            logger.debug("Marital_Status: " + Marital_Status);
            String Identity_No = request.getParameter("Identity_No");
            logger.debug("Identity_No: " + Identity_No);
            String Physical_Address_Line1 = request.getParameter("Physical_Address_Line1");
            logger.debug("Physical_Address_Line1: " + Physical_Address_Line1);
            String Physical_Address_Line2 = request.getParameter("Physical_Address_Line2");
            logger.debug("Physical_Address_Line2: " + Physical_Address_Line2);
            String Physical_Address_Town = request.getParameter("Physical_Address_Town");
            logger.debug("Physical_Address_Town: " + Physical_Address_Town);
            String Physical_Address_Region = request.getParameter("Physical_Address_Region");
            logger.debug("Physical_Address_Region: " + Physical_Address_Region);
            String Physical_Address_Country = request.getParameter("Physical_Address_Country");
            logger.debug("Physical_Address_Country: " + Physical_Address_Country);
            String Home_Phone_No = request.getParameter("Home_Phone_No");
            logger.debug("Home_Phone_No: " + Home_Phone_No);
            String Mobile = request.getParameter("Mobile");
            logger.debug("Mobile: " + Mobile);
            String EMail = request.getParameter("EMail");
            logger.debug("EMail: " + EMail);
            String Member_No = request.getParameter("Member_No");
            logger.debug("Member_No: " + Member_No);
            String BP_Cust_ID = request.getParameter("BP_Cust_ID");
            logger.debug("BP_Cust_ID: " + BP_Cust_ID);
            String BP_Cust_Dep_ID = request.getParameter("BP_Cust_Dep_ID");
            logger.debug("BP_Cust_Dep_ID: " + BP_Cust_Dep_ID);

            try {
                Statement stinsert = Utils.TGDBcon.createStatement();
                date = new Date();
                logger.debug(dateFormat.format(date));
                logger.debug("Query: update T_BP_Customer_Dependants set FirstName = '" + FirstName + "', SurName = '" + SurName + "', Initials = '" + Initials + "', Title = '" + Title + "', Sex = '" + Sex + "', Marital_Status = '" + Marital_Status + "', Identity_No = '" + Identity_No + "', Physical_Address_Line1 = '" + Physical_Address_Line1 + "', Physical_Address_Line2 = '" + Physical_Address_Line2 + "', Physical_Address_Town = '" + Physical_Address_Town + "', Physical_Address_Region = '" + Physical_Address_Region + "', Physical_Address_Country = '" + Physical_Address_Country + "', Home_Phone_No = '" + Home_Phone_No + "', Mobile = '" + Mobile + "', EMail = '" + EMail + "', Member_No = '" + Member_No + "' where BP_Cust_Dep_ID = '" + BP_Cust_Dep_ID + "'");
                stinsert.executeUpdate("update T_BP_Customer_Dependants set FirstName = '" + FirstName + "', SurName = '" + SurName + "', Initials = '" + Initials + "', Title = '" + Title + "', Sex = '" + Sex + "', Marital_Status = '" + Marital_Status + "', Identity_No = '" + Identity_No + "', Physical_Address_Line1 = '" + Physical_Address_Line1 + "', Physical_Address_Line2 = '" + Physical_Address_Line2 + "', Physical_Address_Town = '" + Physical_Address_Town + "', Physical_Address_Region = '" + Physical_Address_Region + "', Physical_Address_Country = '" + Physical_Address_Country + "', Home_Phone_No = '" + Home_Phone_No + "', Mobile = '" + Mobile + "', EMail = '" + EMail + "', Member_No = '" + Member_No + "' where BP_Cust_Dep_ID = '" + BP_Cust_Dep_ID + "'");
            } catch (SQLException ex) {
                date = new Date();
                logger.fatal(dateFormat.format(date));
                logger.fatal("SQLException " + ex.getMessage());
                logger.fatal("With query: update T_BP_Customer_Dependants set FirstName = '" + FirstName + "', SurName = '" + SurName + "', Initials = '" + Initials + "', Title = '" + Title + "', Sex = '" + Sex + "', Marital_Status = '" + Marital_Status + "', Identity_No = '" + Identity_No + "', Physical_Address_Line1 = '" + Physical_Address_Line1 + "', Physical_Address_Line2 = '" + Physical_Address_Line2 + "', Physical_Address_Town = '" + Physical_Address_Town + "', Physical_Address_Region = '" + Physical_Address_Region + "', Physical_Address_Country = '" + Physical_Address_Country + "', Home_Phone_No = '" + Home_Phone_No + "', Mobile = '" + Mobile + "', EMail = '" + EMail + "', Member_No = '" + Member_No + "' where BP_Cust_Dep_ID = '" + BP_Cust_Dep_ID + "'");
            }


            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<link rel=\"stylesheet\" href=\"/PaymentSystemSwipe/" + Beneficiary_Name + "/css/style.css\" type=\"text/css\"/>");
            out.println("<head>");
            out.println("<link rel=\"icon\" type=\"/PaymentSystemSwipe/" + Beneficiary_Name + "/img/Icon.ico\"></link>");
            out.println("<link rel=\"shortcut icon\" href=\"/PaymentSystemSwipe/" + Beneficiary_Name + "/img/Icon.ico\"></link>");
            out.println("<title>Dependant Updated</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("<div id=\"wrapper\">");
            out.println("		<!-- Header -->");
            out.println("		<div id=\"header\">");
            out.println("			<!-- Your website name  -->");
            out.println("			<h1><a href=\"" + Beneficiary_WebSite + "\">" + Beneficiary_Name + "</a></h1>");
            out.println("			<!-- Your website name end -->");
            out.println("			<!-- Your slogan -->");
            out.println("			<h2>" + Beneficiary_Slogan + "</h2>");
            out.println("			<!-- Your slogan end -->");
            out.println("			<div id=\"header-arrow\"></div>");
            out.println("		</div>");
            out.println("		<!-- Header end -->");
            out.println("		<!-- Menu -->");
            out.println("		<ul id=\"menu\" class=\"cleaning-box\">");
            out.println("			<li><a href=\"/PaymentSystemSwipe/secureUser/home.jsp\">Home</a></li>");
            out.println("			<li><a href=\"/PaymentSystemSwipe/secureAdmin/MemberDetailsID.jsp\">Add new member</a></li>");
            out.println("			<li class=\"first\"><a href=\"/PaymentSystemSwipe/secureAdmin/Members.jsp\" class=\"active\">Current Members</a></li>");
            out.println("			<li><a href=\"/PaymentSystemSwipe/secureUser/Payments.jsp\">Payment Report</a></li>");
            out.println("			<li><a href=\"/PaymentSystemSwipe/secureAdmin/MemberCashPayment.jsp\">Process a Payment</a></li>");
            out.println("			<li><a href=\"/PaymentSystemSwipe/logout.jsp?logoff=true\">Logoff " + User + "</a></li>");
            out.println("		</ul>");
            out.println("		<!-- Menu end -->");
            out.println("<hr class=\"noscreen\" />");
            out.println("	<div id=\"content-box\">");
            out.println("		<div id=\"content-box-in\">");
            out.println("			<a name=\"skip-menu\"></a>");
            out.println("			<!-- Content -->");
            out.println("			<div class=\"content-box\">");
            out.println("				<div class=\"content-box-in\">");
            out.println("					<h3>Dependant details updated</h3>");
            out.println("<center><button type=\"button\" onclick =\"document.location.href = '/PaymentSystemSwipe/secureUser/home.jsp'\">Back to Main</button></center> <br />");
            out.println("<center><button type=\"button\" value=\"Back\" onclick=\"history.back()\">Back</button></center>");
            out.println("					</div>");
            out.println("				</div>");
            out.println("			</div>");
            out.println("			<!-- Content end -->");
            out.println("		</div>");
            out.println("<hr class=\"noscreen\" />");
            out.println("	<!-- Footer -->");
            out.println("	<div id=\"footer\">");
            out.println("		<p class=\"left\">Powered by <a class=\"b\" href=\"http://www.touchpay.co.za\">TouchPay</a><img src=\"/PaymentSystemSwipe/" + Beneficiary_Name + "/img/TPLogoSmall.png\"></p>");
            out.println("	</div>");
            out.println("	<!-- Footer end -->");
            out.println("</div>    ");
            out.println("</body>");
            out.println("</html>");


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
