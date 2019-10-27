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
        String Deposit = "0.00";

        String MembershipNo = "";
        String Name ="";
	try{
	      /* //String url="jdbc:microsoft:sqlserver://Touch2:1433;DatabaseName=TrangateDBwebpayments";
		//con=DriverManager.getConnection(url, "trangate", "trangate");
		stmt = PaymentSystemSwipe.Utils.TGDBcon.createStatement(); */
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
                

                String IDNum = request.getParameter("id");
                try
                {
                    rst = stmt.executeQuery("select Member_No,FirstName + ' ' + SurName from T_BP_Customers where Identity_No = '" + IDNum + "'");
                    while(rst.next())
                    {
                        MembershipNo = rst.getString(1);
                        Name = rst.getString(2); 
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
  document.MemberEdit.submit();  
}  

</script> 
<SCRIPT type="text/javascript">    
    window.history.forward();    
    function noBack() { window.history.forward(); }
</SCRIPT>
    
    <link rel="stylesheet" href="/PaymentSystemSwipe/<%=Beneficiary_Name%>/css/style.css" type="text/css"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Members</title>
        <link rel="icon" type="image/ico" href="/PaymentSystemSwipe/<%=Beneficiary_Name%>/img/TPIconTrans.ico"></link> 
        <link rel="shortcut icon" href="/PaymentSystemSwipe/<%=Beneficiary_Name%>/img/Icon.ico"></link>
        <script type="text/javascript">
            var LastGood = 1;
            var fee = 0;
            var troundedfee = "";
            
            function CheckValue(input, max) {
                var value = Number(input.value);
                if (NaN == value || input.value>max) { //it is not a number or is too big - revert
                    input.value = LastGood;
                } else { //it is ok, save it as the last good value
                    LastGood = value;
                }
            }
            function validate(evt) {
                var theEvent = evt || window.event;
                var key = theEvent.keyCode || theEvent.which;
                key = String.fromCharCode( key );
                //alert(key);
                var regex = /[0-9]|\./;
                if( !regex.test(key) ) {
                    theEvent.returnValue = false;
                    if(theEvent.preventDefault) theEvent.preventDefault();
                    //alert('here 1');
                    document.ProcessCashPayment.POSAmount.value = document.ProcessCashPayment.TestAmount.value;
                }
                else{
                    var amount = document.ProcessCashPayment.TestAmount.value;
                    amount = (amount *10) + (key/100);
                    
                    fee = amount * .05;
                    
                    var roundedamount = Math.round(amount*100)/100
                    var troundedamount = "" + roundedamount;

                    var roundedfee = Math.round(fee*100)/100
                    troundedfee = "" + roundedfee;
                    
                    var totalamount = roundedamount;
                    //var totalamount = roundedamount + roundedfee;
                    var ttotalamount = "" + totalamount;
                    //alert("fee: " + fee)
                    //alert("troundedfee: " + troundedfee)
                    //alert("ttotalamount: " + ttotalamount)

                }
                
                var pos = troundedamount.indexOf(".");
                //alert("pos: " + pos);
                var len = troundedamount.length;
                //alert("len: " + len);
                
                if ((key==0) && (pos != -1)){
                    troundedamount = troundedamount + "0";
                    ttotalamount = ttotalamount + "0";
                }
                if (pos == -1){
                    //alert("troundedamount before: " + troundedamount)
                    troundedamount = troundedamount + ".00";
                    ttotalamount = ttotalamount + ".00";
                    //alert("troundedamount after: " + troundedamount)
                }
                    
                document.ProcessCashPayment.POSAmount.value = troundedamount;
                document.ProcessCashPayment.TestAmount.value = troundedamount;
                document.ProcessCashPayment.TotalAmount.value = ttotalamount;
            }
            function copy() {
                document.ProcessCashPayment.POSAmount.value = troundedamount;
                document.ProcessCashPayment.TestAmount.value = troundedamount;
                document.ProcessCashPayment.TotalAmount.value = ttotalamount;
            }
            function clearfields() {
                document.ProcessCashPayment.POSAmount.value = "0";
                document.ProcessCashPayment.tranRef.value = "";
                document.ProcessCashPayment.TestAmount.value = "";
                document.ProcessCashPayment.TotalAmount.value = "";
            }
            
            var agree,win;
            function showWin(){
                win=window.open("","","width=200,height=200,status=yes,fullscreen=yes");
                agree=false;
                win.document.open();
                //win.document.write("<body onblur='this.focus();><center>Here are the Terms...<p><button onclick='self.close();opener.agree=true;'>I Agree</button></center>");
                win.document.write("<button onclick='self.close();opener.agree=true;'>I Agree</button><button onclick='self.close();opener.location.href=\"redirect.html\";'>Disagree</button></center>");
                win.document.close();
                if (agree == true)
                    document.ProcessCashPayment.submit();
            }
            function check(){if(!agree){showWin();}}
            window.onfocus=function(){if(win&&!win.closed){win.focus();}}
            
            function check2(fee){
                if (confirm('Do you accept a transaction fee of R' + troundedfee + '?')){
                    document.ProcessCashPayment.submit();
                    //window.location.href = 'http://someurl.com';
                } else {
                    window.location.href = '../NotAccepted.html';
                    return false;
                }
            }            
        </script>
        
        <%
        /*
                     out.println("<script> ");
             
                    out.println("function validate(evt) {");
                    out.println("var theEvent = evt || window.event;");
                    out.println("var key = theEvent.keyCode || theEvent.which;");
                    out.println("key = String.fromCharCode( key );");
                    //out.println("alert(key);");
                    out.println("if (key == \"`\")");
                    out.println("key = \"0\";");
                    out.println("if (key == \"a\")");
                    out.println("key = \"1\";");
                    out.println("if (key == \"b\")");
                    out.println("key = \"2\";");
                    out.println("if (key == \"c\")");
                    out.println("key = \"3\";");
                    out.println("if (key == \"d\")");
                    out.println("key = \"4\";");
                    out.println("if (key == \"e\")");
                    out.println("key = \"5\";");
                    out.println("if (key == \"f\")");
                    out.println("key = \"6\";");
                    out.println("if (key == \"g\")");
                    out.println("key = \"7\";");
                    out.println("if (key == \"h\")");
                    out.println("key = \"8\";");
                    out.println("if (key == \"i\")");
                    out.println("key = \"9\";");
                    //out.println("alert(key);");
                    out.println("var regex = /[0-9]|\\./;");
                    out.println("if( !regex.test(key) ) {");
                    out.println("theEvent.returnValue = false;");
                    out.println("if(theEvent.preventDefault) theEvent.preventDefault();");
                        //alert('here 1');
                    //out.println("document.Allreports.POSAmount.value = document.Allreports.TestAmount.value;");
                    out.println("document.ProcessCashPayment.POSAmount.value = document.ProcessCashPayment.TestAmount.value;");

                    out.println("}");
                    out.println("else{");
                    out.println("var amount = document.ProcessCashPayment.TestAmount.value;");
                    //out.println("alert(\"amount1: \" + amount)");
                    out.println("amount = (amount *10) + (key/100);");
                    //out.println("alert(\"amount2: \" + amount)");
                    
                    out.println("fee = amount * .05;");
                    
                    out.println("var roundedamount = Math.round(amount*100)/100");
                    out.println("var troundedamount = \"\" + roundedamount;");

                    out.println("var roundedfee = Math.round(fee*100)/100");
                    out.println("troundedfee = \"\" + roundedfee;");
                    
                    out.println("var totalamount = roundedamount + roundedfee;");
                    out.println("var ttotalamount = \"\" + totalamount;");
                    //alert("fee: " + fee)
                    //alert("troundedfee: " + troundedfee)
                    //alert("ttotalamount: " + ttotalamount)

                    out.println("}");

                    out.println("var pos = troundedamount.indexOf(\".\");");
                    //alert("pos: " + pos);
                    out.println("var len = troundedamount.length;");
                    //alert("len: " + len);

                    out.println("if ((key==0) && (pos != -1)){");
                    out.println("troundedamount = troundedamount + \"0\";");
                    out.println("ttotalamount = ttotalamount + \"0\";");
                    out.println("}");
                    out.println("if (pos == -1){");
                    //out.println("alert(\"troundedamount before: \" + troundedamount)");
                    out.println("    troundedamount = troundedamount + \".00\";");
                    out.println("    ttotalamount = ttotalamount + \".00\";");
                    //out.println("alert(\"troundedamount after: \" + troundedamount)");
                    out.println("}");

                    out.println("document.ProcessCashPayment.POSAmount.value = troundedamount;");
                    out.println("document.ProcessCashPayment.TestAmount.value = troundedamount;");
                    //out.println("document.SaveWallet.TotalAmount.value = ttotalamount;");
                    out.println("}");    
                    out.println("function copy() {");
                    out.println("    document.ProcessCashPayment.POSAmount.value = troundedamount;");
                    out.println("    document.ProcessCashPayment.TestAmount.value = troundedamount;");
                   // out.println("    document.SaveWallet.TotalAmount.value = ttotalamount;");
                    out.println("}");
                    out.println("</script> ");
                    out.println("<script> ");
                    out.println("function clearfields() {");
                    out.println("    document.ProcessCashPayment.POSAmount.value = \"0.00\";");
                    out.println("    document.ProcessCashPayment.TestAmount.value = \"\";");
                    out.println("}");  

                    

                    out.println("function clearreasonfields() {");
                   // var Reason=document.Allreports.phoneno.value;
                    out.println("  document.ProcessCashPayment.Reason.value = \"\";");
                    //out.println("    document.Allreports.TestAmount.value = \"\";");
                    out.println("}");  
                    out.println("</script> ");  */       
        %>        
    </head>
    <body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="" >

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
			<li><a href="/PaymentSystemSwipe/secureAdmin/Members.jsp">Current Members</a></li>
			<li><a href="/PaymentSystemSwipe/secureUser/Payments.jsp">Payment Report</a></li>
			<li class="first"><a href="/PaymentSystemSwipe/secureAdmin/MemberCashPayment.jsp" class="active">Process a Payment</a></li>
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
					<h3>Please enter payment information</h3>
        
        
        <form name="ProcessCashPayment" method="post" action="../ProcessCashPayment">
            
            
            <center>FullName: <input type="text" value="<%=Name%>"/><br /></center>
            <center>Amount: <input value ="0" type="text" name="POSAmount" onkeyup="validate(event)" /><br /></center-->
            <%
            //out.println("<td ALIGN=RIGHT> Amount: <input type=\"text\" size=\"10\" name=\"POSAmount\" value ="+ Deposit + " onkeyup=\"validateForm(event);\" /></td><td><center><button type=\"button\" onclick =\"clearfields()\">Reset </button></center></td><td>");
            //out.println("<center><input style=\"visibility:hidden\"  value ="+ Deposit + "  type=\"text\" name=\"TestAmount\" on=\"copy()\" /><br /></center>");
            
            %>                  
            <center>Reference Number: <input type="text" name="tranRef" value="<%=MembershipNo%>"/><br /></center>

            <center><button type="button" onclick ="clearfields()">Reset Fields</button></center> <br />
            <%--center><button type="button" onclick ="check2(fee)">Proceed</button></center--%>
            <center><input type="submit" value="Proceed" name="ProcessCashPayment"></center>
            <center><button type="button" onclick ="document.location.href = '/PaymentSystemSwipe/secureUser/home.jsp'">Back to Main</button></center> <br />
            <%--<center><input type="submit" value="Proceed" name="IssueCard"></center>--%>
            <center><input style="visibility:hidden" type="text" name="TestAmount" on="copy()" /><br /></center>
            <center><input style="visibility:hidden" type="text" name="TotalAmount" on="copy()" /><br /></center>            

            <c:choose>
                <c:when test="${empty param.id}">
                    <center><input style="visibility:hidden" type="text" name="IdentityNumber" value = ""/><br/></center>
                </c:when>
                <c:otherwise>
                    <center><input style="visibility:hidden" type="text" name="IdentityNumber" value = "<c:out value="${param.id}"/>"/><br/></center>
                </c:otherwise>
            </c:choose>        
        </form>

					</div>
				</div>
			</div>
			<!-- Content end -->
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
