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
@WebServlet(name = "SaveBankDetails", urlPatterns = {"/SaveBankDetails"})
public class SaveBankDetails extends HttpServlet {

    static Logger logger = Logger.getLogger(SaveBankDetails.class);
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
            logger.info("SaveBankDetails accessed by: " + User);
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


            String BankName = request.getParameter("BankName");
            date = new Date();
            logger.debug(dateFormat.format(date));
            logger.debug("BankName: " + BankName);
            String BranchName = request.getParameter("BranchName");
            logger.debug("BranchName: " + BranchName);
            String BankAcctHolder = request.getParameter("BankAcctHolder");
            logger.debug("BankAcctHolder: " + BankAcctHolder);
            String BankAcctNr = request.getParameter("BankAcctNr");
            logger.debug("BankAcctNr: " + BankAcctNr);
            String IDNum = request.getParameter("IDNum");
            logger.debug("IDNum: " + IDNum);

            String Branch_Code = "";

            try {
                Statement stselect = Utils.TGDBcon.createStatement();
                date = new Date();
                logger.debug(dateFormat.format(date));
                logger.debug("Query: select Branch_Code from T_BP_Bank A, T_BP_Bank_Branches B where A.Bank_ID = B.Bank_ID and Bank_Name = '" + BankName + "' and Branch_Name = '" + BranchName + "'");
                ResultSet rsselect = stselect.executeQuery("select Branch_Code from T_BP_Bank A, T_BP_Bank_Branches B where A.Bank_ID = B.Bank_ID and Bank_Name = '" + BankName + "' and Branch_Name = '" + BranchName + "'");
                while (rsselect.next()) {
                    Branch_Code = rsselect.getString(1);
                    date = new Date();
                    logger.debug(dateFormat.format(date));
                    logger.debug("Branch_Code: " + Branch_Code);
                }
            } catch (SQLException ex) {
                date = new Date();
                logger.fatal(dateFormat.format(date));
                logger.fatal("SQLException " + ex.getMessage());
                logger.fatal("With query: select Branch_Code from T_BP_Bank A, T_BP_Bank_Branches B where A.Bank_ID = B.Bank_ID and Bank_Name = '" + BankName + "' and Branch_Name = '" + BranchName + "'");
            }

            try {
                Statement stinsert = Utils.TGDBcon.createStatement();
                date = new Date();
                logger.debug(dateFormat.format(date));
                logger.debug("Query: update T_BP_Customers set Bank_Acct_No = '" + BankAcctNr + "', Bank_Acct_Type = '10', Bank_Branch = '" + BranchName + "', Branch_Code = '" + Branch_Code + "', Bank_Name = '" + BankName + "', Bank_Acct_Holder = '" + BankAcctHolder + "' where Identity_No = '" + IDNum + "'");
                stinsert.executeUpdate("update T_BP_Customers set Bank_Acct_No = '" + BankAcctNr + "', Bank_Acct_Type = '10', Bank_Branch = '" + BranchName + "', Branch_Code = '" + Branch_Code + "', Bank_Name = '" + BankName + "', Bank_Acct_Holder = '" + BankAcctHolder + "' where Identity_No = '" + IDNum + "'");
            } catch (SQLException ex) {
                date = new Date();
                logger.fatal(dateFormat.format(date));
                logger.fatal("SQLException " + ex.getMessage());
                logger.fatal("With query: update T_BP_Customers set Bank_Acct_No = '" + BankAcctNr + "', Bank_Acct_Type = '10', Bank_Branch = '" + BranchName + "', Branch_Code = '" + Branch_Code + "', Bank_Name = '" + BankName + "', Bank_Acct_Holder = '" + BankAcctHolder + "' where Identity_No = '" + IDNum + "'");
            }


            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<link rel=\"stylesheet\" href=\"/PaymentSystemSwipe/" + Beneficiary_Name + "/css/style.css\" type=\"text/css\"/>");
            out.println("<head>");
            out.println("<link rel=\"icon\" type=\"/PaymentSystemSwipe/" + Beneficiary_Name + "/img/TPIcon.ico\"></link>");
            out.println("<link rel=\"shortcut icon\" href=\"/PaymentSystemSwipe/" + Beneficiary_Name + "/img/TPIcon.ico\"></link>");
            out.println("<title>Bank Details Saved</title>");
            out.println("</head>");
            out.println("<body>");


            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<link rel=\"stylesheet\" href=\"/PaymentSystemSwipe/" + Beneficiary_Name + "/css/style.css\" type=\"text/css\"/>");
            out.println("<head>");
            out.println("<link rel=\"icon\" type=\"/PaymentSystemSwipe/" + Beneficiary_Name + "/img/Icon.ico\"></link>");
            out.println("<link rel=\"shortcut icon\" href=\"/PaymentSystemSwipe/" + Beneficiary_Name + "/img/Icon.ico\"></link>");
            out.println("        <title>Bank Details Saved</title>");
            out.println("    <script type=\"text/javascript\">");
            out.println("        function GetSearch() {");
            out.println("                search = document.memberdetails.Search.value;");
            //out.println("                alert(\"search: \" + search);");
            /*out.println("                <jsp:forward page=\"/PaymentSystemSwipe/secureAdmin/Members.jsp\">");
            out.println("                <jsp:param name=\"search\" value=\"search\" />");
            out.println("                </jsp:forward>             ");*/
            out.println("                window.location.href=\"/PaymentSystemSwipe/secureAdmin/Members.jsp?search=\"+search;");
            out.println("            }");
            out.println("        function bankchange() {");
            out.println("                bankname = document.BankDetails.BankName.value;");
            out.println("                idnum = document.BankDetails.IDNum.value;");
            out.println("                window.location.href=\"/PaymentSystemSwipe/secureAdmin/BankDetails.jsp?id=\"+idnum+\"&bankname=\"+bankname;");
            //out.println("                alert(\"idnum: \" + idnum);");
            //out.println("                window.location.href=\"/PaymentSystemSwipe/secureAdmin/BankDetails.jsp?id=\"+idnum;");
            out.println("            }");
            out.println("        function branchchange() {");
            out.println("                bankname = document.BankDetails.BankName.value;");
            out.println("                branchname = document.BankDetails.BranchName.value;");
            out.println("                window.location.href=\"/PaymentSystemSwipe/secureAdmin/BankDetails.jsp?bankname=\"+bankname+\"&branchname=\"+branchname;");
            out.println("            }");
            out.println("    </script>");
            /*out.println("    <link rel=\"stylesheet\" href=\"/PaymentSystemSwipe/" + Beneficiary_Name + "/css/style.css\" type=\"text/css\"/>");
            out.println("    <head>");
            out.println("        <link rel=\"icon\" type=\"/PaymentSystemSwipe/"+ Beneficiary_Name + "/img/Icon.ico\"></link>");
            out.println("        <link rel=\"shortcut icon\" href=\"/PaymentSystemSwipe/"+ Beneficiary_Name + "/img/Icon.ico\"></link>");
            out.println("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");*/
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
            out.println("			<li class=\"first\"><a href=\"/PaymentSystemSwipe/secureAdmin/MemberDetailsID.jsp\" class=\"active\">Add new member</a></li>");
            out.println("			<li><a href=\"/PaymentSystemSwipe/secureAdmin/Members.jsp\">Current Members</a></li>");
            out.println("			<li><a href=\"/PaymentSystemSwipe/secureUser/Payments.jsp\">Payment Report</a></li>");
            out.println("			<li><a href=\"/PaymentSystemSwipe/secureAdmin/MemberCashPayment.jsp\">Process a Payment</a></li>");
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
            out.println("					<h3>Bank details saved</h3>");


            out.println("<form name=\"BankDetails\" method=\"post\" action=\"Save_Beneficiary\">");
            out.println("<center><input style=\"visibility:hidden\" type=\"text\" name=\"IDNum\" value = \"" + IDNum + "\"/><br/></center>");


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
