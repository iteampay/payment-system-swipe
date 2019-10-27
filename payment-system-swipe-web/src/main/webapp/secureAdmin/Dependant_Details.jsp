<%-- 
    Document   : index
    Created on : Nov 23, 2011, 11:47:11 AM
    Author     : Akhona
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ page language="java" import="java.sql.*" %>

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
		stmt = PaymentSystemSwipe.Utils.TGDBcon.createStatement(); 
                
                
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
function validateForm()
{
    var firstname=document.newdependant.FirstName.value;
    if (firstname==null || firstname=="")
    {
        alert("First Name field must be completed.");
        return false;
    }
    var surname=document.newdependant.SurName.value;
    if (surname==null || surname=="")
    {
        alert("Surname field must be completed.");
        return false;
    }
    var initials=document.newdependant.Initials.value;
    if (initials==null || initials=="")
    {
        alert("Initials field must be completed.");
        return false;
    }
    var identity_no=document.newdependant.Identity_No.value;
    if (identity_no==null || identity_no=="")
    {
        alert("Identity number field must be completed.");
        return false;
    }
    var member_no=document.newdependant.Member_No.value;
    if (member_no==null || member_no=="")
    {
        alert("Membership number field must be completed.");
        return false;
    }
    var add1=document.newdependant.Physical_Address_Line1.value;
    if (add1==null || add1=="")
    {
        alert("Address line 1 field must be completed.");
        return false;
    }
    document.newdependant.submit();
}
</script> 
    <link rel="stylesheet" href="/PaymentSystemSwipe/<%=Beneficiary_Name%>/css/style.css" type="text/css"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Dependant</title>
        <link rel="icon" type="image/ico" href="/PaymentSystemSwipe/<%=Beneficiary_Name%>/img/TPIconTrans.ico"></link> 
        <link rel="shortcut icon" href="/PaymentSystemSwipe/<%=Beneficiary_Name%>/img/Icon.ico"></link>
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
			<li><a href="/PaymentSystemSwipe/secureUser/home.jsp">Home</a></li>
			<li class="first"><a href="/PaymentSystemSwipe/secureAdmin/MemberDetailsID.jsp" class="active">Add new member</a></li>
			<li><a href="/PaymentSystemSwipe/secureAdmin/Members.jsp">Current Members</a></li>
			<li><a href="/PaymentSystemSwipe/secureUser/Payments.jsp">Payment Report</a></li>
			<li><a href="/PaymentSystemSwipe/secureAdmin/MemberCashPayment.jsp">Process a Payment</a></li>
			<li><a href="/PaymentSystemSwipe/logout.jsp?logoff=true">Logoff <%= request.getRemoteUser()%></a></li>
		</ul>
		<!-- Menu end -->
                
                
                
                
                
                
<hr class="noscreen" />

	<div id="content-box">
		<div id="content-box-in">

			<a name="skip-menu"></a>
			<!-- Content -->
			<div class="content-box">
				<div class="content-box-in">
					<h3>Please enter the Dependant details</h3>
        <form name="newdependant" method="post" action="../Add_Dependant">
            <center>
                <table border="0">
                    <tr>
                        <td ALIGN=RIGHT>First Name: <input type="text" name="FirstName"></td>
                        <td ALIGN=RIGHT>Surname: <input type="text" name="SurName"></td>
                    </tr>
                    <tr>
                        <td ALIGN=RIGHT>Initials: <input type="text" name="Initials"></td>
                        <td ALIGN=RIGHT>Title: <input type="text" name="Title"></td>
                    </tr>
                    <tr>
                        <td ALIGN=RIGHT>Sex: <input type="text" name="Sex"></td>
                        <td ALIGN=RIGHT>Marital Status: <input type="text" name="Marital_Status"></td>
                    </tr>
                    <tr>
                        <td ALIGN=RIGHT>Identity Number: <input type="text" name="Identity_No"></td>
                        <td ALIGN=RIGHT>Dependant Number: <input type="text" name="Member_No"></td>
                    </tr>
                    <tr>
                        <td ALIGN=RIGHT>Address Line 1: <input type="text" name="Physical_Address_Line1"></td>
                        <td ALIGN=RIGHT>Address Line 2: <input type="text" name="Physical_Address_Line2"></td>
                    </tr>
                    <tr>
                        <td ALIGN=RIGHT>Address Town: <input type="text" name="Physical_Address_Town"></td>
                        <td ALIGN=RIGHT>Address Region: <input type="text" name="Physical_Address_Region"></td>
                    </tr>
                    <tr>
                        <td ALIGN=RIGHT>Address Country: <input type="text" name="Physical_Address_Country"></td>
                        <td ALIGN=RIGHT>E-Mail: <input type="text" name="EMail"></td>
                    </tr>
                    <tr>
                        <td ALIGN=RIGHT>Home Phone Number: <input type="text" name="Home_Phone_No"></td>
                        <td ALIGN=RIGHT>Mobile: <input type="text" name="Mobile"></td>
                    </tr>
                    <tr>
                    </tr>
                </table>
            </center>
            <c:choose>
                <c:when test="${empty param.bpcustid}">
                    <center><input style="visibility:hidden" type="text" name="BP_Cust_ID" value = ""/><br/></center>
                </c:when>
                <c:otherwise>
                    <center><input style="visibility:hidden" type="text" name="BP_Cust_ID" value = "<c:out value="${param.bpcustid}"/>"/><br/></center>
                </c:otherwise>
            </c:choose>        
            
            <center><button type="button" onclick ="validateForm()">Next</button></center>
            <center><button type="button" onclick ="document.location.href = '/PaymentSystemSwipe/secureUser/home.jsp'">Back to Main</button></center> <br />

             <%--center><button type="button" onclick ="document.location.href = '/PaymentSystemSwipe/Test1.jsp'">Next</button></center> <br /--%>
        </form>
					</div>
				</div>
			</div>
			<!-- Content left end -->
		</div>
<hr class="noscreen" />

	<!-- Footer -->
	<div id="footer">
		<p class="left">Powered by <a class="b" href="http://www.touchpay.co.za">TouchPay</a><img src="/PaymentSystemSwipe/<%=Beneficiary_Name%>/img/TPLogoSmall.png"></p>
	</div>
	<!-- Footer end -->
</div>    
    </body>
</html>
