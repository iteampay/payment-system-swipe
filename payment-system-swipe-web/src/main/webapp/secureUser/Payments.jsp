<%-- 
    Document   : Payments
    Created on : Dec 12, 2011, 4:51:01 PM
    Author     : Akhona
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.sql.*" %>
<%@ page import="za.co.itempay.paymentsystemswipe.backend.Utils" %>

<%
        String User = request.getRemoteUser();
	//String driver = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
	//Class.forName(driver).newInstance();
	

	Connection con=null;
	ResultSet rst=null;
	Statement stmt=null;
        String Beneficiary_Name = "";
        String Beneficiary_WebSite = "";
        String Beneficiary_Slogan = "";
        String DefaultStartDate = "";
        String DefaultEndDate = "";

	try{
		//String url="jdbc:microsoft:sqlserver://Touch2:1433;DatabaseName=TrangateDBwebpayments";
		//con=DriverManager.getConnection(url, "trangate", "trangate");
		stmt = Utils.TGDBcon.createStatement();
                
                
                try
                {
                    rst = stmt.executeQuery("select Beneficiary_Name, Beneficiary_WebSite, Beneficiary_Slogan, convert(varchar, dateadd(dd, -2, getdate()), 23), convert(varchar, dateadd(dd, 1, getdate()), 23) from Users A, T_BP_Beneficiaries B where A.Beneficiary_No = B.Beneficiary_No and User_Name = '" + User + "'");
                    while(rst.next())
                    {
                        Beneficiary_Name = rst.getString(1);
                        Beneficiary_WebSite = rst.getString(2);
                        Beneficiary_Slogan = rst.getString(3);
                        DefaultStartDate = rst.getString(4);
                        DefaultEndDate = rst.getString(5);
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
        <title>Payments Report</title>
        <link rel="icon" type="image/ico" href="/payment-system-swipe-web/<%=Beneficiary_Name%>/img/TPIconTrans.ico"></link>
        <link rel="shortcut icon" href="/payment-system-swipe-web/<%=Beneficiary_Name%>/img/Icon.ico"></link>
        <script src="datetimepicker_css.js"></script>
    </head>
    <body>
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
		<li class="first"><a href="/payment-system-swipe-web/secureUser/home.jsp" >Home</a></li>
			<li><a href="/payment-system-swipe-web/secureAdmin/MemberDetailsID.jsp">Add new member</a></li>
			<li><a href="/payment-system-swipe-web/secureAdmin/Members.jsp">Current Members</a></li>
			<li><a href="/payment-system-swipe-web/secureUser/Reports.jsp">Reports</a></li>
			<li><a href="/payment-system-swipe-web/secureAdmin/MemberCashPayment.jsp">Process a Payment</a></li>
                        <li><a href="/payment-system-swipe-web/logout.jsp?logoff=true">Logoff <%= request.getRemoteUser()%></a></li>
                        <li><a href="/payment-system-swipe-web/secureUser/Payments.jsp"  class="active"> Payment Report</a></li>
		  <li><a href="/payment-system-swipe-web/secureUser/Unpaids.jsp"> Unpaid Report</a></li>
		</ul>
		<!-- Menu end -->
                
                

<hr class="noscreen" />

	<div id="content-box">
		<div id="content-box-in">

			<a name="skip-menu"></a>
			<!-- Content -->
			<div class="content-box">
				<div class="content-box-in">
					<!--h3>Please enter the client ID number</h3-->
					<h3>Please enter report details</h3>
                                        
        <form name="dbmanagement" method="post" action="../Payments_Report_All">
            <p><center>Please start and end date</center></p>
            <!--center>ID Number <input type="text" name="IDNum"/><br /></center-->
            <label for="demo1">Please enter start date here &gt;&gt; </label>
            <input type="Text" id="startdate" maxlength="25" size="25" name="startdate" value ="<%=DefaultStartDate%>"/>
            <img src="images2/cal.gif" onclick="javascript:NewCssCal('startdate','yyyyMMdd','dropdown',true,'24')" style="cursor:pointer"/>
            
            <label for="demo1">Please enter end date here &gt;&gt; </label>
            <input type="Text" id="enddate" maxlength="25" size="25" name="enddate" value ="<%=DefaultEndDate%>"/>
            <img src="images2/cal.gif" onclick="javascript:NewCssCal('enddate','yyyyMMdd','dropdown',true,'24')" style="cursor:pointer"/>

            <center><input type="submit" value="Proceed" name="Payments_Report"></center>
            <center><button type="button" onclick ="document.location.href = '/payment-system-swipe-web/secureUser/home.jsp'">Back to Main</button></center> <br />
        </form>
                                        
                                        
					</div>
				</div>
			</div>
			<!-- Content end -->
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
