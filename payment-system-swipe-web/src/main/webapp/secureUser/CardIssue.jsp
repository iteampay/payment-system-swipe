<%-- 
    Document   : CardIssue
    Created on : 2012/02/01, 11:14:17
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
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
 //window.onload = submitform();   

function submitform()  
{  
  document.cardissue.submit();  
}  

</script> 
    
    <link rel="stylesheet" href="/payment-system-swipe-web/<%=Beneficiary_Name%>/css/style.css" type="text/css"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Card Issuing</title>
        <link rel="icon" type="image/ico" href="/payment-system-swipe-web/<%=Beneficiary_Name%>/img/TPIconTrans.ico"></link>
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
			<li><a href="/payment-system-swipe-web/secureUser/home.jsp">Home</a></li>
			<li class="first"><a href="/payment-system-swipe-web/secureAdmin/MemberDetailsID.jsp" class="active">Add new member</a></li>
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
					<h3>Please swipe card through POS device when prompted</h3>
        <form name="cardissue" method="post" action="../CardIssue">
            <center><button type="button" onclick ="document.location.href = '/payment-system-swipe-web/secureUser/home.jsp'">Back to Main</button></center> <br />
<c:choose>
    <c:when test="${empty param.bpcustid}">
        <center><input style="visibility:hidden" type="text" name="BP_Cust_ID" value = ""/><br/></center>
    </c:when>
    <c:otherwise>
        <center><input style="visibility:hidden" type="text" name="BP_Cust_ID" value = "<c:out value="${param.bpcustid}"/>"/><br/></center>
    </c:otherwise>
</c:choose>        
<c:choose>
    <c:when test="${empty param.id}">
        <center><input style="visibility:hidden" type="text" name="ID" value = ""/><br/></center>
    </c:when>
    <c:otherwise>
        <center><input style="visibility:hidden" type="text" name="ID" value = "<c:out value="${param.id}"/>"/><br/></center>
    </c:otherwise>
</c:choose>        
<c:choose>
    <c:when test="${empty param.member}">
        <center><input style="visibility:hidden" type="text" name="Member" value = ""/><br/></center>
    </c:when>
    <c:otherwise>
        <center><input style="visibility:hidden" type="text" name="Member" value = "<c:out value="${param.member}"/>"/><br/></center>
    </c:otherwise>
</c:choose>        
<c:choose>
    <c:when test="${empty param.newmember}">
        <center><input style="visibility:hidden" type="text" name="NewMember" value = ""/><br/></center>
    </c:when>
    <c:otherwise>
        <center><input style="visibility:hidden" type="text" name="NewMember" value = "<c:out value="${param.newmember}"/>"/><br/></center>
    </c:otherwise>
</c:choose>        
            <%--center><input type="submit" value="Proceed" name="IssueCard"></center>
            <center><button type="button" onclick ="document.location.href = '/PaymentSystemSwipe/secureUser/home.jsp'">Back to Main</button></center> <br /--%>
          <script>         
              submitform();     
          </script> 
            
        </form>
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
</html>
