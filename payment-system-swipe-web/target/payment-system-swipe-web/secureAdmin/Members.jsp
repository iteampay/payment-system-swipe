<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<!DOCTYPE html>
<html>
<script type="text/javascript">  

function display_alert()
 { 
  alert('Alert')
 }   
                
 function validateForm()
  { 
     
          //alert("Here 2");  
            //document.getElementById("myRadio").checked;  
            if(document.getElementById('Active').checked)  
            {  
              //  alert("Here 3");
                document.Members.Transtype.value = "Y" ;
            } 
            if(document.getElementById('NotActive').checked)
            {
              //  alert("Here 4");
                document.Members.Transtype.value = "N"; 
            }
            if(document.getElementById('All').checked)  
            {  
               // alert("Here 5"); 
                document.Members.Transtype.value = "A" ;
            } 
            // alert("Here Done"); 
                          
            document.Members.submit();
  }                
</script>     
<script type="text/javascript">  
 //window.onload = submitform();   

function submitform()  
{  
  document.Members.submit();  
}  

</script> 
    <link rel="stylesheet" href="/PaymentSystemSwipe/<%=Beneficiary_Name%>/css/style.css" type="text/css"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Members</title>
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
			<li><a href="/PaymentSystemSwipe/secureAdmin/MemberDetailsID.jsp">Add new member</a></li>
			<li class="first"><a href="/PaymentSystemSwipe/secureAdmin/Members.jsp" class="active">Current Members</a></li>
			<li><a href="/PaymentSystemSwipe/secureUser/Reports.jsp">Report</a></li>
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
					<h3>Please select details</h3>
        <form name="Members" method="post" action="../MemberDetails">
            <% 
                out.println("<center> ");
                out.println("<p><center>&nbsp &nbsp Please select Report type here before you run</center></p>");                                        
                out.println("<table border=\"0\"> "); 
                out.println("<tr>");
                out.println("<td ALIGN=RIGHT>Active <input id=\"Active\" type=\"radio\" name=\"ActiveType\"  checked  value=\"Active_Type\" style=\"background: blue\"/></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td ALIGN=RIGHT>Non Active <input id=\"NotActive\"  type=\"radio\" name=\"ActiveType\"  value=\"NotActive_Type\" style=\"background: blue\" /></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td ALIGN=RIGHT>All <input id=\"All\"  type=\"radio\" name=\"ActiveType\"  value=\"All\" style=\"background: blue\" />   </td> ");
                out.println("</tr>");
                out.println("</table>"); 
                out.println("</center> "); 
                out.println("<center><button type=\"button\" style=\" width:60px\" onclick =\"validateForm()\">Proceed</button></center>  ");   
                out.println("<center><input  style=\"visibility:hidden\" type=\"text\" name=\"Transtype\"   /><br /></center> "); 
                out.println("<center><input  style=\"visibility:hidden\" type=\"text\" name=\"SortBy\"  /><br /></center> "); 
                out.println("<center><input  style=\"visibility:hidden\" type=\"text\" name=\"AscDesc\"  /><br /></center> "); 
                out.println("<center><input  style=\"visibility:hidden\" type=\"text\" name=\"Search\"  /><br /></center> "); 
                  
            %> 
            <%--   
            <c:choose>
                <c:when test="${empty param.sort}">
                    <center><input style="visibility:hidden" type="text" name="SortBy" value = ""/><br/></center>
                </c:when>
                <c:otherwise>
                    <center><input style="visibility:hidden" type="text" name="SortBy" value = "<c:out value="${param.sort}"/>"/><br/></center>
                </c:otherwise>
            </c:choose>        
            <c:choose>
                <c:when test="${empty param.order}">
                    <center><input style="visibility:hidden" type="text" name="AscDesc" value = ""/><br/></center>
                </c:when>
                <c:otherwise>
                    <center><input style="visibility:hidden" type="text" name="AscDesc" value = "<c:out value="${param.order}"/>"/><br/></center>
                </c:otherwise>
            </c:choose>        
            <c:choose>
                <c:when test="${empty param.search}">
                    <center><input style="visibility:hidden" type="text" name="Search" value = ""/><br/></center>
                </c:when>
                <c:otherwise>
                    <center><input style="visibility:hidden" type="text" name="Search" value = "<c:out value="${param.search}"/>"/><br/></center>
                </c:otherwise>
            </c:choose>        
          <script>         
              submitform();     
          </script> 
          --%>
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
