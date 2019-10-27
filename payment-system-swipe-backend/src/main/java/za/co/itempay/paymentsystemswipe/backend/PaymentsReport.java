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

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@WebServlet(name = "Payments_Report", urlPatterns = {"/Payments_Report"})
public class PaymentsReport extends HttpServlet {


    static Logger logger = Logger.getLogger(PaymentsReport.class);
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
            logger.info("Add_Member accessed by: " + User);
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
            String MembershipNo = request.getParameter("MembershipNo");
            String IDNum = request.getParameter("IDNum");
            ;
            /*date = new Date();
            logger.debug(dateFormat.format(date));
            logger.debug("TerminalSerialNumber: " + TerminalSerialNumber);*/
            String surname = request.getParameter("Surname");
            String FirstName = request.getParameter("FirstName");
            String Password = request.getParameter("Password");
            
            /*String surname = request.getParameter("sex");
            String FirstName = request.getParameter("IDNUM");
            string MaritalStatus = request.getParameter("MaritalStatus");
            
            String Address1 = request.getParameter("Address1");
            string Address2 = request.getParameter("Address2");
            String Town = request.getParameter("Town");
            string code = request.getParameter("Code");
             
            String pAddress1 = request.getParameter("pAddress1");
            string pAddress2 = request.getParameter("pAddress2");
            String pTown = request.getParameter("pTown");
            string pcode = request.getParameter("pCode");
             
             
            String wPhone = request.getParameter("wPhone");
            string Mobile = request.getParameter("Mobile");
            String fax = request.getParameter("fax");
            string email = request.getParameter("email");*/

            String TermID = "";

            TermID = "";

            String File_Date_Time = "";
            String Surname = "";

            String Collector_Name = "";
            String Amount = "";
            String TP_Fee = "";

            
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
                logger.debug("Query:SELECT d.File_Date_Time, e.Acct_Ref_No,A.Surname, A.FirstName, A.Identity_No, D.EasyPay_No,D.PAN, D.Tender_Type, F. Collector_Name, D.Amount, D.TP_Fee FROM T_BP_Customers A, T_BP_Card B, T_EasyPay_No_Track C, T_EasyPay_Messages D, T_BP_Card_Beneficiaries E, T_EasyPay_Collectors F WHERE A.Identity_No = '" + IDNum + "' AND D.Beneficiary_no = '" + Beneficiary_No + "' AND A.BP_Cust_ID = B.BP_Cust_ID AND B.PAN = C.PAN AND B.PAN = E.PAN AND D.Beneficiary_no = E.Beneficiary_no AND C.EasyPay_No = D.EasyPay_No AND D.Collector_Identifier = F.Collector_Identifier Order by d.File_Date_Time desc");
                //ResultSet rsselect = stselect.executeQuery("select POSSerialNo, MerchantNo from T_Map_Pos where BranchIPRange = '" + IPRange + "'");
                //ResultSet rsselect = stselect.executeQuery("Query: select Beneficiary_no, User_name, PIN from T_Web_users where PIN = '" + Password + "'");

                ResultSet rsselect = stselect.executeQuery("Query:SELECT d.File_Date_Time, e.Acct_Ref_No,A.Surname, A.FirstName, A.Identity_No, D.EasyPay_No,D.PAN, D.Tender_Type, F. Collector_Name, D.Amount, D.TP_Fee FROM T_BP_Customers A, T_BP_Card B, T_EasyPay_No_Track C, T_EasyPay_Messages D, T_BP_Card_Beneficiaries E, T_EasyPay_Collectors F WHERE A.Identity_No = '" + IDNum + "' AND D.Beneficiary_no = '" + Beneficiary_No + "' AND A.BP_Cust_ID = B.BP_Cust_ID AND B.PAN = C.PAN AND B.PAN = E.PAN AND D.Beneficiary_no = E.Beneficiary_no AND C.EasyPay_No = D.EasyPay_No AND D.Collector_Identifier = F.Collector_Identifier Order by d.File_Date_Time desc");


////AND B.PAN = C.PAN AND B.PAN = E.PAN AND D.Beneficiary_no = E.Beneficiary_no 
//AND C.EasyPay_No = D.EasyPay_No AND D.Collector_Identifier = F.Collector_Identifier                         
//AND D.Deleted = 'N'       ");         


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
                out.println("					<h3>Payment History for ID: " + IDNum + "</h3>");


                out.println("<center><table border=\"1\">");
                out.println("<tr>");
                out.println("<th>Date and Time</th>");
                out.println("<th>Surname</th>");
                out.println("<th>Firstname</th>");
                out.println("<th>Place of payment</th>");
                out.println("<th>Amount</th>");
//out.println("<th>Fee</th>");
                out.println("</tr>");

                while (rsselect.next()) {
                    TermID = rsselect.getString(1);
                    String PIN = rsselect.getString(3);


                    File_Date_Time = rsselect.getString(1);
                    Surname = rsselect.getString(3);
                    FirstName = rsselect.getString(4);
                    Collector_Name = rsselect.getString(9);
                    Amount = rsselect.getString(10);
                    TP_Fee = rsselect.getString(11);
                    date = new Date();
                    logger.debug(dateFormat.format(date));
                    logger.debug("TermID: " + TermID);

                    out.println("<tr>");
                    out.println("<td>" + File_Date_Time + "</td>");
                    out.println("<td>" + Surname + "</td>");
                    out.println("<td>" + FirstName + "</td>");
                    out.println("<td>" + Collector_Name + "</td>");
                    out.println("<td>" + Amount + "</td>");
//out.println("<td>" + TP_Fee + "</td>");
//out.println("<hr> " + File_Date_Time + " " + Surname + " " + FirstName + " " + Collector_Name + " " + Amount + "  ");
                    out.println("</tr>");


                    //out.println("<hr> " + File_Date_Time + " " + Surname + " " + FirstName + " " + Collector_Name + " " + Amount + "  ");
                    //out.println("<hr><b>Surname:</b> \"" + Surname + "\"");
                    //out.println("<hr><b>FirstName:</b> \"" + FirstName + "\"");
                    //out.println("<hr><b>Collector_Name:</b> \"" + Collector_Name + "\"");
                    //out.println("<hr><b>Amount:</b> \"" + Amount + "\"");
                    // out.println("<hr><b>MembershipNo:</b> \"" + MembershipNo + "\"");
                    //out.println("<hr><b>UpdateQuery:</b> \"" + UpdateQuery + "\"");


                    //out.println("<hr><b>User authenticated:</b> ");
                    //out.println("<center><button type=\"button\" onclick =\"document.location.href = 'http://localhost:8084/PaymentSystemSwipe/secureUser/home.jsp'\">Continue</button></center> <br /> ");


                }

                out.println("</table></center>");

            /*out.println("<center><button type=\"button\" onclick =\"document.location.href = '/PaymentSystemSwipe/secureUser/home.jsp'\">Back to Main</button></center> <br />");
            out.println("<center><button type=\"button\" value=\"Back\" onclick=\"history.back()\">Back</button></center>");*/


            } catch (SQLException ex) {


                date = new Date();
                logger.fatal(dateFormat.format(date));
                logger.fatal("SQLException " + ex.getMessage());
                logger.fatal("Query:SELECT d.File_Date_Time, e.Acct_Ref_No,A.Surname, A.FirstName, A.Identity_No, D.EasyPay_No,D.PAN, D.Tender_Type, F. Collector_Name, D.Amount, D.TP_Fee FROM T_BP_Customers A, T_BP_Card B, T_EasyPay_No_Track C, T_EasyPay_Messages D, T_BP_Card_Beneficiaries E, T_EasyPay_Collectors F WHERE A.Identity_No = '" + IDNum + "' AND D.Beneficiary_no = '" + Beneficiary_No + "' AND A.BP_Cust_ID = B.BP_Cust_ID AND B.PAN = C.PAN AND B.PAN = E.PAN AND D.Beneficiary_no = E.Beneficiary_no AND C.EasyPay_No = D.EasyPay_No AND D.Collector_Identifier = F.Collector_Identifier Order by d.File_Date_Time desc");
                String TGResponseCode = "05";
                String TGResponseMessage = "Database Error";


            }


//Output "D","mm/dd/ccyy","yymm9",penalty.//or something close!!
//}
            //{
            if (File_Date_Time == "") {


                //out.println("<hr><b> User Login Failed!:</b> ");
                //out.println("<center><button type=\"button\" onclick =\"document.location.href = '/PaymentSystemSwipe/index.jsp'\">Back to Main</button></center> <br /> ");
                //out.println("<center><button type=\"button\" onclick =\"document.location.href = '/PaymentSystemSwipe/secureUser/home.jsp'\">Back to Main</button></center> <br />");

                //TermID = "";     
            }
            
            /*try
            {
                Statement stinsert = con.createStatement();
                date = new Date();
                logger.debug(dateFormat.format(date));
                logger.debug("Query: insert into T_AB_Card_Activations (Terminal_ID, Transaction_Type, Transaction_Date_Time, Envelope_Nr, PAN, RSP_Code, RSP_Message) values ('" + TermID +"', 'CIM', getdate(), '" + EnvelopeNumber + "', '" + PAN + "', '" + TGResponseCode + "', '" + TGResponseMessage + "')");
                stinsert.executeUpdate("insert into T_AB_Card_Activations (Terminal_ID, Transaction_Type, Transaction_Date_Time, Envelope_Nr, PAN, RSP_Code, RSP_Message) values ('" + TermID +"', 'CIM', getdate(), '" + EnvelopeNumber + "', '" + PAN + "', '" + TGResponseCode + "', '" + TGResponseMessage + "')");
            }
            catch(SQLException ex)
            {
                date = new Date();
                logger.fatal(dateFormat.format(date));
                logger.fatal("SQLException " + ex.getMessage());
                logger.fatal("With query: insert into T_AB_Card_Activations (Terminal_ID, Transaction_Type, Transaction_Date_Time, Envelope_Nr, PAN, RSP_Code, RSP_Message) values ('" + TermID +"', 'CIM', getdate(), '" + EnvelopeNumber + "', '" + PAN + "', '" + TGResponseCode + "', '" + TGResponseMessage + "')");
                String TGResponseCode = "01";
            }*/
         
             
                 /*out.println("<title>Main Screen</title>");
            out.println("</head>");
            out.println("<body>");

            //Display the report's name as a header within the body of the report:
            out.println("<h2><font color='red'><center>POS Setup result</center></font></h2>");
            

            out.println("<hr><b>IP:</b> \"" + IP + "\"");
            out.println("<hr><b>HostName:</b> \"" + HostName + "\"");
            out.println("<hr><b>IPRange:</b> \"" + IPRange + "\"");
            out.println("<hr><b>MembershipNo:</b> \"" + MembershipNo + "\"");
            //out.println("<hr><b>UpdateQuery:</b> \"" + UpdateQuery + "\"");

            
            //out.println("<center><button type=\"button\" onclick =\"document.location.href = 'https://localhost:8443/TranGate/'\">Back to Main</button></center> <br />");*/


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
