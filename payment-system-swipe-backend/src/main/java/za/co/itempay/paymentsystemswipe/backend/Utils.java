/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.itempay.paymentsystemswipe.backend;

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
public class Utils {

    static Logger logger = Logger.getLogger(Utils.class);
    int startlog = StartLogging("C:/Program Files/TranGate/TranGateQSP/classes/log4j-PaymentSystemSwipe.xml", 5000);
    public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static DateFormat filedateFormat = new SimpleDateFormat("yyyy-MM-dd HHmmss");
    public static Date date = new Date();
    public static Connection TGDBcon = GetSQLServerDataBaseConnection();

    public static void Startup() {
        StartLogging("C:/Program Files/TranGate/TranGateQSP/classes/log4j-PaymentSystemSwipe.xml", 5000);
        TGDBcon = GetSQLServerDataBaseConnection();
    }


    public static int StartLogging(String FileName, int Size) {
        int Result = 0;

        File file = new File("C:/Program Files/TranGate/TranGateQSP/logfiles/PaymentSystemSwipe.htm");
        date = new Date();
        File file2 = new File("C:/Program Files/TranGate/TranGateQSP/logfiles/PaymentSystemSwipe Backup" + filedateFormat.format(date) + ".htm");
        boolean success = file.renameTo(file2);

        try {
            DOMConfigurator.configureAndWatch(FileName, Size);
            date = new Date();
            logger.info(dateFormat.format(date));
            logger.info("Log Started");
        } catch (Exception Ex) {
            date = new Date();
            logger.fatal(dateFormat.format(date));
            logger.fatal(Ex.getMessage());
            Result = 1;
        }
        return (Result);
    }

    public static Connection GetSQLServerDataBaseConnection() {
        Connection TGcon = null;
        try {
            Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
        } catch (ClassNotFoundException e1) {
            logger.fatal("ClassNotFoundException: " + e1.getMessage());
            date = new Date();
            logger.error(dateFormat.format(date));
        }
        try {
            // String url = "jdbc:microsoft:sqlserver://Touch2:1433;DatabaseName=TranGateDBSAAB";
            String url = "jdbc:microsoft:sqlserver://Touch2:1433;DatabaseName=TrangateDBwebpayments";

            Properties props = new Properties();
            //try retrieve data from file
            try {
                props.load(new FileInputStream("C:/Program Files/TranGate/TranGateQSP/classes/DBServer.properties"));
                // assign value to message variable only if it is not null

                if (props.getProperty("FuneralURL") != null) {
                    url = props.getProperty("FuneralURL");
                }
            }
            //catch exception in case properties file does not exist
            catch (IOException e) {
                //System.err.println("IO Exception: " + e.getMessage());
                date = new Date();
                logger.error(dateFormat.format(date));
                logger.error("IO Exception: " + e.getMessage());
            }
            //log to TranGate Monitor
            TGcon = DriverManager.getConnection(url, "trangate", "trangate");
        } catch (SQLException ex) {
            date = new Date();
            logger.error(dateFormat.format(date));
            logger.error("SQLException: " + ex.getMessage());
        }
        logger.info("Database Connection Started");
        return TGcon;
    }


}
