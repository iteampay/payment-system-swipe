<%-- 
    Document   : home
    Created on : Nov 24, 2011, 12:50:50 PM
    Author     : Akhona
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.sql.*" %>
<%@ page import="za.co.itempay.paymentsystemswipe.backend.Utils" %>

<%


        String User = request.getRemoteUser();
        System.out.println("User = "+User);
        
	/* //String driver = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
	//Class.forName(driver).newInstance();
	*/

	Connection con=null;
	ResultSet rst=null;
	Statement stmt=null;
        String Beneficiary_Name = "";
        String Beneficiary_WebSite = "";
        String Beneficiary_Slogan = "";
        String Beneficiary_No = "";
        String Total_Members = "";
        String Active_Members = "";
        String Inactive_Members = "";
        String Total_Dependants = "";
        String Total_Available ="";

	try{
                 stmt = Utils.TGDBcon.createStatement();
                
		/* //String url="jdbc:microsoft:sqlserver://Touch2:1433;DatabaseName=TrangateDBwebpayments";
		   //con=DriverManager.getConnection(url, "trangate", "trangate");
                   stmt = PaymentSystemSwipe.Utils.TGDBcon.createStatement(); 
                */
		 
                
                
                try
                {
                    rst = stmt.executeQuery("select Beneficiary_Name, Beneficiary_WebSite, Beneficiary_Slogan, B.Beneficiary_No from Users A, T_BP_Beneficiaries B where A.Beneficiary_No = B.Beneficiary_No and User_Name = '" + User + "'");
                    while(rst.next())
                    {
                        Beneficiary_Name = rst.getString(1);
                        Beneficiary_WebSite = rst.getString(2);
                        Beneficiary_Slogan = rst.getString(3);
                        Beneficiary_No = rst.getString(4);
                    }
                }
                catch(SQLException ex)
                {
                    System.out.println(ex.getMessage());
                }
                

	}
	catch(Exception e){
		System.out.println(e.getMessage());
	}
	try{
             // //String url="jdbc:microsoft:sqlserver://Touch2:1433;DatabaseName=TrangateDBwebpayments";
	     /*	//con=DriverManager.getConnection(url, "trangate", "trangate");
		stmt = PaymentSystemSwipe.Utils.TGDBcon.createStatement();  
             */
                
                
                try
                {
                    rst = stmt.executeQuery("select count(*) from T_BP_Customers A, T_BP_Card B, T_BP_Card_Beneficiaries C where A.BP_Cust_ID = B.BP_Cust_ID and B.PAN = C.PAN and Card_Status = 2 and C.Beneficiary_No = '" + Beneficiary_No + "'");
                    while(rst.next())
                    {
                        Total_Members = rst.getString(1);
                    }
                    
                    rst = stmt.executeQuery("select count(Distinct(B.PAN)) from T_BP_Customers A, T_BP_Card B, T_BP_Card_Beneficiaries C, T_EasyPay_Messages D where A.BP_Cust_ID = B.BP_Cust_ID and B.PAN = C.PAN and D.PAN = B.PAN and C.Beneficiary_No = '" + Beneficiary_No + "' and File_Date_Time >= dateadd(mm, -3, getdate()) and File_Date_Time <= getdate()");
                    while(rst.next())
                    {
                        Active_Members = rst.getString(1);
                    }
                    
                    Inactive_Members = String.valueOf(Integer.parseInt(Total_Members) - Integer.parseInt(Active_Members));
                    
                    rst = stmt.executeQuery("select count(*) from T_BP_Customer_Dependants A, T_BP_Customers B, T_BP_Card C, T_BP_Card_Beneficiaries D where A.BP_Cust_ID = B.BP_Cust_ID and B.BP_Cust_ID = C.BP_Cust_ID and C.PAN = D.PAN and D.Beneficiary_No = '" + Beneficiary_No + "'");
                    while(rst.next())
                    {
                        Total_Dependants = rst.getString(1);
                    }
                    rst = stmt.executeQuery("select count(*) from dbo.T_EasyPay_No_Track_New where  Status='Available' and Beneficiary_no= '" + Beneficiary_No + "'");
                    while(rst.next())
                    {
                        Total_Available = rst.getString(1);
                    }
                }
                catch(SQLException ex)
                {
                    System.out.println(ex.getMessage());
                }
                

	}
	catch(Exception e){
		System.out.println(e.getMessage());
	}

            
 %>
<!DOCTYPE html>
<html>
    <link rel="stylesheet" href="/payment-system-swipe-web/<%=Beneficiary_Name%>/css/style.css" type="text/css"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment Management System</title>
        <link rel="icon" type="image/ico" href="/payment-system-swipe-web/<%=Beneficiary_Name%>/img/TPIconTrans.ico"></link>
        <link rel="shortcut icon" href="/payment-system-swipe-web/<%=Beneficiary_Name%>/img/Icon.ico"></link>
    </head>
    <body">
<div id="wrapper">
    
		<!-- Header -->
		<div id="header">

			<!-- Your website name  -->
			<h1><a href="<%=Beneficiary_WebSite%>"><%=Beneficiary_Name%></a></h1>
			<!-- Your website name end -->
		
			<!-- Your slogan -->
			<h2><%=Beneficiary_Slogan%></h2>
			<!-- Your slogan end -->

			<div id="header-arrow"></div>
		</div>
		<!-- Header end -->
                
		<!-- Menu -->
		<ul id="menu" class="cleaning-box">
			<li class="first"><a href="/payment-system-swipe-web/secureUser/home.jsp" class="active">Home</a></li>
			<li><a href="/payment-system-swipe-web/secureAdmin/MemberDetailsID.jsp">Add new member</a></li>
			<li><a href="/payment-system-swipe-web/secureAdmin/Members.jsp">Current Members</a></li>
			<li><a href="/payment-system-swipe-web/secureUser/Reports.jsp">Reports</a></li>
			<li><a href="/payment-system-swipe-web/secureAdmin/MemberCashPayment.jsp">Process a Payment</a></li>
            <li><a href="/payment-system-swipe-web/logout.jsp?logoff=true">Logoff <%= request.getRemoteUser()%></a></li>
            
		</ul>
                
              
		<!-- Menu end -->
                
                

<hr class="noscreen" />

	<div id="content-box">
		<div id="content-box-in">

			<a name="skip-menu"></a>
			<!-- Content -->
			<div class="content-box">
				<div class="content-box-in">
					<h3>Please select an operation from the links above</h3>
                                        
            <center><table border="1"> 
                <tr>
                    <th>Total Members</th>
                    <th>Active Members</th>
                    <th>Inactive Members</th>
                    <th>Total Available Card</th>
                </tr>
                <tr>
                    <td ALIGN=RIGHT><%=Total_Members%></td>
                    <td ALIGN=RIGHT><%=Active_Members%></td>
                    <td ALIGN=RIGHT><%=Inactive_Members%></td>
                    <%
                    if(Total_Available.equals("0"))
                    {
                        %>
                        <td ALIGN=RIGHT><%=Total_Available%></td>
                        <%
                    }   
                    else 
                    {  
                        %>
                        <td ALIGN=RIGHT><a href="/payment-system-swipe-web/secureUser/AvailaibleCard.jsp"><%=Total_Available%></a></td>
                        <%
                    }
                    %>
                </tr>
            </table></center></br>
            
            </br>
            <!-- <center><button type="button" style=" background-color:#BDBFC2; color:#0B0501; height: 60px; width:185px" onclick ="document.location.href = '/PaymentSystemSwipe/Test'"><font size="4" color="3168B0"><B>Test</B></font></button></center> -->  
                 
            <!--<a href="/PaymentSystemSwipe/secureAdmin/BankDetails.jsp?id=8206185023087">Test Bank Details</a>>
            <center><input style="visibility:hidden" type="text" name="IP" value = "<-%IP%>"/><br/></center-->
            
            
            
					</div>
				</div>
			</div>
			<!-- Content left end -->
		</div>
<hr class="noscreen" />

	<!-- Footer -->
	<div id="footer">
		<p class="left">Powered by <a class="b" href="http://www.touchpay.co.za">TouchPay</a><img src="/payment-system-swipe-web/<%=Beneficiary_Name%>/img/TPLogoSmall.png"></p>
	</div>
	<!-- Footer end -->
</div>    
            
    </body>
</html>
