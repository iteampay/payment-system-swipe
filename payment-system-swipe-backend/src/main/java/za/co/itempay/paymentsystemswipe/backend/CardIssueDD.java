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
//import PaymentSystemSwipe.POSRsp;

import java.sql.*;

import org.apache.log4j.*;
import org.apache.log4j.xml.DOMConfigurator;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.io.*;
import java.util.*;

import za.co.itempay.paymentsystemswipe.backend.ws.*;


/**
 * @author Administrator
 */
@WebServlet(name = "CardIssueDD", urlPatterns = {"/CardIssueDD"})
public class CardIssueDD extends HttpServlet {

    static Logger logger = Logger.getLogger(CardIssueDD.class);
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
            logger.info("CardIssueDD accessed by: " + User);
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

            String TGResponseCode = "99";

            //Get the details from the JSP page
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

            String RSPCode = "";
            String PAN = "";
            String EasyPay_No = "";
            String Barcode_Info = "";


            String BP_Cust_ID = request.getParameter("BP_Cust_ID");
            logger.debug("BP_Cust_ID: " + BP_Cust_ID);
            String ID = request.getParameter("ID");
            logger.debug("ID: " + ID);
            String Member_No = request.getParameter("Member_No");
            logger.debug("Member_No: " + Member_No);

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<link rel=\"stylesheet\" href=\"/PaymentSystemSwipe/" + Beneficiary_Name + "/css/style.css\" type=\"text/css\"/>");
            out.println("<head>");
            out.println("<link rel=\"icon\" type=\"/PaymentSystemSwipe/" + Beneficiary_Name + "/img/Icon.ico\"></link>");
            out.println("<link rel=\"shortcut icon\" href=\"/PaymentSystemSwipe/" + Beneficiary_Name + "/img/Icon.ico\"></link>");
            out.println("<title>Card Issuing</title>");
            out.println("    <script type=\"text/javascript\">");
            out.println("        function submitcarddetails() {");
            out.println("                idnum = document.addcard.ID.value;");
            out.println("                bpcustid = document.addcard.BP_Cust_ID.value;");
            out.println("                member = document.addcard.Member_No.value;");
            out.println("                pan = document.addcard.PAN.value;");
            out.println("                window.location.href=\"/PaymentSystemSwipe/Save_Card?ID=\"+idnum+\"&BP_Cust_ID=\"+bpcustid+\"&Member_No=\"+member+\"&PAN=\"+pan;");
            out.println("            }");
            out.println("    </script>");
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
            out.println("					<h3>Card Issuing:</h3>");


            try {
                Statement stselect = Utils.TGDBcon.createStatement();
                date = new Date();
                logger.debug(dateFormat.format(date));
                logger.debug("Query:select PAN, EasyPay_No, Barcode_Info from T_EasyPay_No_Track_New where Status = 'Available'");
                ResultSet rsselect = stselect.executeQuery("select PAN, EasyPay_No, Barcode_Info from T_EasyPay_No_Track_New where Status = 'Available'");


                out.println("<form name=\"addcard\" method=\"post\" action=\"Save_Card\">");
                out.println("<center>Select Card Number: <select style=\"width: 153px\" name=\"PAN\">");


                while (rsselect.next()) {
                    PAN = rsselect.getString(1);
                    EasyPay_No = rsselect.getString(2);
                    Barcode_Info = rsselect.getString(3);
                    date = new Date();
                    logger.debug(dateFormat.format(date));
                    logger.debug("PAN: " + PAN);
                    logger.debug("EasyPay_No: " + EasyPay_No);
                    logger.debug("Barcode_Info: " + Barcode_Info);
                    String ShortPAN = PAN.substring(PAN.length() - 9, PAN.length());
                    logger.debug("ShortPAN: " + ShortPAN);

                    out.println("<option value=\"" + PAN + "\">" + ShortPAN + "</option>");


                }

                out.println("</select></center>");

            } catch (SQLException ex) {
                date = new Date();
                logger.fatal(dateFormat.format(date));
                logger.fatal("SQLException " + ex.getMessage());
                logger.fatal("Query:select PAN, EasyPay_No, Barcode_Info from T_EasyPay_No_Track_New where Status = 'Available'");
                String TGResponseMessage = "Database Error";
            }   
            
            
            
            


            /*if (TGResponseCode.equals("00"))
            {
                out.println("<b><center>Card: " + PAN + " Issued Successfuly</center>");
            }
            else if (TGResponseCode.equals("01"))
            {
                out.println("<b><center>Database Error. Please phone support</center>");
            }
            else if (TGResponseCode.equals("02"))
            {
                out.println("<b><center>Card Issuing Unsuccessful</center>");
                out.println("<b><center>Response: " + RSPCode + "</center>");
            }
            else if (TGResponseCode.equals("03"))
            {
                out.println("<b><center>No POS device configured for this IP, please configure POS first.</center>");
            }
            else if (TGResponseCode.equals("99"))
            {
                out.println("<b><center>Internal Error</center>");
            }*/


            //out.println("            <center><button type=\"button\" onclick =\"submitcarddetails()\">Save Card Details</button></center> <br />");
            out.println("            <center><input type=\"submit\" value=\"Proceed\" name=\"AddCard\"></center> <br />");
            out.println("<center><button type=\"button\" onclick =\"document.location.href = '/PaymentSystemSwipe/secureUser/home.jsp'\">Back to Main</button></center> <br />");
            out.println("<center><button type=\"button\" value=\"Back\" onclick=\"history.back()\">Back</button></center>");

            out.println("            <center><input style=\"visibility:hidden\" type=\"text\" name=\"ID\" value=\"" + ID + "\"/><br /></center>");
            out.println("            <center><input style=\"visibility:hidden\" type=\"text\" name=\"BP_Cust_ID\" value=\"" + BP_Cust_ID + "\"/><br /></center>");
            out.println("            <center><input style=\"visibility:hidden\" type=\"text\" name=\"Member_No\" value=\"" + Member_No + "\"/><br /></center>");

            out.println("</form>");


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
            
            /*try
            {
                Statement stinsert = con.createStatement();
                date = new Date();
                logger.debug(dateFormat.format(date));
                logger.debug("Query: insert into T_AB_Payments (Payment_Date_Time, TerminalSerialNo, Merchant_No, Amount, TransRef, Response) values (getdate(), '" + TermID + "', '" + MerchNo + "', " + POSAmount + ", '" + tranRef + "', '" + RSPCode + "')");
                stinsert.executeUpdate("insert into T_AB_Payments (Payment_Date_Time, TerminalSerialNo, Merchant_No, Amount, TransRef, Response) values (getdate(), '" + TermID + "', '" + MerchNo + "', " + POSAmount + ", '" + tranRef + "', '" + RSPCode + "')");
            }
            catch(SQLException ex)
            {
                date = new Date();
                logger.fatal(dateFormat.format(date));
                logger.fatal("SQLException " + ex.getMessage());
                logger.fatal("With query: insert into T_AB_Payments (Payment_Date_Time, TerminalSerialNo, Merchant_No, Amount, TransRef, Response) values (getdate(), '" + TermID + "', '" + MerchNo + "', " + POSAmount + ", '" + tranRef + "', '" + RSPCode + "')");
                TGResponseCode = "01";
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
