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
@WebServlet(name = "MemberCashPayment", urlPatterns = {"/MemberCashPayment"})
public class MemberCashPayment extends HttpServlet {

    static Logger logger = Logger.getLogger(MemberCashPayment.class);
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
            logger.info("MemberCashPayment accessed by: " + User);
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


            String SortBy = request.getParameter("SortBy");
            date = new Date();
            logger.debug(dateFormat.format(date));
            logger.debug("SortBy: " + SortBy);
            String AscDesc = request.getParameter("AscDesc");
            logger.debug("AscDesc: " + AscDesc);
            String Search = request.getParameter("Search");
            logger.debug("Search: " + Search);

            if (SortBy.equals("") || SortBy == null) {
                SortBy = "Identity_No";
            }
            if (AscDesc.equals("") || AscDesc == null) {
                AscDesc = "Asc";
            }
            String OrderBy = SortBy + " " + AscDesc;

            String Identity_No = "";

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("    <script type=\"text/javascript\">");
            out.println("        function GetSearch() {");
            out.println("                search = document.memberdetails.Search.value;");
            out.println("                window.location.href=\"/PaymentSystemSwipe/secureAdmin/MemberCashPayment.jsp?search=\"+search;");
            out.println("            }");
            out.println("    </script>");
            out.println("    <link rel=\"stylesheet\" href=\"/PaymentSystemSwipe/" + Beneficiary_Name + "/css/style.css\" type=\"text/css\"/>");
            out.println("    <head>");
            out.println("        <link rel=\"icon\" type=\"/PaymentSystemSwipe/" + Beneficiary_Name + "/img/Icon.ico\"></link>");
            out.println("        <link rel=\"shortcut icon\" href=\"/PaymentSystemSwipe/" + Beneficiary_Name + "/img/Icon.ico\"></link>");
            out.println("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
            out.println("        <title>Members Details</title>");
            out.println("    </head>");
            out.println("    <body>");
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
            out.println("			<li><a href=\"/PaymentSystemSwipe/secureAdmin/Members.jsp\">Current Members</a></li>");
            out.println("			<li><a href=\"/PaymentSystemSwipe/secureUser/Payments.jsp\">Payment Report</a></li>");
            out.println("			<li class=\"first\"><a href=\"/PaymentSystemSwipe/secureAdmin/MemberCashPayment.jsp\" class=\"active\">Process a Payment</a></li>");
            out.println("			<li><a href=\"/PaymentSystemSwipe/logout.jsp?logoff=true\">Logoff " + request.getRemoteUser() + "</a></li>");
            out.println("		</ul>");
            out.println("		<!-- Menu end -->");
            out.println("<hr class=\"noscreen\" />");
            out.println("	<div id=\"content-box\">");
            out.println("		<div id=\"content-box-in\">");
            out.println("			<a name=\"skip-menu\"></a>");
            out.println("			<!-- Content -->");
            out.println("			<div class=\"content-box\">");
            out.println("				<div class=\"content-box-in\">");
            out.println("					<h3>Select Member</h3>");


            out.println("        <form name=\"memberdetails\" method=\"post\" action=\"MemberEdit\">");
            out.println("            <center>Search: <input type=\"text\" name=\"Search\"/><button type=\"button\" onclick =\"GetSearch()\">Search</button></center><br />");
            out.println("            <center><button type=\"button\" onclick =\"document.location.href = '/PaymentSystemSwipe/secureUser/home.jsp'\">Back to Main</button></center> <br />");
            out.println("<center><button type=\"button\" value=\"Back\" onclick=\"history.back()\">Back</button></center>");
            out.println("<center><table border=\"1\">");
            out.println("<tr>");
            if (SortBy.equals("FirstName") && AscDesc.equals("Asc")) {
                out.println("<th><a href=\"/PaymentSystemSwipe/secureAdmin/MemberCashPayment.jsp?sort=FirstName&order=Desc&search=" + Search + "\">Name</a></th>");
            } else {
                out.println("<th><a href=\"/PaymentSystemSwipe/secureAdmin/MemberCashPayment.jsp?sort=FirstName&order=Asc&search=" + Search + "\">Name</a></th>");
            }
            if (SortBy.equals("Identity_No") && AscDesc.equals("Asc")) {
                out.println("<th><a href=\"/PaymentSystemSwipe/secureAdmin/MemberCashPayment.jsp?sort=Identity_No&order=Desc&search=" + Search + "\">Identity Number</a></th>");
            } else {
                out.println("<th><a href=\"/PaymentSystemSwipe/secureAdmin/MemberCashPayment.jsp?sort=Identity_No&order=Asc&search=" + Search + "\">Identity Number</a></th>");
            }
            if (SortBy.equals("Member_No") && AscDesc.equals("Asc")) {
                out.println("<th><a href=\"/PaymentSystemSwipe/secureAdmin/MemberCashPayment.jsp?sort=Member_No&order=Desc&search=" + Search + "\">Membership Number</a></th>");
            } else {
                out.println("<th><a href=\"/PaymentSystemSwipe/secureAdmin/MemberCashPayment.jsp?sort=Member_No&order=Asc&search=" + Search + "\">Membership Number</a></th>");
            }
            if (SortBy.equals("B.PAN") && AscDesc.equals("Asc")) {
                out.println("<th><a href=\"/PaymentSystemSwipe/secureAdmin/MemberCashPayment.jsp?sort=B.PAN&order=Desc&search=" + Search + "\">Card Number</a></th>");
            } else {
                out.println("<th><a href=\"/PaymentSystemSwipe/secureAdmin/MemberCashPayment.jsp?sort=B.PAN&order=Asc&search=" + Search + "\">Card Number</a></th>");
            }
            out.println("</tr>");


            try {
                Statement stselect = Utils.TGDBcon.createStatement();
                date = new Date();
                logger.debug(dateFormat.format(date));
                //logger.debug("Query:select FirstName + ' ' + SurName, Identity_No, Member_No from T_BP_Customers A, T_BP_Card B, T_BP_Card_Beneficiaries C where A.BP_Cust_ID = B.BP_Cust_ID and B.PAN = C.PAN and C.Beneficiary_No = '" + Beneficiary_No + "' order by " + OrderBy);
                logger.debug("Query:select FirstName + ' ' + SurName, Identity_No, Member_No, B.PAN from T_BP_Customers A, T_BP_Card B, T_BP_Card_Beneficiaries C where A.BP_Cust_ID = B.BP_Cust_ID and B.PAN = C.PAN and C.Beneficiary_No = '" + Beneficiary_No + "' and ((FirstName like '%' + '" + Search + "' + '%') or (SurName like '%' + '" + Search + "' + '%') or (Identity_No like '%' + '" + Search + "' + '%') or (Member_No like '%' + '" + Search + "' + '%')) order by " + OrderBy);
                ResultSet rsselect = stselect.executeQuery("select FirstName + ' ' + SurName, Identity_No, Member_No, B.PAN from T_BP_Customers A, T_BP_Card B, T_BP_Card_Beneficiaries C where A.BP_Cust_ID = B.BP_Cust_ID and B.PAN = C.PAN and C.Beneficiary_No = '" + Beneficiary_No + "' and ((FirstName like '%' + '" + Search + "' + '%') or (SurName like '%' + '" + Search + "' + '%') or (Identity_No like '%' + '" + Search + "' + '%') or (Member_No like '%' + '" + Search + "' + '%')) order by " + OrderBy);

                while (rsselect.next()) {
                    String Name = rsselect.getString(1);
                    String ID = rsselect.getString(2);
                    String MemNo = rsselect.getString(3);
                    String PAN = rsselect.getString(4);

                    out.println("<tr>");

                    String FormattedID = ID.replaceAll(" ", "+");

                    out.println("<td><a href=\"/PaymentSystemSwipe/secureAdmin/ProcessCashPayment.jsp?id=" + FormattedID + "\">" + Name + "</a></td>");
                    out.println("<td><a href=\"/PaymentSystemSwipe/secureAdmin/ProcessCashPayment.jsp?id=" + FormattedID + "\">" + ID + "</a></td>");
                    out.println("<td><a href=\"/PaymentSystemSwipe/secureAdmin/ProcessCashPayment.jsp?id=" + FormattedID + "\">" + MemNo + "</a></td>");
                    out.println("<td><a href=\"/PaymentSystemSwipe/secureAdmin/ProcessCashPayment.jsp?id=" + FormattedID + "\">" + PAN + "</a></td>");
                    out.println("</tr>");
                }

                out.println("</table></center>");
            } catch (SQLException ex) {
                date = new Date();
                logger.fatal(dateFormat.format(date));
                logger.fatal("SQLException " + ex.getMessage());
                logger.fatal("Query:select FirstName + ' ' + SurName, Identity_No, Member_No, B.PAN from T_BP_Customers A, T_BP_Card B, T_BP_Card_Beneficiaries C where A.BP_Cust_ID = B.BP_Cust_ID and B.PAN = C.PAN and C.Beneficiary_No = '" + Beneficiary_No + "' and ((FirstName like '%' + '" + Search + "' + '%') or (SurName like '%' + '" + Search + "' + '%') or (Identity_No like '%' + '" + Search + "' + '%') or (Member_No like '%' + '" + Search + "' + '%')) order by " + OrderBy);
            }
            out.println("            <center><button type=\"button\" onclick =\"document.location.href = '/PaymentSystemSwipe/secureUser/home.jsp'\">Back to Main</button></center> <br />");
            out.println("<center><button type=\"button\" value=\"Back\" onclick=\"history.back()\">Back</button></center>");
            out.println("            <center><input style=\"visibility:hidden\" type=\"text\" name=\"IdentityNumber\" /><br /></center>");
            out.println("        </form>");


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


            out.println("    </body>");
            out.println("</html>");
            //}


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
