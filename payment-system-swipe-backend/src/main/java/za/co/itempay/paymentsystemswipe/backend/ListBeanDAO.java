/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.itempay.paymentsystemswipe.backend;

import javax.naming.*;
import javax.sql.*;
import java.sql.*;
import java.util.*;

import java.sql.*;

import org.apache.log4j.*;
import org.apache.log4j.xml.DOMConfigurator;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.io.*;


public class ListBeanDAO {

    static Logger logger = Logger.getLogger(ListBeanDAO.class);
    public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static DateFormat filedateFormat = new SimpleDateFormat("yyyy-MM-dd HHmmss");
    public static Date date = new Date();

    private List<ListBeanDTO> stateList;

    public ListBeanDAO() {
    }

    public List<ListBeanDTO> getStateList() {
        stateList = new ArrayList<ListBeanDTO>();


        try {
            Statement stselect = Utils.TGDBcon.createStatement();
            date = new Date();
            logger.debug(dateFormat.format(date));
            logger.debug("Query: select Identity_No from T_BP_Customers A, T_BP_Card B, T_BP_Card_Beneficiaries C where A.BP_Cust_ID = B.BP_Cust_ID and B.PAN = C.PAN and C.Beneficiary_No = '0015' order by Identity_No");
            ResultSet rsselect = stselect.executeQuery("select Identity_No from T_BP_Customers A, T_BP_Card B, T_BP_Card_Beneficiaries C where A.BP_Cust_ID = B.BP_Cust_ID and B.PAN = C.PAN and C.Beneficiary_No = '0015' order by Identity_No");
            while (rsselect.next()) {
                    
                    
                /*String stname1 = "South Africa";//rs.getString("Name") ;  
                String stcod1    = "RSA";//rs.getString("Cod") ;  
                //ListBeanDTO s1     = new ListBeanDTO(stcod1,stname1);  
                ListBeanDTO s1     = new ListBeanDTO("Item 1");  
                stateList.add(s1);  
                String stname2 = "Lesotho";//rs.getString("Name") ;  
                String stcod2    = "LES";//rs.getString("Cod") ;  
                //ListBeanDTO s2     = new ListBeanDTO(stcod2,stname2);  
                ListBeanDTO s2     = new ListBeanDTO("Item 2");  
                stateList.add(s2);  
                String stname3 = "Swaziland";//rs.getString("Name") ;  
                String stcod3    = "SZL";//rs.getString("Cod") ;  
                //ListBeanDTO s3     = new ListBeanDTO(stcod3,stname3);  
                ListBeanDTO s3     = new ListBeanDTO("Item 3");  
                stateList.add(s3);*/

                String TermID = rsselect.getString(1);
                date = new Date();
                logger.debug(dateFormat.format(date));
                logger.debug("TermID: " + TermID);
                ListBeanDTO s1 = new ListBeanDTO(TermID);
                stateList.add(s1);
            }
        } catch (SQLException ex) {
            date = new Date();
            logger.fatal(dateFormat.format(date));
            logger.fatal("SQLException " + ex.getMessage());
            logger.fatal("With query: select Identity_No from T_BP_Customers A, T_BP_Card B, T_BP_Card_Beneficiaries C where A.BP_Cust_ID = B.BP_Cust_ID and B.PAN = C.PAN and C.Beneficiary_No = '0015' order by Identity_No");
        }

        return stateList;
    }
}  