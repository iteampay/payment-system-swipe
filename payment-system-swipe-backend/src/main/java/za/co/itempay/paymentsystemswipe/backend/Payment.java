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
@WebServlet(name = "Payment", urlPatterns = {"/Payment"})
public class Payment extends HttpServlet {

    static Logger logger = Logger.getLogger(Payment.class);
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
            logger.info("Payment accessed by: " + User);
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

            //String POSAmount = request.getParameter("POSAmount"); TestAmount
            String TestAmount = request.getParameter("TestAmount");
            date = new Date();
            logger.debug(dateFormat.format(date));
            logger.debug("TestAmount: " + TestAmount);

            String TotalAmount = request.getParameter("TotalAmount");
            date = new Date();
            logger.debug(dateFormat.format(date));
            logger.debug("TotalAmount: " + TotalAmount);


            String tranRef = request.getParameter("tranRef");
            date = new Date();
            logger.debug(dateFormat.format(date));
            logger.debug("tranRef: " + tranRef);

            String POSAmount = Double.toString(Math.round(Double.parseDouble(TotalAmount) * 100));
            if (POSAmount.indexOf(".") > 0)
                POSAmount = POSAmount.substring(0, POSAmount.indexOf("."));
            date = new Date();
            logger.debug(dateFormat.format(date));
            logger.debug("POSAmount: " + POSAmount);

            /*String TermID = "810193521";
            String MerchNo = "1240365";*/
            String TermID = "";
            String MerchNo = "";

            String RSPCode = "";
            
            /*try
            {
                Statement stselect = con.createStatement();
                date = new Date();
                logger.debug(dateFormat.format(date));
                logger.debug("Query: select POSSerialNo, MerchantNo from T_Map_Pos where BranchIPRange = '" + IPRange + "'");
                ResultSet rsselect = stselect.executeQuery("select POSSerialNo, MerchantNo from T_Map_Pos where BranchIPRange = '" + IPRange + "'");
                while(rsselect.next())
                {*/
            //TermID = rsselect.getString(1);
            //TermID = "810193521";
            TermID = "411270244";
            date = new Date();
            logger.debug(dateFormat.format(date));
            logger.debug("TermID: " + TermID);
            //MerchNo = rsselect.getString(2);
            MerchNo = "1240365";
            date = new Date();
            logger.debug(dateFormat.format(date));
            logger.debug("MerchNo: " + MerchNo);
            //}
            if (!TermID.equals("") && !MerchNo.equals("")) {
                POSRsp result = posPurchase(TermID, MerchNo, POSAmount, tranRef);


                RSPCode = result.getResponseCode();
                date = new Date();
                logger.debug(dateFormat.format(date));
                logger.debug("RSPCode: " + RSPCode);
                String AccountNo = result.getAccountNumber();
                date = new Date();
                logger.debug(dateFormat.format(date));
                logger.debug("AccountNo: " + AccountNo);
                String PAN = result.getPAN();
                date = new Date();
                logger.debug(dateFormat.format(date));
                logger.debug("PAN: " + PAN);

                if (RSPCode.equals("00")) {
                    //out.println("<b><center>Card Payment Successful</center>");
                    TGResponseCode = "00";
                } else {
                    TGResponseCode = "02";
                }
            } else {
                TGResponseCode = "03";
            }
            /*}
            catch(SQLException ex)
            {
                date = new Date();
                logger.fatal(dateFormat.format(date));
                logger.fatal("SQLException " + ex.getMessage());
                logger.fatal("With query: select POSSerialNo, MerchantNo from T_Map_Pos where BranchIPRange = '" + IPRange + "'");
                TGResponseCode = "01";
            }*/

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<link rel=\"stylesheet\" href=\"/PaymentSystemSwipe/" + Beneficiary_Name + "/css/style.css\" type=\"text/css\"/>");
            out.println("<head>");
            out.println("<link rel=\"icon\" type=\"/PaymentSystemSwipe/" + Beneficiary_Name + "/img/Icon.ico\"></link>");
            out.println("<link rel=\"shortcut icon\" href=\"/PaymentSystemSwipe/" + Beneficiary_Name + "/img/Icon.ico\"></link>");
            out.println("<title>Payment</title>");
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
            out.println("					<h3>Payment result</h3>");


            if (TGResponseCode.equals("00")) {
                out.println("<b><center>Card Payment Successful</center>");
            } else if (TGResponseCode.equals("01")) {
                out.println("<b><center>Database Error. Please phone support</center>");
            } else if (TGResponseCode.equals("02")) {
                out.println("<b><center>Card Payment Unsuccessful</center>");
                out.println("<b><center>Response: " + RSPCode + "</center>");
            } else if (TGResponseCode.equals("03")) {
                out.println("<b><center>No POS device configured for this IP, please configure POS first.</center>");
            } else if (TGResponseCode.equals("99")) {
                out.println("<b><center>Internal Error</center>");
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

    private static POSRsp posPurchase(String terminalID, String merchantID, String posAmount, String tranRef) {
        PosTransactionService service = new PosTransactionService();
        PosTransactionServiceSoap port = service.getPosTransactionServiceSoap();
        return port.posPurchase(terminalID, merchantID, posAmount, tranRef);
    }
}
