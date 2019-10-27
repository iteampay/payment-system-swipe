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

import java.io.*;


@WebServlet(name = "TotalProfitability", urlPatterns = {"/TotalProfitability"})
public class TotalProfitability extends HttpServlet {


    static Logger logger = Logger.getLogger(PaymentsReportAll.class);
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
            logger.info("Payments_Report_All accessed by: " + User);
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
            /*date = new Date();
            logger.debug(dateFormat.format(date));
            logger.debug("TerminalSerialNumber: " + TerminalSerialNumber);*/
            /*String surname = request.getParameter("Surname");
            String FirstName = request.getParameter("FirstName");
            String Password = request.getParameter("Password");*/


            String StartDate = request.getParameter("startdate");
            String EndDate = request.getParameter("enddate");

            String TermID = "";

            String File_Date_Time = "";
            String Customer_ID = "";
            String Surname = "";
            String FirstName = "";
            String IDNumber = "";
            String EasyPayNumber = "";
            String TouchPayNumber = "";
            String PaymentType = "";
            String Collector_Name = "";
            String Amount = "";
            String TP_Fee = "";
            String TxBalance = "";

            String TotalPaymentsAmount = "";
            String TotalPaymentsNumber = "";
            String TotalTPFee = "";
            String Balance = "";
            String VAT = "";
            String SettelmentAmount = "";

            String CashAmount = "";
            String ChequeAmount = "";
            String CreditCardAmount = "";
            String CreditEFTAmount = "";
            String OtherAmount = "";

            String CashNumber = "";
            String ChequeNumber = "";
            String CreditCardNumber = "";
            String CreditEFTNumber = "";
            String OtherNumber = "";

            String CashPercentage = "";
            String ChequePercentage = "";
            String CreditCardPercentage = "";
            String CreditEFTPercentage = "";
            String OtherPercentage = "";
            String TotalUnsettled = "";
            String Paymentamount = "";
            String touchpayfee = "";
            String SettledAmount = "";
            String FuneralParlour = "";
            String Totalcards = "";
            String totalpayments = "";
            String totaleasypay = "";
            String Bankcost = "";
            String OtherFees = "";
            String Subtotal = "";
            String Profit = "";

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<link rel=\"stylesheet\" href=\"/PaymentSystemSwipe/" + Beneficiary_Name + "/css/style.css\" type=\"text/css\"/>");
            out.println("<head>");
            out.println("<link rel=\"icon\" type=\"/PaymentSystemSwipe/" + Beneficiary_Name + "/img/Icon.ico\"></link>");
            out.println("<link rel=\"shortcut icon\" href=\"/PaymentSystemSwipe/" + Beneficiary_Name + "/img/Icon.ico\"></link>");
            out.println("<title>Payment History</title>");
            out.println("</head>");
            out.println("<body>");

            try {
                //String file_name = "C:\\Temp\\Report.txt";
                //String file_name = "C:\\Software Develoment\\PaymentSystemSwipe\\PaymentSystemSwipe\\build\\web\\Report.csv";
                String file_name = "C:\\Program Files\\Apache Software Foundation\\Apache Tomcat 7.0.14\\webapps\\PaymentSystemSwipe\\Report.csv";
                File file = new File(file_name);
                /*boolean exist = */
                file.createNewFile();
                FileWriter fstream = new FileWriter(file_name);
                BufferedWriter outfile = new BufferedWriter(fstream);


                try {
                    //DISPLAYING TOTALS

                    Statement stselect = Utils.TGDBcon.createStatement();
                    date = new Date();
                    logger.debug(dateFormat.format(date));
                    logger.debug("Query:SELECT SUM(Amount)FROM T_EasyPay_Messages ");
                    ResultSet rsselect = stselect.executeQuery("Query:SELECT SUM(Amount)FROM T_EasyPay_Messages ");

                    while (rsselect.next()) {
                        totalpayments = rsselect.getString(1);
                        date = new Date();
                        logger.debug(dateFormat.format(date));
                        logger.debug("Total Payments: " + totalpayments);
                    }


                    //need to change to a correct no
                    logger.debug("Query:SELECT COUNT(*)FROM T_EasyPay_Messages");
                    rsselect = stselect.executeQuery("Query:SELECT COUNT(*)FROM T_EasyPay_Messages ");

                    while (rsselect.next()) {
                        TotalPaymentsNumber = rsselect.getString(1);
                        date = new Date();
                        logger.debug(dateFormat.format(date));
                        logger.debug("TotalPaymentsNumber: " + TotalPaymentsNumber);
                    }

                    if (TotalPaymentsNumber.equals("0")) {
                        TotalPaymentsAmount = "0";
                        date = new Date();
                        logger.debug(dateFormat.format(date));
                        logger.debug("TotalPaymentsAmount: " + totalpayments);
                    }

                    //Total easypay fees
                    logger.debug("Query:SELECT SUM(EP_Fee)FROM T_EasyPay_Messages WHERE DATENAME(MONTH,File_Date_Time)= 'July'AND DATENAME(YEAR,File_Date_Time)= '2013'");
                    rsselect = stselect.executeQuery("Query:SELECT SUM(EP_Fee)FROM T_EasyPay_Messages ");

                    while (rsselect.next()) {
                        totaleasypay = rsselect.getString(1);
                        date = new Date();
                        logger.debug(dateFormat.format(date));
                        logger.debug("total Easypay: " + totaleasypay);
                    }


                    //Total easypay fees
                    logger.debug("Query:SELECT SUM(EP_Fee)FROM T_EasyPay_Messages WHERE DATENAME(MONTH,File_Date_Time)= 'July'AND DATENAME(YEAR,File_Date_Time)= '2013'");
                    rsselect = stselect.executeQuery("Query:SELECT SUM(EP_Fee)FROM T_EasyPay_Messages ");

                    while (rsselect.next()) {
                        totaleasypay = rsselect.getString(1);
                        date = new Date();
                        logger.debug(dateFormat.format(date));
                        logger.debug("total Easypay: " + totaleasypay);
                    }
                    //total Bank cost
                    logger.debug("Query:SELECT SUM(A.Bankcost)FROM T_EasyPay_Messages A, T_BP_Beneficiaries B WHERE a.Settled = 'N' AND a.beneficiary_no = b.beneficiary_no AND A.Deleted = 'N' AND B.Split = 1 ");
                    rsselect = stselect.executeQuery("Query:SELECT SUM(Bank_cost)FROM T_EasyPay_Messages");

                    while (rsselect.next()) {
                        Bankcost = rsselect.getString(1);
                        date = new Date();
                        logger.debug(dateFormat.format(date));
                        logger.debug("Bank Cost: " + Bankcost);
                    }

                    //Total other cost
                    logger.debug("Query:SELECT SUM(A.Other_Fee)FROM T_EasyPay_Messages A, T_BP_Beneficiaries B WHERE a.Settled = 'N' AND a.beneficiary_no = b.beneficiary_no AND A.Deleted = 'N' AND B.Split = 1 ");
                    rsselect = stselect.executeQuery("Query:SELECT SUM(Other_Fee)FROM T_EasyPay_Messages ");

                    while (rsselect.next()) {
                        OtherFees = rsselect.getString(1);
                        date = new Date();
                        logger.debug(dateFormat.format(date));
                        logger.debug("Other Fee: " + OtherFees);
                    }

                    //SUBTOTAL
                    Subtotal = String.valueOf(Double.parseDouble(totaleasypay) + Double.parseDouble(Bankcost) + Double.parseDouble(OtherFees));

                    logger.debug("Query:SELECT SUM(TP_Fee)FROM T_EasyPay_Messages WHERE DATENAME(MONTH,File_Date_Time)= 'July' AND DATENAME(YEAR,File_Date_Time)= '2013'  ");
                    rsselect = stselect.executeQuery("Query:SELECT SUM(TP_Fee)FROM T_EasyPay_Messages ");

                    while (rsselect.next()) {
                        TotalTPFee = rsselect.getString(1);
                        date = new Date();
                        logger.debug(dateFormat.format(date));
                        logger.debug("Total TPFee: " + TotalTPFee);
                    }

                    VAT = String.valueOf(Double.parseDouble(TotalTPFee) * 0.13);
                    Profit = String.valueOf(Double.parseDouble(TotalTPFee) - Double.parseDouble(Subtotal));
                /*Balance = String.valueOf(Double.parseDouble(TotalTPFee) - Double.parseDouble(TotalTPFee));
                date = new Date();
                logger.debug(dateFormat.format(date));
                logger.debug("Balance: " + Balance);
                    
                VAT = String.valueOf(Double.parseDouble(TotalTPFee) * 0.14);
                date = new Date();
                logger.debug(dateFormat.format(date));
                logger.debug("VAT: " + VAT);

                SettelmentAmount = String.valueOf(Double.parseDouble(Balance) - Double.parseDouble(VAT));
                date = new Date();
                logger.debug(dateFormat.format(date));
                logger.debug("SettelmentAmount: " + SettelmentAmount);*/


                    logger.debug("Query:SELECT SUM(Amount)FROM T_EasyPay_Messages WHERE DATENAME(MONTH,File_Date_Time)= 'July'AND DATENAME(YEAR,File_Date_Time)= '2013'AND Tender_Type = 'Cash'    ");
                    rsselect = stselect.executeQuery("Query:SELECT SUM(Amount)FROM T_EasyPay_Messages WHERE  Tender_Type = 'Cash'  ");

                    while (rsselect.next()) {
                        CashAmount = rsselect.getString(1);
                        date = new Date();
                        logger.debug(dateFormat.format(date));
                        logger.debug("CashAmount: " + CashAmount);
                    }


                    logger.debug("Query:select count(*) FROM T_EasyPay_Messages WHERE DATENAME(MONTH,File_Date_Time)= 'July'AND DATENAME(YEAR,File_Date_Time)= '2013'AND Tender_Type = 'Cash' ");
                    rsselect = stselect.executeQuery("Query:select count(*) FROM T_EasyPay_Messages WHERE Tender_Type = 'Cash' ");

                    while (rsselect.next()) {
                        CashNumber = rsselect.getString(1);
                        date = new Date();
                        logger.debug(dateFormat.format(date));
                        logger.debug("CashNumber: " + CashNumber);
                    }

                    if (CashNumber.equals("0")) {
                        CashAmount = "0";
                        date = new Date();
                        logger.debug(dateFormat.format(date));
                        logger.debug("CashAmount: " + CashAmount);
                    }

                    //*CashPercentage = String.valueOf(Double.parseDouble(CashAmount) / Double.parseDouble(TotalPaymentsAmount) * 100);
                    date = new Date();
                    logger.debug(dateFormat.format(date));
                    logger.debug("CashPercentage: " + CashPercentage);

                    logger.debug("Query:select ltrim(rtrim(str(sum(Amount), 13, 2))) from T_EasyPay_Messages where File_Date_Time >= '" + StartDate + "' and File_Date_Time <= '" + EndDate + "' and Beneficiary_no = '" + Beneficiary_No + "' and Tender_Type = 'Cheque'");
                    rsselect = stselect.executeQuery("Query:select ltrim(rtrim(str(sum(Amount), 13, 2))) FROM T_EasyPay_Messages WHERE Tender_Type = 'Cheque' ");

                    while (rsselect.next()) {
                        ChequeAmount = rsselect.getString(1);
                        date = new Date();
                        logger.debug(dateFormat.format(date));
                        logger.debug("ChequeAmount: " + ChequeAmount);
                    }

                    logger.debug("Query:select count(*) from T_EasyPay_Messages where File_Date_Time >= '" + StartDate + "' and File_Date_Time <= '" + EndDate + "' and Beneficiary_no = '" + Beneficiary_No + "' and Tender_Type = 'Cheque'");
                    rsselect = stselect.executeQuery("Query:select count(*)  FROM T_EasyPay_Messages WHERE Tender_Type = 'Cheque' ");

                    while (rsselect.next()) {
                        ChequeNumber = rsselect.getString(1);
                        date = new Date();
                        logger.debug(dateFormat.format(date));
                        logger.debug("ChequeNumber: " + ChequeNumber);
                    }

                    if (ChequeNumber.equals("0")) {
                        ChequeAmount = "0";
                        date = new Date();
                        logger.debug(dateFormat.format(date));
                        logger.debug("ChequeAmount: " + ChequeAmount);
                    }

                    //*ChequePercentage = String.valueOf(Double.parseDouble(ChequeAmount) / Double.parseDouble(TotalPaymentsAmount) * 100);
                    date = new Date();
                    logger.debug(dateFormat.format(date));
                    logger.debug("ChequePercentage: " + ChequePercentage);

                    logger.debug("Query:select ltrim(rtrim(str(sum(Amount), 13, 2))) from T_EasyPay_Messages where File_Date_Time >= '" + StartDate + "' and File_Date_Time <= '" + EndDate + "' and Beneficiary_no = '" + Beneficiary_No + "' and Tender_Type = 'CreditCard'");
                    rsselect = stselect.executeQuery("Query:select ltrim(rtrim(str(sum(Amount), 13, 2))) FROM T_EasyPay_Messages WHERE Tender_Type = 'CreditCard' ");

                    while (rsselect.next()) {
                        CreditCardAmount = rsselect.getString(1);
                        date = new Date();
                        logger.debug(dateFormat.format(date));
                        logger.debug("CreditCardAmount: " + CreditCardAmount);
                    }

                    logger.debug("Query:select count(*) from T_EasyPay_Messages where File_Date_Time >= '" + StartDate + "' and File_Date_Time <= '" + EndDate + "' and Beneficiary_no = '" + Beneficiary_No + "' and Tender_Type = 'CreditCard'");
                    rsselect = stselect.executeQuery("Query:select count(*) FROM T_EasyPay_Messages WHERE Tender_Type = 'CreditCard' ");

                    while (rsselect.next()) {
                        CreditCardNumber = rsselect.getString(1);
                        date = new Date();
                        logger.debug(dateFormat.format(date));
                        logger.debug("CreditCardNumber: " + CreditCardNumber);
                    }

                    if (CreditCardNumber.equals("0")) {
                        CreditCardAmount = "0";
                        date = new Date();
                        logger.debug(dateFormat.format(date));
                        logger.debug("CreditCardAmount: " + CreditCardAmount);
                    }

                    //*CreditCardPercentage = String.valueOf(Double.parseDouble(CreditCardAmount) / Double.parseDouble(TotalPaymentsAmount) * 100);
                    date = new Date();
                    logger.debug(dateFormat.format(date));
                    logger.debug("CreditCardPercentage: " + CreditCardPercentage);

                    logger.debug("Query:select ltrim(rtrim(str(sum(Amount), 13, 2))) from T_EasyPay_Messages where File_Date_Time >= '" + StartDate + "' and File_Date_Time <= '" + EndDate + "' and Beneficiary_no = '" + Beneficiary_No + "' and (Tender_Type = 'CreditEFT' or Tender_Type = 'EFT')");
                    rsselect = stselect.executeQuery("Query:select ltrim(rtrim(str(sum(Amount), 13, 2))) FROM T_EasyPay_Messages (Tender_Type = 'CreditEFT' or Tender_Type = 'EFT' )");

                    while (rsselect.next()) {
                        CreditEFTAmount = rsselect.getString(1);
                        date = new Date();
                        logger.debug(dateFormat.format(date));
                        logger.debug("CreditEFTAmount: " + CreditEFTAmount);
                    }

                    logger.debug("Query:select count(*) from T_EasyPay_Messages where File_Date_Time >= '" + StartDate + "' and File_Date_Time <= '" + EndDate + "' and Beneficiary_no = '" + Beneficiary_No + "' and (Tender_Type = 'CreditEFT' or Tender_Type = 'EFT')");
                    rsselect = stselect.executeQuery("Query:select count(*) FROM T_EasyPay_Messages WHERE (Tender_Type = 'CreditEFT' or Tender_Type = 'EFT') ");

                    while (rsselect.next()) {
                        CreditEFTNumber = rsselect.getString(1);
                        date = new Date();
                        logger.debug(dateFormat.format(date));
                        logger.debug("CreditEFTNumber: " + CreditEFTNumber);
                    }

                    if (CreditEFTNumber.equals("0")) {
                        CreditEFTAmount = "0";
                        date = new Date();
                        logger.debug(dateFormat.format(date));
                        logger.debug("CreditEFTAmount: " + CreditEFTAmount);
                    }

                    //*CreditEFTPercentage = String.valueOf(Double.parseDouble(CreditEFTAmount) / Double.parseDouble(TotalPaymentsAmount) * 100);
                    date = new Date();
                    logger.debug(dateFormat.format(date));
                    logger.debug("CreditEFTPercentage: " + CreditEFTPercentage);

                    logger.debug("Query:select ltrim(rtrim(str(sum(Amount), 13, 2))) from T_EasyPay_Messages where File_Date_Time >= '" + StartDate + "' and File_Date_Time <= '" + EndDate + "' and Beneficiary_no = '" + Beneficiary_No + "' and Tender_Type = 'Other'");
                    rsselect = stselect.executeQuery("Query:select ltrim(rtrim(str(sum(Amount), 13, 2))) FROM T_EasyPay_Messages WHERE DATENAME(MONTH,File_Date_Time)= 'July'AND DATENAME(YEAR,File_Date_Time)= '2013'AND Tender_Type = 'Other' ");

                    while (rsselect.next()) {
                        OtherAmount = rsselect.getString(1);
                        date = new Date();
                        logger.debug(dateFormat.format(date));
                        logger.debug("OtherAmount: " + OtherAmount);
                    }

                    logger.debug("Query:select count(*) from T_EasyPay_Messages where File_Date_Time >= '" + StartDate + "' and File_Date_Time <= '" + EndDate + "' and Beneficiary_no = '" + Beneficiary_No + "' and Tender_Type = 'Other'");
                    rsselect = stselect.executeQuery("Query:select count(*) FROM T_EasyPay_Messages WHERE DATENAME(MONTH,File_Date_Time)= 'July'AND DATENAME(YEAR,File_Date_Time)= '2013'AND Tender_Type = 'Other' ");

                    while (rsselect.next()) {
                        OtherNumber = rsselect.getString(1);
                        date = new Date();
                        logger.debug(dateFormat.format(date));
                        logger.debug("OtherNumber: " + OtherNumber);
                    }

                    if (OtherNumber.equals("0")) {
                        OtherAmount = "0";
                        date = new Date();
                        logger.debug(dateFormat.format(date));
                        logger.debug("OtherAmount: " + OtherAmount);
                    }

                    //* = String.valueOf(Double.parseDouble(OtherAmount) / Double.parseDouble(TotalPaymentsAmount) * 100);
                    date = new Date();
                    logger.debug(dateFormat.format(date));
                    logger.debug("CashPercentage: " + CashPercentage);

                } catch (SQLException ex) {
                    date = new Date();
                    logger.fatal(dateFormat.format(date));
                    logger.fatal("SQLException " + ex.getMessage());
                    logger.fatal("Query:select count(*) from T_EasyPay_Messages where File_Date_Time >= '" + StartDate + "' and File_Date_Time <= '" + EndDate + "' and Beneficiary_no = '" + Beneficiary_No + "'");
                }


                try {
                    Statement stselect = Utils.TGDBcon.createStatement();
                    date = new Date();
                    logger.debug(dateFormat.format(date));
                    logger.debug("Query:SELECT b.Beneficiary_Name, COUNT(*) FROM T_BP_Card_Beneficiaries a, T_BP_Beneficiaries b WHERE a.Beneficiary_No = b.Beneficiary_No Group by b.Beneficiary_Name ORDER by b.Beneficiary_Name  ");
                    //logger.debug("Query:select convert(varchar, d.File_Date_Time, 20), e.Acct_Ref_No, A.Surname, A.FirstName, A.Identity_No, D.EasyPay_No, D.PAN, D.Tender_Type, F.Collector_Name, D.Amount, D.TP_Fee from T_BP_Customers A, T_BP_Card B, T_EasyPay_No_Track C, T_EasyPay_Messages D, T_BP_Card_Beneficiaries E, T_EasyPay_Collectors F where d.File_Date_Time >= '" + StartDate + "' and d.File_Date_Time <= '" + EndDate + "' and D.Beneficiary_no = '" + Beneficiary_No + "' and A.BP_Cust_ID = B.BP_Cust_ID and B.PAN = C.PAN and B.PAN = E.PAN and D.Beneficiary_no = E.Beneficiary_no and C.EasyPay_No = D.EasyPay_No and D.Collector_Identifier = F.Collector_Identifier and D.Deleted = 'N' order by d.File_Date_Time, A.Surname");
                    //logger.debug("Query:SELECT d.File_Date_Time, e.Acct_Ref_No,A.Surname, A.FirstName, A.Identity_No, D.EasyPay_No,D.PAN, D.Tender_Type, F. Collector_Name, D.Amount, D.TP_Fee FROM T_BP_Customers A, T_BP_Card B, T_EasyPay_No_Track C, T_EasyPay_Messages D, T_BP_Card_Beneficiaries E, T_EasyPay_Collectors F WHERE A.Identity_No = '" + IDNum + "' AND D.Beneficiary_no = '" + Beneficiary_No + "' AND A.BP_Cust_ID = B.BP_Cust_ID AND B.PAN = C.PAN AND B.PAN = E.PAN AND D.Beneficiary_no = E.Beneficiary_no AND C.EasyPay_No = D.EasyPay_No AND D.Collector_Identifier = F.Collector_Identifier Order by d.File_Date_Time desc");
                    //ResultSet rsselect = stselect.executeQuery("select POSSerialNo, MerchantNo from T_Map_Pos where BranchIPRange = '" + IPRange + "'");
                    //ResultSet rsselect = stselect.executeQuery("Query: select Beneficiary_no, User_name, PIN from T_Web_users where PIN = '" + Password + "'");
                    //ResultSet rsselect = stselect.executeQuery("Query:SELECT d.File_Date_Time, e.Acct_Ref_No,A.Surname, A.FirstName, A.Identity_No, D.EasyPay_No,D.PAN, D.Tender_Type, F. Collector_Name, D.Amount, D.TP_Fee FROM T_BP_Customers A, T_BP_Card B, T_EasyPay_No_Track C, T_EasyPay_Messages D, T_BP_Card_Beneficiaries E, T_EasyPay_Collectors F WHERE A.Identity_No = '" + IDNum + "' AND D.Beneficiary_no = '" + Beneficiary_No + "' AND A.BP_Cust_ID = B.BP_Cust_ID AND B.PAN = C.PAN AND B.PAN = E.PAN AND D.Beneficiary_no = E.Beneficiary_no AND C.EasyPay_No = D.EasyPay_No AND D.Collector_Identifier = F.Collector_Identifier Order by d.File_Date_Time desc");
                    ResultSet rsselect = stselect.executeQuery("Query:SELECT b.Beneficiary_Name, COUNT(*) FROM T_BP_Card_Beneficiaries a, T_BP_Beneficiaries b WHERE a.Beneficiary_No = b.Beneficiary_No Group by b.Beneficiary_Name ORDER by b.Beneficiary_Name ");


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
                    out.println("			<li><a href=\"/PaymentSystemSwipe/secureAdmin/Members.jsp\">Current Members</a></li>");
                    out.println("			<li class=\"first\"><a href=\"/PaymentSystemSwipe/secureUser/Report.jsp\" >Report</a></li>");
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
                    //out.println("					<h3>Payment History for ID: " + IDNum + "</h3>");
                    out.println("					<h3>Total Profitability</h3>");


//outfile.write("Payment report from: " + StartDate + " to: " + EndDate + "\r\n");
                    out.println("<center>Right click <a href=\"/PaymentSystemSwipe/Report.csv\">here</a> and choose save as to save this report as .csv file.</center>");

                    out.println("<center><table border=\"0\" cellpadding=\"10\" cellspacing=\"10\" ;>");//table1
                    out.println("<tr>");
                    out.println("<td valign=top>");

                    out.println("<center><table border=\"1\" valign=top>");//table 2
                    out.println("<tr>");
                    out.println("<th>Description</th>");
                    outfile.write("Description,");
                    out.println("<th>Amount</th>");
                    outfile.write("Amount,");
                    out.println("<th>Number</th>");
                    outfile.write("Number\r\n");
                    out.println("</tr>");

                    out.println("<tr>");
                    out.println("<td>Total Payments</td>");
                    outfile.write("Total Payments,");
                    out.println("<td>" + totalpayments + "</td>");
                    outfile.write(totalpayments + ",");
                    out.println("<td>" + TotalPaymentsNumber + "</td>");
                    outfile.write(TotalPaymentsNumber + "\r\n");
                    out.println("</tr>");

                    out.println("<tr>");
                    out.println("<td>Total EasypayFees</td>");
                    outfile.write("Total EasypayFees,");
                    out.println("<td>" + totaleasypay + "</td>");
                    outfile.write(totaleasypay + ",");

                    out.println("</tr>");

                    out.println("<tr>");
                    out.println("<td>Total Bank cost</td>");
                    outfile.write("Total Bank cost,");
                    out.println("<td>" + Bankcost + "</td>");
                    outfile.write(Bankcost + ",");

                    out.println("</tr>");
                    out.println("<tr>");


                    out.println("<td>Total Other Fees</td>");
                    outfile.write("Total Other Fees,");
                    out.println("<td>" + OtherFees + "</td>");
                    outfile.write(OtherFees + ",");
                    out.println("</tr>");

                    out.println("<tr>");
                    out.println("<td>Sub Total </td>");
                    outfile.write("Sub Total,");
                    out.println("<td>" + Subtotal + "</td>");
                    outfile.write(Subtotal + ",");


                    out.println("<tr>");
                    out.println("<td>Total touchpay Fees</td>");
                    outfile.write("Total touchpay Fees,");
                    out.println("<td>" + TotalTPFee + "</td>");
                    outfile.write(TotalTPFee + ",");
                    out.println("</tr>");


                    out.println("<tr>");
                    out.println("<td>VAT</td>");
                    outfile.write("VAT,");
                    out.println("<td>" + VAT + "</td>");
                    outfile.write(VAT + ",");
                    out.println("</tr>");

                    out.println("<tr>");
                    out.println("<td>Profit</td>");
                    outfile.write("Profit,");
                    out.println("<td>" + Profit + "</td>");
                    outfile.write(Profit + ",");
                    out.println("</tr>");


                    out.println("</tr>");

                    
/*out.println("<tr>");
out.println("<td>Total TouchPay Fee</td>");
outfile.write("Total TouchPay Fee,");
out.println("<td>" + TotalTPFee + "</td>");
outfile.write(TotalTPFee + "\r\n");
out.println("<td></td>");
out.println("</tr>");

out.println("<tr>");
out.println("<td>Balance</td>");
outfile.write("Balance,");
out.println("<td>" + Balance + "</td>");
outfile.write(Balance + "\r\n");
out.println("<td></td>");
out.println("</tr>");

out.println("<tr>");
out.println("<td>VAT</td>");
outfile.write("VAT,");
out.println("<td>" + VAT + "</td>");
outfile.write(VAT + "\r\n");
out.println("<td></td>");
out.println("</tr>");

out.println("<tr>");
out.println("<td>Settelment Amount</td>");
outfile.write("Settelment Amount,");
out.println("<td>" + SettelmentAmount + "</td>");
outfile.write(SettelmentAmount + "\r\n");
out.println("<td></td>");
out.println("</tr>");*/

                    out.println("</table></center>");

                    out.println("</td>");
                    out.println("<td valign=top>");

                    out.println("<center><table border=\"1\">");
                    out.println("<tr>");
                    out.println("<th>Payment Method</th>");
                    outfile.write("Payment Method,");
                    out.println("<th>Amount</th>");
                    outfile.write("Amount,");
                    out.println("<th>Number</th>");
                    outfile.write("Number,");
                    out.println("<th>Percentage (Amount)</th>");
                    outfile.write("Percentage (Amount)\r\n");
                    out.println("</tr>");

                    out.println("<tr>");
                    out.println("<td>Cash</td>");
                    outfile.write("Cash,");
                    out.println("<td>" + CashAmount + "</td>");
                    outfile.write(CashAmount + ",");
                    out.println("<td>" + CashNumber + "</td>");
                    outfile.write(CashNumber + ",");
                    out.println("<td>" + CashPercentage + "</td>");
                    outfile.write(CashPercentage + "\r\n");
                    out.println("</tr>");

                    out.println("<tr>");
                    out.println("<td>Cheque</td>");
                    outfile.write("Cheque,");
                    out.println("<td>" + ChequeAmount + "</td>");
                    outfile.write(ChequeAmount + ",");
                    out.println("<td>" + ChequeNumber + "</td>");
                    outfile.write(ChequeNumber + ",");
                    out.println("<td>" + ChequePercentage + "</td>");
                    outfile.write(ChequePercentage + "\r\n");
                    out.println("</tr>");

                    out.println("<tr>");
                    out.println("<td>Credit Card</td>");
                    outfile.write("Credit Card,");
                    out.println("<td>" + CreditCardAmount + "</td>");
                    outfile.write(CreditCardAmount + ",");
                    out.println("<td>" + CreditCardNumber + "</td>");
                    outfile.write(CreditCardNumber + ",");
                    out.println("<td>" + CreditCardPercentage + "</td>");
                    outfile.write(CreditCardPercentage + "\r\n");
                    out.println("</tr>");

                    out.println("<tr>");
                    out.println("<td>Credit EFT</td>");
                    outfile.write("Credit EFT,");
                    out.println("<td>" + CreditEFTAmount + "</td>");
                    outfile.write(CreditEFTAmount + ",");
                    out.println("<td>" + CreditEFTNumber + "</td>");
                    outfile.write(CreditEFTNumber + ",");
                    out.println("<td>" + CreditEFTPercentage + "</td>");
                    outfile.write(CreditEFTPercentage + "\r\n");
                    out.println("</tr>");

                    out.println("<tr>");
                    out.println("<td>Other</td>");
                    outfile.write("Other,");
                    out.println("<td>" + OtherAmount + "</td>");
                    outfile.write(OtherAmount + ",");
                    out.println("<td>" + OtherNumber + "</td>");
                    outfile.write(OtherNumber + ",");
                    out.println("<td>" + OtherPercentage + "</td>");
                    outfile.write(OtherPercentage + "\r\n");
                    out.println("</tr>");

                    out.println("</table></center>");

/*out.println("</td>");
out.println("</tr>");
out.println("</table></center>");*/

                    out.println("<td>");

                    out.println("<center><table border=\"1\" >");//table 3
                    out.println("<tr>");
                    out.println("<th>Funeral Parlour</th>");
                    outfile.write("Funeral Parlour,");
                    out.println("<th>Total Cards</th>");
                    outfile.write("Total Cards,");
/*out.println("<th>Payment Amount</th>");
outfile.write("Payment Amount,");
out.println("<th>Touchpay Fee</th>");
outfile.write("Touchpay Fee,");
out.println("<th>VAT</th>");
outfile.write("VAT,");
out.println("<th>Balance</th>");
outfile.write("Balance,");
out.println("<th>Settlement Amount</th>");
outfile.write("Settlement Amount,");*/
//out.println("<th>EasyPay Number</th>");
//outfile.write("EasyPay Number,");
//out.println("<th>TouchPay Number</th>");
//outfile.write("TouchPay Number,");
//out.println("<th>Settlement Amount</th>");
//outfile.write("Settlement Amount,");
//out.println("<th>Payment Location</th>");
////outfile.write("Payment Location,");
//out.println("<th>Payment Amount</th>");
//outfile.write("Payment Amount,");
//out.println("<th>TouchPay Fee</th>");
//outfile.write("TouchPay Fee,");
//out.println("<th>Balance</th>");
//outfile.write("Balance\r\n");
                    outfile.write("\r\n");
                    out.println("</tr>");

                    while (rsselect.next()) {
                        FuneralParlour = rsselect.getString(1);
                        Totalcards = rsselect.getString(2);
       /*Paymentamount = rsselect.getString(6); 
       touchpayfee = rsselect.getString(7);  
       VAT = String.valueOf(Double.parseDouble(touchpayfee) * 0.13);
       Balance = String.valueOf(Double.parseDouble(Paymentamount) - Double.parseDouble(touchpayfee));
       SettledAmount = String.valueOf(Double.parseDouble(Balance) - Double.parseDouble(VAT));*/


                        out.println("<tr>");
                        out.println("<td>" + FuneralParlour + "</td>");
                        outfile.write(FuneralParlour + ",");
                        out.println("<td>" + Totalcards + "</td>");
                        outfile.write(Totalcards + ",");
        /*out.println("<td>" + Paymentamount + "</td>");
        outfile.write(Paymentamount + ",");
        out.println("<td>" + touchpayfee + "</td>");
        outfile.write(touchpayfee + ",");
        out.println("<td>" + VAT + "</td>");
        outfile.write(VAT + ",");
        out.println("<td>" + Balance + "</td>");
        outfile.write(Balance + ",");
        out.println("<td>" + SettledAmount + "</td>");
        outfile.write(SettledAmount + ",");*/
        /*out.println("<td>" + EasyPayNumber + "</td>");
        outfile.write(EasyPayNumber + ",");*/
        /*out.println("<td>" + TouchPayNumber + "</td>");
        outfile.write(TouchPayNumber + ",");
        out.println("<td>" + PaymentType + "</td>");
        outfile.write(PaymentType + ",");
        out.println("<td>" + Collector_Name + "</td>");
        outfile.write(Collector_Name + ",");
        out.println("<td>" + Amount + "</td>");*/
                        outfile.write(Amount + ",");
        /*out.println("<td>" + TP_Fee + "</td>");
        outfile.write(TP_Fee + ",");*/
        /*out.println("<td>" + TxBalance + "</td>");
        outfile.write(TxBalance + "\r\n");*/
                        outfile.write("\r\n");
                        out.println("</tr>");
                    }

                    out.println("</table></center>");

                    out.println("</td>");
                    out.println("</tr>");
                    out.println("</table></center>");


                    out.println("<center><button type=\"button\" onclick =\"document.location.href = '/PaymentSystemSwipe/secureUser/home.jsp'\">Back to Main</button></center> <br />");
                    out.println("<center><button type=\"button\" value=\"Back\" onclick=\"history.back()\">Back</button></center>");


                } catch (SQLException ex) {
                    date = new Date();
                    logger.fatal(dateFormat.format(date));
                    logger.fatal("SQLException " + ex.getMessage());
                    logger.fatal("Query:select convert(varchar, d.File_Date_Time, 20), e.Acct_Ref_No, A.Surname, A.FirstName, A.Identity_No, D.EasyPay_No, D.PAN, D.Tender_Type, F.Collector_Name, D.Amount, D.TP_Fee from T_BP_Customers A, T_BP_Card B, T_EasyPay_No_Track C, T_EasyPay_Messages D, T_BP_Card_Beneficiaries E, T_EasyPay_Collectors F where d.File_Date_Time >= '" + StartDate + "' and d.File_Date_Time <= '" + EndDate + "' and D.Beneficiary_no = '" + Beneficiary_No + "' and A.BP_Cust_ID = B.BP_Cust_ID and B.PAN = C.PAN and B.PAN = E.PAN and D.Beneficiary_no = E.Beneficiary_no and C.EasyPay_No = D.EasyPay_No and D.Collector_Identifier = F.Collector_Identifier and D.Deleted = 'N' order by d.File_Date_Time, A.Surname");
                    String TGResponseCode = "05";
                    String TGResponseMessage = "Database Error";
                }

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

                outfile.close();
            } catch (Exception ex) {
                date = new Date();
                logger.fatal(dateFormat.format(date));
                logger.fatal("Exception " + ex.getMessage());
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
