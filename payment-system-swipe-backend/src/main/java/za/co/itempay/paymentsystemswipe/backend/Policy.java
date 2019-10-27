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
@WebServlet(name = "Policy", urlPatterns = {"/Policy"})
public class Policy extends HttpServlet {


    static Logger logger = Logger.getLogger(Policy.class);
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
            logger.info("Policy accessed by: " + User);
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
            String TestAmount = request.getParameter("TestAmount");
            date = new Date();
            logger.debug(dateFormat.format(date));
            logger.debug("TestAmount: " + TestAmount);
            String TestFee = request.getParameter("TestFee");
            logger.debug("TestFee: " + TestFee);
            String SelectedType = request.getParameter("SelectedType");
            logger.debug("SelectedType: " + SelectedType);
            String SelectedPayment = request.getParameter("SelectedPayment");
            logger.debug("SelectedPayment: " + SelectedPayment);
            String BP_Cust_ID = request.getParameter("BP_Cust_ID");
            logger.debug("BP_Cust_ID: " + BP_Cust_ID);
            String PAN = request.getParameter("PAN");
            logger.debug("PAN: " + PAN);

            try {
                Statement stselect = Utils.TGDBcon.createStatement();
                date = new Date();
                logger.debug(dateFormat.format(date));
                logger.debug("Query: select convert(varchar, getdate(), 23) as \"Date\", '19' + substring(Identity_No, 1, 2) + '-' + substring(Identity_No, 3, 2) + '-' + substring(Identity_No, 5, 2) as \"DOB if 1900\", datediff(yy, '19' + substring(Identity_No, 1, 2) + '-' + substring(Identity_No, 3, 2) + '-' + substring(Identity_No, 5, 2), getdate()) as \"Age if 1900\", '20' + substring(Identity_No, 1, 2) + '-' + substring(Identity_No, 3, 2) + '-' + substring(Identity_No, 5, 2) as \"DOB if 2000\", datediff(yy, '20' + substring(Identity_No, 1, 2) + '-' + substring(Identity_No, 3, 2) + '-' + substring(Identity_No, 5, 2), getdate()) as \"Age if 2000\" from T_BP_Customers where BP_Cust_ID = " + BP_Cust_ID);
                ResultSet rsselect = stselect.executeQuery("select convert(varchar, getdate(), 23) as \"Date\", '19' + substring(Identity_No, 1, 2) + '-' + substring(Identity_No, 3, 2) + '-' + substring(Identity_No, 5, 2) as \"DOB if 1900\", datediff(yy, '19' + substring(Identity_No, 1, 2) + '-' + substring(Identity_No, 3, 2) + '-' + substring(Identity_No, 5, 2), getdate()) as \"Age if 1900\", '20' + substring(Identity_No, 1, 2) + '-' + substring(Identity_No, 3, 2) + '-' + substring(Identity_No, 5, 2) as \"DOB if 2000\", datediff(yy, '20' + substring(Identity_No, 1, 2) + '-' + substring(Identity_No, 3, 2) + '-' + substring(Identity_No, 5, 2), getdate()) as \"Age if 2000\" from T_BP_Customers where BP_Cust_ID = " + BP_Cust_ID);
                while (rsselect.next()) {
                    String DateToday = rsselect.getString(1);
                    date = new Date();
                    logger.debug(dateFormat.format(date));
                    logger.debug("DateToday: " + DateToday);
                    String DOBif19 = rsselect.getString(2);
                    logger.debug("DOBif19: " + DOBif19);
                    String Ageif19 = rsselect.getString(3);
                    logger.debug("Ageif19: " + Ageif19);
                    String DOBif20 = rsselect.getString(4);
                    logger.debug("DOBif20: " + DOBif20);
                    String Ageif20 = rsselect.getString(5);
                    logger.debug("Ageif20: " + Ageif20);
                    String Age = "";
                    if (Integer.parseInt(Ageif20) < 0)
                        Age = Ageif19;
                    else
                        Age = Ageif20;
                    logger.debug("Age: " + Age);
                }
            } catch (SQLException ex) {
                date = new Date();
                logger.fatal(dateFormat.format(date));
                logger.fatal("SQLException " + ex.getMessage());
                logger.fatal("With query: select convert(varchar, getdate(), 23) as \"Date\", '19' + substring(Identity_No, 1, 2) + '-' + substring(Identity_No, 3, 2) + '-' + substring(Identity_No, 5, 2) as \"DOB if 1900\", datediff(yy, '19' + substring(Identity_No, 1, 2) + '-' + substring(Identity_No, 3, 2) + '-' + substring(Identity_No, 5, 2), getdate()) as \"Age if 1900\", '20' + substring(Identity_No, 1, 2) + '-' + substring(Identity_No, 3, 2) + '-' + substring(Identity_No, 5, 2) as \"DOB if 2000\", datediff(yy, '20' + substring(Identity_No, 1, 2) + '-' + substring(Identity_No, 3, 2) + '-' + substring(Identity_No, 5, 2), getdate()) as \"Age if 2000\" from T_BP_Customers where BP_Cust_ID = " + BP_Cust_ID);
                //logger.fatal("With query: select convert(varchar, Cust_Capture_Date, 23), A.FirstName, A.SurName, Initials, B.Title, B.Sex, MaritalStat, Identity_No, HAddr1, HAddr2, Haddr3, 'Gauteng', 'South Africa', Home_Phone_Code, Home_Phone_No, Mobile, EMail, Member_No, BP_Cust_ID from T_BP_Customers A, PaymentSystemSQL.dbo.tblMembers B where A.Identity_No = B.IDNum and Identity_No = '" + IdentityNumber + "'");
            }


            try {
                Statement stinsert = Utils.TGDBcon.createStatement();
                date = new Date();
                logger.debug(dateFormat.format(date));
                logger.debug("Query: insert into T_BP_Customer_Policies (BP_Cust_ID, CoverAmount, AdminFee, CoverType, PaymentMethod) values (" + BP_Cust_ID + ", " + TestAmount + ", " + TestFee + ", '" + SelectedType + "', '" + SelectedPayment + "')");
                stinsert.executeUpdate("insert into T_BP_Customer_Policies (BP_Cust_ID, CoverAmount, AdminFee, CoverType, PaymentMethod) values (" + BP_Cust_ID + ", " + TestAmount + ", " + TestFee + ", '" + SelectedType + "', '" + SelectedPayment + "')");
            } catch (SQLException ex) {
                date = new Date();
                logger.fatal(dateFormat.format(date));
                logger.fatal("SQLException " + ex.getMessage());
                logger.fatal("With query: insert into T_BP_Customer_Policies (BP_Cust_ID, CoverAmount, AdminFee, CoverType, PaymentMethod) values (" + BP_Cust_ID + ", " + TestAmount + ", " + TestFee + ", '" + SelectedType + "', '" + SelectedPayment + "')");
            }


            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<link rel=\"stylesheet\" href=\"/PaymentSystemSwipe/" + Beneficiary_Name + "/css/style.css\" type=\"text/css\"/>");
            out.println("<head>");
            out.println("<link rel=\"icon\" type=\"/PaymentSystemSwipe/" + Beneficiary_Name + "/img/Icon.ico\"></link>");
            out.println("<link rel=\"shortcut icon\" href=\"/PaymentSystemSwipe/" + Beneficiary_Name + "/img/Icon.ico\"></link>");
            out.println("<title>Policy Saved</title>");
            out.println("</head>");
            out.println("<body>");

            //Display the report's name as a header within the body of the report:
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
            out.println("					<h3>Policy Information Saved</h3>");

            if (SelectedType.equals("Normal")) {
                out.println("<center><button type=\"button\" onclick =\"document.location.href = '/PaymentSystemSwipe/secureAdmin/Dependant_Details.jsp?bpcustid=" + BP_Cust_ID + "'\">Add dependant(s)</button></center> <br />");
            }

            if (SelectedPayment.equals("TouchPay")) {
                String Identity_No = "";
                String Member_No = "";
                try {
                    Statement stselect = Utils.TGDBcon.createStatement();
                    date = new Date();
                    logger.debug(dateFormat.format(date));
                    logger.debug("Query: select Identity_no, Member_no from T_BP_Customers where BP_Cust_ID = " + BP_Cust_ID);
                    ResultSet rsselect = stselect.executeQuery("select Identity_no, Member_no from T_BP_Customers where BP_Cust_ID = " + BP_Cust_ID);
                    while (rsselect.next()) {
                        Identity_No = rsselect.getString(1);
                        date = new Date();
                        logger.debug(dateFormat.format(date));
                        logger.debug("Identity_No: " + Identity_No);
                        Member_No = rsselect.getString(2);
                        logger.debug("Member_No: " + Member_No);
                    }
                } catch (SQLException ex) {
                    date = new Date();
                    logger.fatal(dateFormat.format(date));
                    logger.fatal("SQLException " + ex.getMessage());
                    logger.fatal("With query: select Identity_no, Member_no from T_BP_Customers where BP_Cust_ID = " + BP_Cust_ID);
                }

                out.println("<center><button type=\"button\" onclick =\"document.location.href = '/PaymentSystemSwipe/secureUser/CardIssue.jsp?bpcustid=" + BP_Cust_ID + "&id=" + Identity_No + "&member=" + Member_No + "&newmember=N" + "'\">Issue TouchPay Card</button></center> <br />");
            }
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
