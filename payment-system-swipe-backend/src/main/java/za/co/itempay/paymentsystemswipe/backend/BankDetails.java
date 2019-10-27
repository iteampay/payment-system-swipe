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
@WebServlet(name = "BankDetails", urlPatterns = {"/BankDetails"})
public class BankDetails extends HttpServlet {

    static Logger logger = Logger.getLogger(BankDetails.class);
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
            logger.info("BankDetails accessed by: " + User);
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
            String Branch = request.getParameter("Branch");
            logger.debug("Branch: " + Branch);
            String AcctHolder = request.getParameter("AcctHolder");
            logger.debug("AcctHolder: " + AcctHolder);
            String AcctNr = request.getParameter("AcctNr");
            logger.debug("AcctNr: " + AcctNr);
            String IDNum = request.getParameter("IDNum");
            logger.debug("IDNum: " + IDNum);
            
            /*if (SortBy.equals("") || SortBy == null)
            {
                SortBy = "Identity_No";
            }
            if (AscDesc.equals("") || AscDesc == null)
            {
                AscDesc = "Asc";
            }
            String OrderBy = SortBy + " " + AscDesc;*/

            String Identity_No = "";


            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<link rel=\"stylesheet\" href=\"/PaymentSystemSwipe/" + Beneficiary_Name + "/css/style.css\" type=\"text/css\"/>");
            out.println("<head>");
            out.println("<link rel=\"icon\" type=\"/PaymentSystemSwipe/" + Beneficiary_Name + "/img/TPIcon.ico\"></link>");
            out.println("<link rel=\"shortcut icon\" href=\"/PaymentSystemSwipe/" + Beneficiary_Name + "/img/TPIcon.ico\"></link>");
            out.println("<title>Bank Details</title>");
            out.println("</head>");
            out.println("<body>");


            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<link rel=\"stylesheet\" href=\"/PaymentSystemSwipe/" + Beneficiary_Name + "/css/style.css\" type=\"text/css\"/>");
            out.println("<head>");
            out.println("<link rel=\"icon\" type=\"/PaymentSystemSwipe/" + Beneficiary_Name + "/img/Icon.ico\"></link>");
            out.println("<link rel=\"shortcut icon\" href=\"/PaymentSystemSwipe/" + Beneficiary_Name + "/img/Icon.ico\"></link>");
            out.println("        <title>Bank Details</title>");
            out.println("    <script type=\"text/javascript\">");
            out.println("        function bankchange() {");
            out.println("                idnum = document.BankDetails.IDNum.value;");
            out.println("                bankname = document.BankDetails.BankName.value;");
            //out.println("                branchname = document.BankDetails.BranchName.value;");
            //out.println("                window.location.href=\"/PaymentSystemSwipe/secureAdmin/BankDetails.jsp?id=\"+idnum+\"&bankname=\"+bankname+\"&branchname=\"+branchname;");
            out.println("                window.location.href=\"/PaymentSystemSwipe/secureAdmin/BankDetails.jsp?id=\"+idnum+\"&bankname=\"+bankname;");
            //out.println("                alert(\"idnum: \" + idnum);");
            //out.println("                window.location.href=\"/PaymentSystemSwipe/secureAdmin/BankDetails.jsp?id=\"+idnum;");
            out.println("            }");
            out.println("        function branchchange() {");
            out.println("                idnum = document.BankDetails.IDNum.value;");
            out.println("                bankname = document.BankDetails.BankName.value;");
            out.println("                branchname = document.BankDetails.BranchName.value;");
            out.println("                window.location.href=\"/PaymentSystemSwipe/secureAdmin/BankDetails.jsp?id=\"+idnum+\"&bankname=\"+bankname+\"&branchname=\"+branchname;");
            out.println("            }");
            out.println("        function submitbankdetails() {");
            out.println("                idnum = document.BankDetails.IDNum.value;");
            out.println("                bankname = document.BankDetails.BankName.value;");
            out.println("                branchname = document.BankDetails.BranchName.value;");
            out.println("                bankacctholder = document.BankDetails.AcctHolder.value;");
            out.println("                bankacctnr = document.BankDetails.BankAcctNr.value;");
            out.println("                window.location.href=\"/PaymentSystemSwipe/SaveBankDetails?IDNum=\"+idnum+\"&BankName=\"+bankname+\"&BranchName=\"+branchname+\"&BankAcctHolder=\"+bankacctholder+\"&BankAcctNr=\"+bankacctnr;");
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
            out.println("					<h3>Bank details</h3>");
                                
                                
            /*out.println("        <form name=\"memberdetails\" method=\"post\" action=\"MemberEdit\">");
            out.println("            <center>Search: <input type=\"text\" name=\"Search\"/><button type=\"button\" onclick =\"GetSearch()\">Search</button></center><br />");
            out.println("            <center><button type=\"button\" onclick =\"document.location.href = '/PaymentSystemSwipe/secureUser/home.jsp'\">Back to Main</button></center> <br />");
            out.println("<center><button type=\"button\" value=\"Back\" onclick=\"history.back()\">Back</button></center>");
            out.println("<center><table border=\"1\">");
            out.println("<tr>");
            if (SortBy.equals("FirstName") && AscDesc.equals("Asc"))
            {
                out.println("<th><a href=\"/PaymentSystemSwipe/secureAdmin/Members.jsp?search=" + Search + "&sort=FirstName&order=Desc\">Name</a></th>");
            }
            else
            {
                out.println("<th><a href=\"/PaymentSystemSwipe/secureAdmin/Members.jsp?search=" + Search + "&sort=FirstName&order=Asc\">Name</a></th>");

            }
            if (SortBy.equals("Identity_No") && AscDesc.equals("Asc"))
            {
                out.println("<th><a href=\"/PaymentSystemSwipe/secureAdmin/Members.jsp?search=" + Search + "&sort=Identity_No&order=Desc\">Identity Number</a></th>");
            }
            else
            {
                out.println("<th><a href=\"/PaymentSystemSwipe/secureAdmin/Members.jsp?search=" + Search + "&sort=Identity_No&order=Asc\">Identity Number</a></th>");

            }
            if (SortBy.equals("Member_No") && AscDesc.equals("Asc"))
            {
                out.println("<th><a href=\"/PaymentSystemSwipe/secureAdmin/Members.jsp?search=" + Search + "&sort=Member_No&order=Desc\">Membership Number</a></th>");
            }
            else
            {
                out.println("<th><a href=\"/PaymentSystemSwipe/secureAdmin/Members.jsp?search=" + Search + "&sort=Member_No&order=Asc\">Membership Number</a></th>");

            }
            out.println("</tr>");*/

            out.println("<form name=\"BankDetails\" method=\"post\" action=\"Save_Beneficiary\">");
            out.println("<center><input style=\"visibility:hidden\" type=\"text\" name=\"IDNum\" value = \"" + IDNum + "\"/><br/></center>");

            if (BankName == null)
                BankName = "";
            if (Branch == null)
                Branch = "";
            if (AcctHolder == null)
                AcctHolder = "";
            if (AcctNr == null)
                AcctNr = "";


            if (BankName.equals("") || BankName == null) {
                try {
                    Statement stselect = Utils.TGDBcon.createStatement();
                    date = new Date();
                    logger.debug(dateFormat.format(date));
                    logger.debug("Query:select Bank_Name, Bank_ID from T_BP_Bank");
                    ResultSet rsselect = stselect.executeQuery("Query:select Bank_Name, Bank_ID from T_BP_Bank");


                    //out.println("<center>Select Bank: <select style=\"width: 153px\" name=\"BankName\" onchange=\"bankchange()\">");
                    out.println("<center>Select Bank: <select style=\"width: 153px\" name=\"BankName\" onchange=\"bankchange()\" value=\"Choose One\">");
                    out.println("<option value=\"Choose One\">Choose One</option>");

                    while (rsselect.next()) {
                        String Bank_Name = rsselect.getString(1);
                        String Bank_ID = rsselect.getString(2);

                        date = new Date();
                        logger.debug(dateFormat.format(date));
                        logger.debug("Bank_Name: " + Bank_Name);
                        logger.debug("Bank_ID: " + Bank_ID);

                        out.println("<option value=\"" + Bank_Name + "\">" + Bank_Name + "</option>");
                    }

                    out.println("</select></center>");

                } catch (SQLException ex) {
                    date = new Date();
                    logger.fatal(dateFormat.format(date));
                    logger.fatal("SQLException " + ex.getMessage());
                    logger.fatal("Query:select Bank_Name, Bank_ID from T_BP_Bank");
                    String TGResponseCode = "05";
                    String TGResponseMessage = "Database Error";
                }
            } else {
                if (Branch.equals("") || Branch == null) {
                    try {
                        out.println("<center>Bank Name: <input type=\"text\" name=\"BankName\" value = \"" + BankName + "\"/> <a href=\"/PaymentSystemSwipe/BankDetails?IDNum=" + IDNum + "\">Change Bank</a><br/></center>");
                        Statement stselect = Utils.TGDBcon.createStatement();
                        date = new Date();
                        logger.debug(dateFormat.format(date));
                        logger.debug("Query:select Branch_Name from T_BP_Bank A, T_BP_Bank_Branches B where A.Bank_ID = B.Bank_ID and A.Bank_Name = '" + BankName + "'");
                        ResultSet rsselect = stselect.executeQuery("Query:select Branch_Name from T_BP_Bank A, T_BP_Bank_Branches B where A.Bank_ID = B.Bank_ID and A.Bank_Name = '" + BankName + "'");


                        out.println("<center>Select Branch: <select style=\"width: 153px\" name=\"BranchName\" onchange=\"branchchange()\" value=\"Choose One\">");
                        out.println("<option value=\"Choose One\">Choose One</option>");
                        while (rsselect.next()) {
                            String Branch_Name = rsselect.getString(1);

                            date = new Date();
                            logger.debug(dateFormat.format(date));
                            logger.debug("Branch_Name: " + Branch_Name);

                            out.println("<option value=\"" + Branch_Name + "\">" + Branch_Name + "</option>");
                        }

                        out.println("</select></center>");

                    } catch (SQLException ex) {
                        date = new Date();
                        logger.fatal(dateFormat.format(date));
                        logger.fatal("SQLException " + ex.getMessage());
                        logger.fatal("Query:select Branch_Name from T_BP_Bank A, T_BP_Bank_Branches B where A.Bank_ID = B.Bank_ID and A.Bank_Name = '" + BankName + "'");
                        String TGResponseCode = "05";
                        String TGResponseMessage = "Database Error";
                    }
                } else {
                    out.println("<center>Bank Name: <input type=\"text\" name=\"BankName\" value = \"" + BankName + "\"/> <a href=\"/PaymentSystemSwipe/secureAdmin/BankDetails.jsp\">Change Bank</a><br/></center>");
                    out.println("<center>Branch Name: <input type=\"text\" name=\"BranchName\" value = \"" + Branch + "\"/> <a href=\"/PaymentSystemSwipe/secureAdmin/BankDetails.jsp&bank=\">Change Branch</a><br/></center>");
                    out.println("            <center>");
                    out.println("                <table border=\"0\">");
                    out.println("                    <tr>");
                    if (AcctHolder.equals("")) {
                        out.println("                        <td ALIGN=RIGHT>Account Holder Name: <input type=\"text\" name=\"AcctHolder\"></td>");
                    } else {
                        out.println("                        <td ALIGN=RIGHT>Account Holder Name: <input type=\"text\" name=\"AcctHolder\" value=\"" + AcctHolder + "\"></td>");
                    }
                    out.println("                    </tr>");
                    out.println("                    <tr>");
                    if (AcctNr.equals("")) {
                        out.println("                        <td ALIGN=RIGHT>Account Number: <input type=\"text\" name=\"BankAcctNr\"></td>");
                    } else {
                        out.println("                        <td ALIGN=RIGHT>Account Number: <input type=\"text\" name=\"BankAcctNr\" value=\"" + AcctNr + "\"></td>");
                    }
                    out.println("                    </tr>");
                    out.println("                </table>");
                    out.println("            </center>");
                    out.println("            <center><button type=\"button\" onclick =\"submitbankdetails()\">Save Bank Details</button></center> <br />");
                }
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
