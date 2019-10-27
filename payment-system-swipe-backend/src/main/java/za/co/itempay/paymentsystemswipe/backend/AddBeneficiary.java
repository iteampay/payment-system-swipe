/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.itempay.paymentsystemswipe.backend;

import za.co.itempay.paymentsystemswipe.backend.Utils;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.sql.*;

import org.apache.log4j.*;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


@WebServlet(name = "Add_Beneficiary", urlPatterns = {"/Add_Beneficiary"})
public class AddBeneficiary extends HttpServlet {

    final static Logger logger = Logger.getLogger(AddBeneficiary.class);
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
            logger.info("Add_Beneficiary accessed by: " + User);
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
                logger.log(Level.INFO, dateFormat.format(date));
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
            
            
            /*String User = request.getRemoteUser();
            date = new Date();
            logger.info(dateFormat.format(date));
            logger.info("MemberDetails accessed by: " + User);
            String Beneficiary_No = "";
            
            try
            {
                Statement stselect = Utils.TGDBcon.createStatement();
                date = new Date();
                logger.debug(dateFormat.format(date));
                logger.debug("Query: select Beneficiary_No from Users where User_Name = '" + User + "'");
                ResultSet rsselect = stselect.executeQuery("select Beneficiary_No from Users where User_Name = '" + User + "'");
                while(rsselect.next())
                {
                    Beneficiary_No = rsselect.getString(1);
                    date = new Date();
                    logger.debug(dateFormat.format(date));
                    logger.debug("Beneficiary_No: " + Beneficiary_No);
                }
            }
            catch(SQLException ex)
            {
                date = new Date();
                logger.fatal(dateFormat.format(date));
                logger.fatal("SQLException " + ex.getMessage());
                logger.fatal("With query: select Beneficiary_No from Users where User_Name = '" + User + "'");
            }*/


            //String IP = request.getRemoteAddr();
            /*date = new Date();
            logger.debug(dateFormat.format(date));
            //logger.debug("IP: " + IP);*/

            //String IPRange = IP.substring(0, IP.lastIndexOf("."));
            //IPRange = IPRange + ".0/24";
            /*date = new Date();
            logger.debug(dateFormat.format(date));
            //logger.debug("IPRange: " + IPRange);*/

            //String HostName = request.getRemoteHost();
            /*date = new Date();
            logger.debug(dateFormat.format(date));
            //logger.debug("HostName: " + HostName);*/
            String IDNum = request.getParameter("IDNum");
            /*date = new Date();
            logger.debug(dateFormat.format(date));
            logger.debug("TerminalSerialNumber: " + TerminalSerialNumber);*/

            
            /*out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<link rel=\"stylesheet\" href=\"/PaymentSystemSwipe/css/style.css\" type=\"text/css\"/>");
            out.println("<head>");
            out.println("<link rel=\"icon\" type=\"image/ico\" href=\"TPIconTrans.ico\"></link>");
            out.println("<link rel=\"shortcut icon\" href=\"TPIconTrans.ico\"></link>");
            out.println("<title>Payment History</title>");
            out.println("</head>");
            out.println("<body>");*/

            out.println("<!DOCTYPE html>");
            out.println("<%@ taglib uri=\"/WEB-INF/tld/c.tld\" prefix=\"c\" %>");
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

            try {
                Statement stselect = Utils.TGDBcon.createStatement();
                date = new Date();
                logger.debug(dateFormat.format(date));
                logger.debug("Query:select Beneficiary_Name, Beneficiary_No from T_BP_Beneficiaries where Beneficiary_No not in (select D.Beneficiary_No from T_BP_Customers A, T_BP_Card B, T_BP_Card_Beneficiaries C, T_BP_Beneficiaries D WHERE A.BP_Cust_ID = B.BP_Cust_ID and B.PAN = C.PAN and C.Beneficiary_No = D.Beneficiary_No and A.Identity_No = '" + IDNum + "')");
                ResultSet rsselect = stselect.executeQuery("Query:select Beneficiary_Name, Beneficiary_No from T_BP_Beneficiaries where Beneficiary_No not in (select D.Beneficiary_No from T_BP_Customers A, T_BP_Card B, T_BP_Card_Beneficiaries C, T_BP_Beneficiaries D WHERE A.BP_Cust_ID = B.BP_Cust_ID and B.PAN = C.PAN and C.Beneficiary_No = D.Beneficiary_No and A.Identity_No = '" + IDNum + "')");

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
                out.println("					<h3>Please select beneficiary to add</h3>");


                out.println("<form name=\"addben\" method=\"post\" action=\"Save_Beneficiary\">");
                out.println("<center>Select Beneficiary: <select style=\"width: 153px\" name=\"Beneficiary\">");


                while (rsselect.next()) {
                    String Add_Beneficiary_Name = rsselect.getString(1);
                    String Add_Beneficiary_No = rsselect.getString(2);

                    date = new Date();
                    logger.debug(dateFormat.format(date));
                    logger.debug("Add_Beneficiary_No: " + Add_Beneficiary_No);
                    logger.debug("Add_Beneficiary_Name: " + Add_Beneficiary_Name);

                    out.println("<option value=\"" + Add_Beneficiary_No + "\">" + Add_Beneficiary_Name + "</option>");


                }

                out.println("</select></center>");

            } catch (SQLException ex) {


                date = new Date();
                logger.fatal(dateFormat.format(date));
                logger.fatal("SQLException " + ex.getMessage());
                logger.fatal("Query:select Beneficiary_Name, Beneficiary_No from T_BP_Beneficiaries where Beneficiary_No not in (select D.Beneficiary_No from T_BP_Customers A, T_BP_Card B, T_BP_Card_Beneficiaries C, T_BP_Beneficiaries D WHERE A.BP_Cust_ID = B.BP_Cust_ID and B.PAN = C.PAN and C.Beneficiary_No = D.Beneficiary_No and A.Identity_No = '" + IDNum + "')");
                String TGResponseCode = "05";
                String TGResponseMessage = "Database Error";


            }   
                        
            /*out.println("<c:choose>");
            out.println("    <c:when test=\"${empty param.IDNum}\">");
            out.println("        <center><input style=\"visibility:hidden\" type=\"text\" name=\"IDNum\" value = \"\"/><br/></center>");
            out.println("    </c:when>");
            out.println("    <c:otherwise>");
            out.println("        <center><input style=\"visibility:hidden\" type=\"text\" name=\"IDNum\" value = \"<c:out value=\"${param.IDNum}\"/>\"/><br/></center>");
            out.println("    </c:otherwise>");
            out.println("</c:choose>");*/
            out.println("<center><input type=\"submit\" value=\"Proceed\" name=\"AddBeneficiary\"></center>");
            out.println("<center><button type=\"button\" onclick =\"document.location.href = '/PaymentSystemSwipe/secureUser/home.jsp'\">Back to Main</button></center> <br />");
            out.println("<center><button type=\"button\" value=\"Back\" onclick=\"history.back()\">Back</button></center>");
            out.println("<center><input style=\"visibility:hidden\" type=\"text\" name=\"IDNum\" value = \"" + IDNum + "\"/><br/></center>");
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
