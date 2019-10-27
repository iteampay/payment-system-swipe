<%-- 
    Document   : DB
    Created on : 2012/03/05, 11:44:24
    Author     : Administrator
--%>

<%@ page language="java" import="java.sql.*"%>
<%@ page import="za.co.itempay.paymentsystemswipe.backend.Utils" %>
<html>
    <link rel="stylesheet" href="../css/style.css" type="text/css"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Funeral Management System</title>
        <link rel="icon" type="image/ico" href="../TPIconTrans.ico"></link> 
        <link rel="shortcut icon" href="../TPIconTrans.ico"></link>
    </head>
<body>
    <body>
        <%--center><img src="ABLogo.jpg" alt="Logo"/></center>
        <center><h1>Funeral Management System</h1> </center> 
        <center>
            <table border="1" cellspacing="15" cellpadding="15">
                <tr>
                    <td>
                        <center><h4>Members</h4> </center>
                        <center><a href="/PaymentSystemSwipe/secureAdmin/MemberDetails.jsp">Add new member</a> </center> <br>
                        <center><a href="/PaymentSystemSwipe/secureAdmin/Members.jsp">Current Members</a> </center><br>
                        <center><a href="/PaymentSystemSwipe/secureUser/NewTest.jsp">Test CSS</a> </center><br>
                    </td>
                    <td> 
                        <center><h4>Payments</h4> </center>
                        <center><a href="/PaymentSystemSwipe/secureUser/Payments.jsp">Payment Report</a> </center><br>
                        <center><a href="/PaymentSystemSwipe/secureAdmin/MemberCashPayment.jsp">Process a Payment</a> </center><br>
                    </td>
                </tr>
            </table>
        </center>

        <form method="post" action="/PaymentSystemSwipe/logout.jsp?logoff=true">
            <br><center>Logged in as <%= request.getRemoteUser()%></center>
            <br><center><input type="submit" value="Logoff"></center>
        </form--%>
            
            
<div id="wrapper">
    
		<!-- Header -->
		<div id="header">

			<!-- Your website name  -->
			<h1><a href="http://www.touchpay.co.za">Touchpay</a></h1>
			<!-- Your website name end -->
		
			<!-- Your slogan -->
			<h2>Payments made easy!</h2>
			<!-- Your slogan end -->

			<div id="header-arrow"></div>
		</div>
		<!-- Header end -->
                
		<!-- Menu -->
		<a href="#skip-menu" class="hidden">Skip menu</a>
		<ul id="menu" class="cleaning-box">
			<li class="first"><a href="/payment-system-swipe-web/secureUser/home.jsp" class="active">Home</a></li>
			<li><a href="/payment-system-swipe-web/secureAdmin/MemberDetailsID.jsp">Add new member</a></li>
			<li><a href="/payment-system-swipe-web/secureAdmin/Members.jsp">Current Members</a></li>
			<li><a href="/payment-system-swipe-web/secureUser/Payments.jsp">Payment Report</a></li>
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
                                        <%--centre><%= rst.getString(1) %></centre--%>
                                        <centre><a href="/payment-system-swipe-web/secureUser/DB.jsp">Test DB</a></centre>
					</div>
				</div>
			</div>
			<!-- Content left end -->
		</div>
                
                
                
                
                
                
                


<hr class="noscreen" />

	<!-- Footer -->
	<div id="footer">
		<p class="left">Powered by <a class="b" href="http://www.touchpay.co.za">TouchPay</a><img src="../img/TPLogoSmall.png"></p>
	</div>
	<!-- Footer end -->
</div>    
            
    </body>

<%
//String driver = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
//Class.forName(driver).newInstance();


Connection con=null;
ResultSet rst=null;
Statement stmt=null;

try{
//String url="jdbc:microsoft:sqlserver://Touch2:1433;DatabaseName=TrangateDBwebpayments";

int i=1;
//con=DriverManager.getConnection(url, "trangate", "trangate");
stmt = Utils.TGDBcon.createStatement();
rst=stmt.executeQuery("select getdate() ");
while(rst.next()){

if (i==(i/2)*2){
%>
<tr>
<td bgColor="#ffff98" vAlign="top" width="47" align="center" height="19"><%=i%>.</td>
<td bgColor="#ffff98" vAlign="top" width="107" height="19"><%=rst.getString(1)%></td>
<td bgColor="#ffff98" vAlign="top" width="224" height="19"><a href="<%=rst.getString(1)%>"><%=rst.getString(1)%></a>&nbsp;</td>
<td bgColor="#ffff98" vAlign="top" width="270" height="19"><%=rst.getString(1)%></td>
</tr>
<%
}else{
%>
<tr>
<td bgColor="#ffcc68" vAlign="top" width="47" align="center" height="19"><%=i%>.</td>
<td bgColor="#ffcc68" vAlign="top" width="107" height="19"><%=rst.getString(1)%></td>
<td bgColor="#ffcc68" vAlign="top" width="224" height="19"><a href="<%=rst.getString(1)%>"><%=rst.getString(1)%></a>&nbsp;</td>
<td bgColor="#ffcc68" vAlign="top" width="270" height="19"><%=rst.getString(1)%></td>
</tr>
<% }

i++;
}
rst.close();
stmt.close();
con.close();
}catch(Exception e){
System.out.println(e.getMessage());
}
%>

</tbody>
</table>
</center>
</div>


</body>
</html>