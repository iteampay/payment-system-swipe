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
@WebServlet(name = "DependantEdit", urlPatterns = {"/DependantEdit"})
public class DependantEdit extends HttpServlet {


    static Logger logger = Logger.getLogger(DependantEdit.class);
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
            logger.info("DependantEdit accessed by: " + User);
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
            String IdentityNumber = request.getParameter("IdentityNumber");
            date = new Date();
            logger.debug(dateFormat.format(date));
            logger.debug("IdentityNumber: " + IdentityNumber);

            String BP_Cust_ID = "";
            String BP_Cust_Dep_ID = "";


            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("    <link rel=\"stylesheet\" href=\"/PaymentSystemSwipe/" + Beneficiary_Name + "/css/style.css\" type=\"text/css\"/>");
            out.println("    <head>");
            out.println("        <link rel=\"icon\" type=\"/PaymentSystemSwipe/" + Beneficiary_Name + "/img/Icon.ico\"></link>");
            out.println("        <link rel=\"shortcut icon\" href=\"/PaymentSystemSwipe/" + Beneficiary_Name + "/img/Icon.ico\"></link>");
            out.println("        <title>Edit Dependant Details</title>");
            out.println("    </head>");
            out.println("    <body>");
            //out.println("        <center><img src=\"ABLogo.jpg\" alt=\"Logo\"/></center>    ");
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
            out.println("					<h3>Please enter dependant details</h3>");
            out.println("        <form name=\"dependantdetails\" method=\"post\" action=\"DependantEditSave\">");

            out.println("<center><table border=\"0\">");


            try {
                Statement stselect = Utils.TGDBcon.createStatement();
                date = new Date();
                logger.debug(dateFormat.format(date));
                logger.debug("Query: select convert(varchar, Cust_Capture_Date, 23), FirstName, SurName, Initials, Title, Sex, Marital_Status, Identity_No, Physical_Address_Line1, Physical_Address_Line2, Physical_Address_Town, Physical_Address_Region, Physical_Address_Country, Home_Phone_Code, Home_Phone_No, Mobile, EMail, Member_No, BP_Cust_ID, BP_Cust_Dep_ID from T_BP_Customer_Dependants where Identity_No = '" + IdentityNumber + "'");
                //logger.debug("Query: select convert(varchar, Cust_Capture_Date, 23), A.FirstName, A.SurName, Initials, B.Title, B.Sex, MaritalStat, Identity_No, HAddr1, HAddr2, Haddr3, 'Gauteng', 'South Africa', Home_Phone_Code, Home_Phone_No, Mobile, EMail, Member_No, BP_Cust_ID from T_BP_Customers A, PaymentSystemSQL.dbo.tblMembers B where A.Identity_No = B.IDNum and Identity_No = '" + IdentityNumber + "'");
                ResultSet rsselect = stselect.executeQuery("select convert(varchar, Cust_Capture_Date, 23), FirstName, SurName, Initials, Title, Sex, Marital_Status, Identity_No, Physical_Address_Line1, Physical_Address_Line2, Physical_Address_Town, Physical_Address_Region, Physical_Address_Country, Home_Phone_Code, Home_Phone_No, Mobile, EMail, Member_No, BP_Cust_ID, BP_Cust_Dep_ID from T_BP_Customer_Dependants where Identity_No = '" + IdentityNumber + "'");
                //ResultSet rsselect = stselect.executeQuery("select convert(varchar, Cust_Capture_Date, 23), A.FirstName, A.SurName, Initials, B.Title, B.Sex, MaritalStat, Identity_No, HAddr1, HAddr2, Haddr3, 'Gauteng', 'South Africa', Home_Phone_Code, Home_Phone_No, Mobile, EMail, Member_No, BP_Cust_ID from T_BP_Customers A, PaymentSystemSQL.dbo.tblMembers B where A.Identity_No = B.IDNum and Identity_No = '" + IdentityNumber + "'");
                while (rsselect.next()) {
                    String Cust_Capture_Date = rsselect.getString(1);
                    date = new Date();
                    logger.debug(dateFormat.format(date));
                    logger.debug("Cust_Capture_Date: " + Cust_Capture_Date);
                    String FirstName = rsselect.getString(2);
                    logger.debug("FirstName: " + FirstName);
                    String SurName = rsselect.getString(3);
                    logger.debug("SurName: " + SurName);
                    String Initials = rsselect.getString(4);
                    logger.debug("Initials: " + Initials);
                    String Title = rsselect.getString(5);
                    logger.debug("Title: " + Title);
                    String Sex = rsselect.getString(6);
                    logger.debug("Sex: " + Sex);
                    String Marital_Status = rsselect.getString(7);
                    logger.debug("Marital_Status: " + Marital_Status);
                    String Identity_No = rsselect.getString(8);
                    logger.debug("Identity_No: " + Identity_No);
                    String Physical_Address_Line1 = rsselect.getString(9);
                    logger.debug("Physical_Address_Line1: " + Physical_Address_Line1);
                    String Physical_Address_Line2 = rsselect.getString(10);
                    logger.debug("Physical_Address_Line2: " + Physical_Address_Line2);
                    String Physical_Address_Town = rsselect.getString(11);
                    logger.debug("Physical_Address_Town: " + Physical_Address_Town);
                    String Physical_Address_Region = rsselect.getString(12);
                    logger.debug("Physical_Address_Region: " + Physical_Address_Region);
                    String Physical_Address_Country = rsselect.getString(13);
                    logger.debug("Physical_Address_Country: " + Physical_Address_Country);
                    String Home_Phone_Code = rsselect.getString(14);
                    logger.debug("Home_Phone_Code: " + Home_Phone_Code);
                    String Home_Phone_No = rsselect.getString(15);
                    logger.debug("Home_Phone_No: " + Home_Phone_No);
                    String Mobile = rsselect.getString(16);
                    logger.debug("Mobile: " + Mobile);
                    String EMail = rsselect.getString(17);
                    logger.debug("EMail: " + EMail);
                    String Member_No = rsselect.getString(18);
                    logger.debug("Member_No: " + Member_No);
                    BP_Cust_ID = rsselect.getString(19);
                    logger.debug("BP_Cust_ID: " + BP_Cust_ID);
                    BP_Cust_Dep_ID = rsselect.getString(20);
                    logger.debug("BP_Cust_Dep_ID: " + BP_Cust_Dep_ID);


                    out.println("<tr>");
                    out.println("<td ALIGN=RIGHT>First Name: <input type=\"text\" name=\"FirstName\" value = \"" + FirstName + "\"></td>");
                    out.println("<td ALIGN=RIGHT>Surname: <input type=\"text\" name=\"SurName\" value = \"" + SurName + "\"></td>");
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td ALIGN=RIGHT>Initials: <input type=\"text\" name=\"Initials\" value = \"" + Initials + "\"></td>");
                    out.println("<td ALIGN=RIGHT>Title: <input type=\"text\" name=\"Title\" value = \"" + Title + "\"></td>");
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td ALIGN=RIGHT>Sex: <input type=\"text\" name=\"Sex\" value = \"" + Sex + "\"></td>");
                    out.println("<td ALIGN=RIGHT>Marital Status: <input type=\"text\" name=\"Marital_Status\" value = \"" + Marital_Status + "\"></td>");
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td ALIGN=RIGHT>Identity Number: <input type=\"text\" name=\"Identity_No\" value = \"" + Identity_No + "\"></td>");
                    out.println("<td ALIGN=RIGHT>Membership Number: <input type=\"text\" name=\"Member_No\" value = \"" + Member_No + "\"></td>");
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td ALIGN=RIGHT>Address Line 1: <input type=\"text\" name=\"Physical_Address_Line1\" value = \"" + Physical_Address_Line1 + "\"></td>");
                    out.println("<td ALIGN=RIGHT>Address Line 2: <input type=\"text\" name=\"Physical_Address_Line1\" value = \"" + Physical_Address_Line1 + "\"></td>");
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td ALIGN=RIGHT>Address Town: <input type=\"text\" name=\"Physical_Address_Town\" value = \"" + Physical_Address_Town + "\"></td>");
                    out.println("<td ALIGN=RIGHT>Address Region: <input type=\"text\" name=\"Physical_Address_Region\" value = \"" + Physical_Address_Region + "\"></td>");
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td ALIGN=RIGHT>Address Country: <input type=\"text\" name=\"Physical_Address_Country\" value = \"" + Physical_Address_Country + "\"></td>");
                    out.println("<td ALIGN=RIGHT>E-Mail: <input type=\"text\" name=\"EMail\" value = \"" + EMail + "\"></td>");
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td ALIGN=RIGHT>Home Phone Number: <input type=\"text\" name=\"Home_Phone_No\" value = \"" + Home_Phone_No + "\"></td>");
                    out.println("<td ALIGN=RIGHT>Mobile: <input type=\"text\" name=\"Mobile\" value = \"" + Mobile + "\"></td>");
                    out.println("</tr>");


                }

                out.println("</table></center>");
                out.println("            <center><input type=\"submit\" value=\"Save\" name=\"dependantdetails\"></center>");


                String CoverType = "";
                String PaymentMethod = "";
                String CoverAmount = "";
                String AdminFee = "";

                try {
                    Statement stgetpolicy = Utils.TGDBcon.createStatement();
                    date = new Date();
                    logger.debug(dateFormat.format(date));
                    logger.debug("Query: select CoverType, PaymentMethod, CoverAmount, AdminFee from T_BP_Customer_Policies where BP_Cust_ID = '" + BP_Cust_ID + "'");
                    ResultSet rsgetolicy = stgetpolicy.executeQuery("select CoverType, PaymentMethod, CoverAmount, AdminFee from T_BP_Customer_Policies where BP_Cust_ID = '" + BP_Cust_ID + "'");
                    while (rsgetolicy.next()) {
                        CoverType = rsgetolicy.getString(1);
                        date = new Date();
                        logger.debug(dateFormat.format(date));
                        logger.debug("CoverType: " + CoverType);
                        PaymentMethod = rsgetolicy.getString(2);
                        logger.debug("PaymentMethod: " + PaymentMethod);
                        CoverAmount = rsgetolicy.getString(3);
                        logger.debug("CoverAmount: " + CoverAmount);
                        AdminFee = rsgetolicy.getString(4);
                        logger.debug("AdminFee: " + AdminFee);
                    }

                } catch (SQLException ex) {
                    date = new Date();
                    logger.fatal(dateFormat.format(date));
                    logger.fatal("SQLException " + ex.getMessage());
                    logger.fatal("With query: select CoverType, PaymentMethod, CoverAmount, AdminFee from T_BP_Customer_Policies where BP_Cust_ID = '" + BP_Cust_ID + "'");
                }

                if ((!CoverType.equals("")) && (CoverType != null) && (!PaymentMethod.equals("")) && (PaymentMethod != null)) {
                    //out.println("            <center><button type=\"button\" onclick =\"document.location.href = '/PaymentSystemSwipe/secureAdmin/PolicyEdit.jsp?bpcustid=" + BP_Cust_ID + "&pan=&type=" + CoverType + "&method=" + PaymentMethod + "&cover=" + CoverAmount + "&fee=" + AdminFee + "'\">Edit Policy Details</button></center> <br />");
                }


                out.println("            <center><button type=\"button\" onclick =\"document.location.href = '/PaymentSystemSwipe/secureUser/home.jsp'\">Back to Main</button></center> <br />");
                out.println("            <center><button type=\"button\" value=\"Back\" onclick=\"history.back()\">Back</button></center>");
                out.println("            <center><input style=\"visibility:hidden\" type=\"text\" name=\"BP_Cust_Dep_ID\" value = \"" + BP_Cust_Dep_ID + "\"/><br /></center>");
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

            } catch (SQLException ex) {
                date = new Date();
                logger.fatal(dateFormat.format(date));
                logger.fatal("SQLException " + ex.getMessage());
                logger.fatal("With query: select convert(varchar, Cust_Capture_Date, 23), FirstName, SurName, Initials, Title, Sex, Marital_Status, Identity_No, Physical_Address_Line1, Physical_Address_Line2, Physical_Address_Town, Physical_Address_Region, Physical_Address_Country, Home_Phone_Code, Home_Phone_No, Mobile, EMail, Member_No, BP_Cust_ID, BP_Cust_Dep_ID from T_BP_Customer_Dependants where Identity_No = '" + IdentityNumber + "'");
                //logger.fatal("With query: select convert(varchar, Cust_Capture_Date, 23), A.FirstName, A.SurName, Initials, B.Title, B.Sex, MaritalStat, Identity_No, HAddr1, HAddr2, Haddr3, 'Gauteng', 'South Africa', Home_Phone_Code, Home_Phone_No, Mobile, EMail, Member_No, BP_Cust_ID from T_BP_Customers A, PaymentSystemSQL.dbo.tblMembers B where A.Identity_No = B.IDNum and Identity_No = '" + IdentityNumber + "'");
            }


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
