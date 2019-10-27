<%-- 
    Document   : CaptureType
    Created on : Jul 22, 2016, 10:25:02 AM
    Author     : Billy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.sql.*,PaymentSystemSwipe.Utils,java.text.DateFormat,java.text.SimpleDateFormat,java.util.Date"%>

<%
    String User = request.getRemoteUser();
    String Beneficiary_No = "";
    String Beneficiary_Name = "";
    String Beneficiary_WebSite = "";
    String Beneficiary_Slogan = ""; 
    
    Utils.date = new Date();
            String CaptureType = request.getParameter("CaptureType");
            System.out.println("CaptureType:"+CaptureType);
            String FirstName = request.getParameter("FirstName");
            String SurName = request.getParameter("SurName");
            String Initials = request.getParameter("Initials");
            String Title = request.getParameter("Title");
            String Sex = request.getParameter("Sex");
            String Marital_Status = request.getParameter("Marital_Status");
            String Identity_No = request.getParameter("Identity_No");
            String Member_No = request.getParameter("Member_No");
            String Physical_Address_Line1 = request.getParameter("Physical_Address_Line1");
            String Physical_Address_Line2 = request.getParameter("Physical_Address_Line2");
            String Physical_Address_Town = request.getParameter("Physical_Address_Town");
            String Physical_Address_Region = request.getParameter("Physical_Address_Region");
            String Physical_Address_Country = request.getParameter("Physical_Address_Country");
            String EMail = request.getParameter("EMail");
            String Home_Phone_No = request.getParameter("Home_Phone_No");
            String Mobile = request.getParameter("Mobile");
            String SelectedPayment = request.getParameter("SelectedPayment");
            String Birthdate = request.getParameter("startdate");
            
            try
            {
                Statement stselect = Utils.TGDBcon.createStatement();
                //date = new Date();
                // Utils.logger.debug(dateFormat.format(date));
                Utils.logger.debug("Query: select Beneficiary_No from Users where User_Name = '" + User + "'");
                ResultSet rsselect = stselect.executeQuery("select Beneficiary_No from Users where User_Name = '" + User + "'");
                while(rsselect.next())
                {
                    Beneficiary_No = rsselect.getString(1);
                    Utils.date = new Date();
                    Utils.logger.debug(Utils.dateFormat.format(Utils.date));
                    Utils.logger.debug("Beneficiary_No: " + Beneficiary_No);
                }
            }
            catch(SQLException ex)
            {
                Utils.date = new Date();
                Utils.logger.fatal(Utils.dateFormat.format(Utils.date));
                Utils.logger.fatal("SQLException " + ex.getMessage());
                Utils.logger.fatal("With query: select Beneficiary_No from Users where User_Name = '" + User + "'");
            }
            
            try
            {
                Statement stselect = Utils.TGDBcon.createStatement();
                
                Utils.logger.debug(Utils.dateFormat.format(Utils.date));
                Utils.logger.debug("Query: select Beneficiary_Name, Beneficiary_WebSite, Beneficiary_Slogan from Users A, T_BP_Beneficiaries B where A.Beneficiary_No = B.Beneficiary_No and User_Name = '" + User + "'");
                ResultSet rsselect = stselect.executeQuery("select Beneficiary_Name, Beneficiary_WebSite, Beneficiary_Slogan from Users A, T_BP_Beneficiaries B where A.Beneficiary_No = B.Beneficiary_No and User_Name = '" + User + "'");
                while(rsselect.next())
                {
                    Beneficiary_Name = rsselect.getString(1);
                    Utils.date = new Date();
                    Utils.logger.debug(Utils.dateFormat.format(Utils.date));
                    Utils.logger.debug("Beneficiary_Name: " + Beneficiary_Name);
                    Beneficiary_WebSite = rsselect.getString(2);
                    Utils.logger.debug("Beneficiary_WebSite: " + Beneficiary_WebSite);
                    Beneficiary_Slogan = rsselect.getString(3);
                    Utils.logger.debug("Beneficiary_Slogan: " + Beneficiary_Slogan);
                }
            }
            catch(SQLException ex)
            {
                Utils.date = new Date();
                Utils.logger.fatal(Utils.dateFormat.format(Utils.date));
                Utils.logger.fatal("SQLException " + ex.getMessage());
                Utils.logger.fatal("With query: select Beneficiary_Name, Beneficiary_WebSite, Beneficiary_Slogan from Users A, T_BP_Beneficiaries B where A.Beneficiary_No = B.Beneficiary_No and User_Name = '" + User + "'");
            }   
            
            if(Beneficiary_Name.equals(""))
                Beneficiary_Name="Touchpay";
            if(Beneficiary_Name == null)
                Beneficiary_Name="Touchpay";
%> 
<!DOCTYPE html>
<html>
    <link rel="stylesheet" href="/PaymentSystemSwipe/<%=Beneficiary_Name%>/css/style.css" type="text/css"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Processing</title>
        <link rel="icon" type="image/ico" href="/PaymentSystemSwipe/<%=Beneficiary_Name%>/img/TPIcon.ico"></link> 
        <link rel="shortcut icon" href="/PaymentSystemSwipe/<%=Beneficiary_Name%>/img/TPIcon.ico"></link>

        <script type="text/javascript">
            function disableSelection(target)
            {
                if (typeof target.onselectstart!="undefined") //IE route
                    target.onselectstart=function(){return false}
                else if (typeof target.style.MozUserSelect!="undefined") //Firefox route
                    target.style.MozUserSelect="none"
                else //All other route (ie: Opera)
                    target.onmousedown=function()
                {
                    return false
                }
                target.style.cursor = "default"
            }
            function submitform()
            {
                document.Processing.submit();
            }
            function goBack()
            {
                document.Processing.submit();
            }            
        </script>
    </head>

    <body bgcolor=#C0E2BC>
        <div id="wrapper">
            <div id="header">
                <div id="header-arrow">
                </div>
            </div>
            <hr class="noscreen" />
            <div id="content-box">
                <div id="content-box-in">
                    <a name="skip-menu"></a>
                    <div class="content-box">
                        <div class="content-box-in">
                            <h3><B>Processing please wait...</B></h3>
                            <!--center><font size="4"><b><-%=DisplayMessage%></b></font></center-->
                            
                            
                            <%    
                            if (CaptureType.equals("M"))
                            {
                                out.println("<form name=\"Processing\" method=\"post\" action=\"ManualCapture.jsp\">");
                            } 
                            if (CaptureType.equals("S"))
                            {
                                out.println("<form name=\"Processing\" method=\"post\" action=\"../CardSwipe\">");
                            }
                            %>
                                <script type="text/javascript">
                                    disableSelection(document.body) //disable text selection on entire body of page 
                                </script>
                                <center><img src="/PaymentSystemSwipe/img/Processing.gif" border="0" alt="Payment method."></center>
                                
                                <input class="hiddenField" type="hidden" name="FirstName" value="<%=FirstName%>"/>
                                <input class="hiddenField" type="hidden" name="SurName" value="<%=SurName%>"/>
                                <input class="hiddenField" type="hidden" name="Initials" value = "<%=Initials%>"/>                                
                                <input class="hiddenField" type="hidden" name="Title" value="<%=Title%>"/>
                                <input class="hiddenField" type="hidden" name="Sex" value="<%=Sex%>"/>
                                <input class="hiddenField" type="hidden" name="Marital_Status" value="<%=Marital_Status%>"/>
                                <input class="hiddenField" type="hidden" name="Identity_No" value="<%=Identity_No%>"/>
                                <input class="hiddenField" type="hidden" name="Member_No" value="<%=Member_No%>"/>
                                <input class="hiddenField" type="hidden" name="Physical_Address_Line1" value="<%=Physical_Address_Line1%>"/>
                                <input class="hiddenField" type="hidden" name="Physical_Address_Line2" value="<%=Physical_Address_Line2%>"/>
                                <input class="hiddenField" type="hidden" name="Physical_Address_Town" value="<%=Physical_Address_Town%>"/>
                                <input class="hiddenField" type="hidden" name="Physical_Address_Region" value="<%=Physical_Address_Region%>"/>
                                <input class="hiddenField" type="hidden" name="Physical_Address_Country" value="<%=Physical_Address_Country%>"/>
                                <input class="hiddenField" type="hidden" name="EMail" value="<%=EMail%>"/>
                                <input class="hiddenField" type="hidden" name="Home_Phone_No" value="<%=Home_Phone_No%>"/>
                                <input class="hiddenField" type="hidden" name="Mobile" value="<%=Mobile%>"/>
                                <input class="hiddenField" type="hidden" name="SelectedPayment" value="<%=SelectedPayment%>"/>
                                <input class="hiddenField" type="hidden" name="Birthdate" value="<%=Birthdate%>"/>
                                
                                
                                
                                <!--center><button type="button" style=" background-color:#BDBFC2; color:#0B0501; height: 68px; width:195px" onclick =" document.location.href = '/PaymentSystemSwipe/Home.jsp'"><font size="6"><B>Home</B></font></button></center-->                                
                               <br/>
                                <script>
                                    submitform();
                                </script> 
                            </form>
                            
                        </div>
                    </div>
                </div>
            </div>
                            
            <hr class="noscreen" />
            <div id="footer">
                <p class="left">
                    TouchPay
                    <img src="/PaymentSystemSwipe/<%=Beneficiary_Name%>/img/TPLogoSmall.png">
                </p>
            </div>
        </div>
    </body>
</html>
