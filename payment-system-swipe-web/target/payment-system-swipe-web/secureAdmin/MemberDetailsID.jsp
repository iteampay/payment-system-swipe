<%-- 
    Document   : index
    Created on : Nov 23, 2011, 11:47:11 AM
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

	try{
		//String url="jdbc:microsoft:sqlserver://Touch2:1433;DatabaseName=TrangateDBwebpayments";
		//con=DriverManager.getConnection(url, "trangate", "trangate");
            
		stmt = Utils.TGDBcon.createStatement();
                
                try
                {
                    rst = stmt.executeQuery("select Beneficiary_Name, Beneficiary_WebSite, Beneficiary_Slogan from Users A, T_BP_Beneficiaries B where A.Beneficiary_No = B.Beneficiary_No and User_Name = '" + User + "'");
                    while(rst.next())
                    {
                        Beneficiary_Name = rst.getString(1);
                        Beneficiary_WebSite = rst.getString(2);
                        Beneficiary_Slogan = rst.getString(3);
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
    
<script type="text/javascript">  
function elementExists(id) {
	var el = document.getElementById(id);
	
	if (el != null) {
		return true;
	}
	
	return false;
}

function validateForm()
{
    var firstname=document.newmember.FirstName.value;
    if (firstname==null || firstname=="")
    {
        alert("First Name field must be completed.");
        return false;
    }
    var surname=document.newmember.SurName.value;
    if (surname==null || surname=="")
    {
        alert("Surname field must be completed.");
        return false;
    }
    var initials=document.newmember.Initials.value;
    if (initials==null || initials=="")
    {
        alert("Initials field must be completed.");
        return false;
    }
    var identity_no=document.newmember.Identity_No.value;
    if (identity_no==null || identity_no=="")
    {
        alert("Identity number field must be completed.");
        return false;
    }
    var member_no=document.newmember.Member_No.value;
    if (member_no==null || member_no=="")
    {
        alert("Membership number field must be completed.");
        return false;
    }
    var add1=document.newmember.Physical_Address_Line1.value;
    if (add1==null || add1=="")
    {
        alert("Address line 1 field must be completed.");
        return false;
    }
    
    paymentchosen = "none"
    if (elementExists('AEDO')) {
        if(document.getElementById('AEDO').checked)
        {   paymentchosen = "AEDO" }
    }
    if (elementExists('NAEDO')) {
        if(document.getElementById('NAEDO').checked)
        {   paymentchosen = "NAEDO" }
    }
    if (elementExists('TouchPay')) {
        if(document.getElementById('TouchPay').checked)
        {   paymentchosen = "TouchPay" }
    }
    if (elementExists('Cash')) {
        if(document.getElementById('Cash').checked)
        {   paymentchosen = "Cash" }
    }
    if (elementExists('Debit Order')) {
        if(document.getElementById('Debit Order').checked)
        {   paymentchosen = "Debit Order" }
    }
    document.newmember.SelectedPayment.value = paymentchosen
    
    var paymentmethod=document.newmember.SelectedPayment.value;
    if (paymentmethod==null || paymentmethod=="")
    {
        alert("Payment method must be selected.");
        return false;
    }
    
    
    document.newmember.submit();
}


</script> 

    <link rel="stylesheet" href="/payment-system-swipe-web/<%=Beneficiary_Name%>/css/style.css" type="text/css"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Member</title>
        <link rel="icon" type="image/ico" href="../TPIconTrans.ico"></link> 
        <link rel="shortcut icon" href="/payment-system-swipe-web/<%=Beneficiary_Name%>/img/Icon.ico"></link>
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
			<li><a href="/payment-system-swipe-web/secureAdmin/MemberDetailsID.jsp" class="active">Add new member</a></li>
			<li><a href="/payment-system-swipe-web/secureAdmin/Members.jsp">Current Members</a></li>
			<li><a href="/payment-system-swipe-web/secureUser/Reports.jsp">Reports</a></li>
			<li><a href="/payment-system-swipe-web/secureAdmin/MemberCashPayment.jsp">Process a Payment</a></li>
                        <li><a href="/payment-system-swipe-web/logout.jsp?logoff=true">Logoff <%= request.getRemoteUser()%></a></li>
                        <!--<li><a href="/PaymentSystemSwipe/secureAdmin/UpdatePassword.jsp"> Change password</a></li> -->
		</ul>
		<!-- Menu end -->
<hr class="noscreen" />

	<div id="content-box">
		<div id="content-box-in">

			<a name="skip-menu"></a>
			<!-- Content -->
			<div class="content-box">
				<div class="content-box-in">
					<h3>Please check if the customer exists</h3>
        <form name="checkmember" method="post" action="../CheckID">
            <center>
                Identity Number: <input type="text" name="CheckID">
            </center>
            <center><input type="submit" value="Proceed" name="AddMember"></center>
            <center><button type="button" onclick ="document.location.href = '/payment-system-swipe-web/secureUser/home.jsp'">Back to Main</button></center> <br />

                                        
                                        
					</div>
				</div>
			</div>
			<!-- Content left end -->
		</div>
<hr class="noscreen" />

	<!-- Footer 
	<div id="footer">
		<p class="left">Powered by <a class="b" href="http://www.touchpay.co.za">TouchPay</a><img src="/PaymentSystemSwipe/<%=Beneficiary_Name%>/img/TPLogoSmall.png"></p>
	</div>
	 Footer end -->
</div>    
        
    </body>
</html>
