<%-- 
    Document   : ManualCapture
    Created on : Jul 22, 2016, 11:56:30 AM
    Author     : Billy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" import="java.sql.*,PaymentSystemSwipe.Utils,java.text.DateFormat,java.text.SimpleDateFormat,java.util.Date"%>

<%
    String User = request.getRemoteUser();
    String Beneficiary_No = "";
    String Beneficiary_Name = "";
    String Beneficiary_WebSite = "";
    String Beneficiary_Slogan = ""; 
    
    Utils.date = new Date();
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
<script type="text/javascript">  

function validateForm()
{

    var Cardno=document.MemberEdit.EMPPAN.value;
    if (Cardno==null || Cardno=="")
    {
        alert("Card Number field must be completed.");
        return false;
    } 
    if (Cardno.length < 8 || Cardno.length > 8)
    {
        alert("Card Number must have 8 digits.");
        return false;
    }

    document.MemberEdit.submit();
}
</script> 
    
    <link rel="stylesheet" href="/PaymentSystemSwipe/<%=Beneficiary_Name%>/css/style.css" type="text/css"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Members</title>
        <link rel="icon" type="image/ico" href="/PaymentSystemSwipe/<%=Beneficiary_Name%>/img/TPIconTrans.ico"></link> 
        <link rel="shortcut icon" href="/PaymentSystemSwipe/<%=Beneficiary_Name%>/img/Icon.ico"></link>
    </head>
    <body  OnLoad=document.MemberEdit.EMPPAN.focus();>    

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
					<h3>Please Insert Card Number </h3> 
    <%                                    
     if(User == null)  
      {  
            out.println("					<h3>Please Login before proceding</h3>");           

            // out.println("<font color='Red'><center>Please Login before proceding </center></font>");
             out.println("<form method=\"post\"  action=\"/PaymentSystemSwipe/logout.jsp?logoff=true\" >  ");

             out.println("<center><table border=\"0\">");
             out.println("<table border=\"0\">");
             out.println("   <tr>");
             out.println("   <td>   ");
             out.println("   <br /> ");
             out.println("   <br /> ");
             out.println("   <br /> ");
             //out.println("   <br><input style='height: 25px; width:100px' type=\"submit\" value=\"Login\"></br>");
             out.println("<td>Click here to <a href=\"/PaymentSystemSwipe/logout.jsp?logoff=true\" style=\"text-decoration:none;\"> <U><B>Login</B></U></a></td>");
             out.println("   </td>");
             out.println("   </tr>");
             out.println(" </table>");
             out.println("</center>");

             out.println("</form>");                 

      }  
     else
      {    
            out.println("<form name=\"MemberEdit\" method=\"post\" action=\"ProcessingSwipe.jsp\">");
                out.println("<center><table border=\"0\">");
                out.println("<tr>");
                out.println("<td ALIGN=RIGHT>First Name: <input type=\"text\" name=\"FirstName\" value = \"" + FirstName + "\" readonly></td>");
                out.println("<td ALIGN=RIGHT>Surname: <input type=\"text\" name=\"SurName\" value = \"" + SurName + "\" readonly></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td ALIGN=RIGHT>Identity Number: <input type=\"text\" name=\"Identity_No\" value = \"" + Identity_No + "\"  readonly></td>");
                out.println("<td ALIGN=RIGHT>Membership Number: <input type=\"text\" name=\"Member_No\" value = \"" + Member_No + "\" readonly></td>");
                out.println("</tr>");
                out.println("<tr>");
                //     out.println("<td ALIGN=RIGHT>Card number: <input type=\"text\" name=\"Home_Phone_No\" value = \"" + pan + "\"  readonly></td>");
                out.println("<td></td>");
                out.println("</tr>");
                out.println("</table></center>");  
                out.println("					<center><P><B>Please Enter Customer's Card Number</B></P></center>");
                out.println("<center><table border=\"0\">");
                out.println("<tr>");
                out.println("                <td ALIGN=RIGHT>Card Number: <input type=\"text\" name=\"EMPPAN\"></td>");
                out.println("</tr>");
                out.println("</table></center>"); 
                out.println("    <center><button type=\"button\" onclick =\"validateForm()\"> Next </button></center>"); 
                
                 
                %>               
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
                <%                
                
            out.println("</form>");     
      }
     %>
        

    </body>
</html>
