<%-- 
    Document   : index
    Created on : Nov 23, 2011, 11:47:11 AM
    Author     : Akhona
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.sql.*" %>

<%
        String User = request.getRemoteUser();
	/*//String driver = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
	//Class.forName(driver).newInstance();*/
	

	Connection con=null;
	ResultSet rst=null;
	Statement stmt=null;
        String Beneficiary_Name = "";
        String Beneficiary_WebSite = "";
        String Beneficiary_Slogan = "";
        String DefaultStartDate="";

	try{
               stmt = PaymentSystemSwipe.Utils.TGDBcon.createStatement(); 
		/*//String url="jdbc:microsoft:sqlserver://Touch2:1433;DatabaseName=TrangateDBwebpayments";
		//con=DriverManager.getConnection(url, "trangate", "trangate");
		stmt = PaymentSystemSwipe.Utils.TGDBcon.createStatement(); */
                
                
                try
                {
                    rst = stmt.executeQuery("select Beneficiary_Name, Beneficiary_WebSite, Beneficiary_Slogan, convert(varchar, dateadd(dd, -2, getdate()), 23) from Users A, T_BP_Beneficiaries B where A.Beneficiary_No = B.Beneficiary_No and User_Name = '" + User + "'");
                    while(rst.next())
                    {
                        Beneficiary_Name = rst.getString(1);
                        Beneficiary_WebSite = rst.getString(2);
                        Beneficiary_Slogan = rst.getString(3);
                        DefaultStartDate = rst.getString(4);
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

function validateForm(CaptureType)
{
    //alert("CaptureType:"+CaptureType);
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
    var mobileno=document.newmember.Mobile.value;
    if (mobileno==null || mobileno=="")
    {
        alert("Mobile field must be completed.");
        return false;
    } 
    if (mobileno.length < 10 || mobileno.length > 10)
    {
        alert("Mobile Number must have 10 digits.");
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
    if (paymentmethod==null || paymentmethod=="none")
    {
        alert("Payment method must be selected.");
        return false;
    }

    document.newmember.CaptureType.value = CaptureType;  
    document.newmember.submit();
}


</script> 

    <link rel="stylesheet" href="/PaymentSystemSwipe/<%=Beneficiary_Name%>/css/style.css" type="text/css"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Member</title>
        <link rel="icon" type="image/ico" href="../TPIconTrans.ico"></link> 
        <link rel="shortcut icon" href="/PaymentSystemSwipe/<%=Beneficiary_Name%>/img/Icon.ico"></link>
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
					 <!-- h3>Please check if the customer exists</h3>-->
        <!-- <form name="checkmember" method="post" action="../Add_Member">
            <center>
                Identity Number: <input type="text" name="CheckID">
            </center>-->


<%
        String IDNum = request.getParameter("IDNum");
        String Available = "";
        System.out.println("IDNum: "+IDNum);
        if(IDNum.equals("0"))
        {
            out.println("                                <h3>Please enter the Identity Number to proceed </h3><br/><br/>");
            //out.println("<p> Please enter the Identity Number to proceed </p><br/>");
            out.println("<center><button type=\"button\" value=\"Back\" onclick=\"history.back()\">Back</button></center>");
        }
        else
        {    
            

            try
            {
                    stmt = PaymentSystemSwipe.Utils.TGDBcon.createStatement();                
                    try
                    {
                        rst = stmt.executeQuery("select count(*) from T_BP_Customers where Identity_No = '" + IDNum + "'");
                        while(rst.next())
                        {
                            if (rst.getInt(1) <= 0)
                            {
                                out.println("                                <h3>Please enter the customer details</h3>");
                                out.println("<form name=\"newmember\" method=\"post\" action=\"CaptureType.jsp\">"); //Add_Member../CardSwipe
                                out.println("    <center>");
                                out.println("        <table border=\"0\">");
                                out.println("            <tr>");
                                out.println("                <td ALIGN=RIGHT>First Name: <input type=\"text\" name=\"FirstName\"></td>");
                                out.println("                <td ALIGN=RIGHT>Surname: <input type=\"text\" name=\"SurName\"></td>");
                                out.println("            </tr>");
                                out.println("            <tr>");
                                out.println("                <td ALIGN=RIGHT>Initials: <input type=\"text\" name=\"Initials\"></td>");
                                out.println("                <td ALIGN=RIGHT>Title: <select style=\"width: 153px\" name=\"Title\"><option value=\"Mr\">Mr</option><option value=\"Mrs\">Mrs</option><option value=\"Miss\">Miss</option></select></td>");
                                //out.println("                <!--<td ALIGN=RIGHT>Title: <input type="text" name="Title"></td>-->");
                                out.println("            </tr>");
                                out.println("            <tr>");
                                //out.println("                <!--<td ALIGN=RIGHT>Sex: <input type="text" name="Sex"></td>-->");
                                out.println("                <td ALIGN=RIGHT>Gender: <select style=\"width: 153px\" name=\"Sex\"><option value=\"Male\">Male</option><option value=\"Female\">Female</option></select></td>");
                                //out.println("                <!--<td ALIGN=RIGHT>Marital Status: <input type="text" name="Marital_Status"></td>-->");
                                out.println("                <td ALIGN=RIGHT>Marital Status: <select style=\"width: 153px\" name=\"Marital_Status\"><option value=\"Single\">Single</option><option value=\"Married\">Married</option></select></td>");
                                out.println("            </tr>");
                                out.println("            <tr>");
                                out.println("                <td ALIGN=RIGHT>Identity Number: <input type=\"text\" name=\"Identity_No\" value="+IDNum+" readonly></td>");
                                out.println("                <td ALIGN=RIGHT>Membership Number: <input type=\"text\" name=\"Member_No\"></td>");
                                out.println("            </tr>");
                                out.println("            <tr>");
                                out.println("                <td ALIGN=RIGHT>Address Line 1: <input type=\"text\" name=\"Physical_Address_Line1\"></td>");
                                out.println("                <td ALIGN=RIGHT>Address Line 2: <input type=\"text\" name=\"Physical_Address_Line2\"></td>");
                                out.println("            </tr>");
                                out.println("            <tr>");
                                out.println("                <td ALIGN=RIGHT>Address Town: <input type=\"text\" name=\"Physical_Address_Town\"></td>");
                                out.println("                <td ALIGN=RIGHT>Address Region: <input type=\"text\" name=\"Physical_Address_Region\"></td>");
                                out.println("            </tr>");
                                out.println("            <tr>");
                                out.println("                <td ALIGN=RIGHT>Address Country: <input type=\"text\" name=\"Physical_Address_Country\"></td>");
                                out.println("                <td ALIGN=RIGHT>E-Mail: <input type=\"text\" name=\"EMail\"></td>");
                                out.println("            </tr>");
                                out.println("            <tr>");
                                out.println("                <td ALIGN=RIGHT>Home Phone Number: <input type=\"text\" name=\"Home_Phone_No\"></td>");
                                out.println("                <td ALIGN=RIGHT>Mobile: <input type=\"text\" name=\"Mobile\"></td>");
                                out.println("            </tr>");
                                out.println("            <tr>");
                                //out.println("                <td ALIGN=RIGHT>Birth Date: <input type=\"text\" name=\"Birthdate\"></td>");

                                out.println("<td ALIGN=RIGHT>Birth Date: <input type=\"Text\" id=\"startdate\" maxlength=\"17\" size=\"17\" name=\"startdate\" value ="+DefaultStartDate+" />");
                                out.println("<img src=\"cal.gif\" onclick=\"javascript:NewCssCal('startdate','yyyyMMdd','dropdown',false,'24')\" style=\"cursor:pointer\"/></td>");
                                out.println("            </tr>");    
                                out.println("        </table>");
                                out.println("    </center>");
                                out.println("    <center><input style=\"visibility:hidden\" type=\"text\" name=\"SelectedPayment\"/><br/></center>");
                                out.println("    <center><button type=\"button\" onclick =\"validateForm('M')\">Manually Capture Card</button></center>");
                                out.println("    <center><button type=\"button\" onclick =\"validateForm('S')\">Swipe Card</button></center>");  
                               
                                //out.println("   <input class=\"hiddenField\" type=\"hidden\" name=\"CaptureType\"  />");
                                out.println(" <center><input style=\"visibility:hidden\"  type=\"text\" name=\"CaptureType\" /></center>");
                                out.println("        </form>");
                                out.println(" <br /> <br />");
                                String PaymentType = "";
                                String PaymentDescription = "";

                                try
                                {
                                    stmt = PaymentSystemSwipe.Utils.TGDBcon.createStatement(); 
                                    try
                                    {
                                        rst = stmt.executeQuery("select PaymentMethod, PaymentDescription from T_BP_Beneficiaries A, T_Beneficiary_Payment_Method B, T_Payment_Methods C where A.Beneficiary_No = B.Beneficiary_No and B.Payment_ID = C.Payment_ID and A.Beneficiary_Name = '" + Beneficiary_Name + "'");
                                        out.println("<center><table border=\"1\">");
                                        out.println("<tr>");
                                        out.println("<th>Payment Method</th>");
                                        out.println("</tr>");

                                        while(rst.next())
                                        {
                                            PaymentType = rst.getString(1);
                                            PaymentDescription = rst.getString(2);
                                            out.println("<tr><td ALIGN=LEFT><input type=\"radio\" name=\"paymentmethod\" id=\"" + PaymentType + "\"  value=\"" + PaymentType + "\">" + PaymentType + "</td></tr>");                        
                                        }
                                        out.println("</table></center><br /><br /><br />");

                                    }
                                    catch(SQLException ex)
                                    {
                                        System.out.println(ex.getMessage());
                                    }
                                }
                                catch(Exception e)
                                {
                                    System.out.println(e.getMessage());
                                }
                                //out.println("<tr><td ALIGN=LEFT><input type=\"radio\" name=\"paymentmethod\" id=\"" + PaymentType + "\"  value=\"" + PaymentType + "\">" + PaymentType + "</td></tr>"); 

                            }
                            else
                            {
                                out.println(" <br /> <br />");
                                out.println("<center><a href=\"/PaymentSystemSwipe/secureAdmin/MemberEdit.jsp?id=" + IDNum + "\">Edit current user with identification number: " + IDNum + "</a></center>");
                            }
                        }
                        out.println("</table></center>");

                    }
                    catch(SQLException ex)
                    {
                        System.out.println(ex.getMessage());
                    }                

            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        
        }   
 %>

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
