<%-- 
    Document   : home
    Created on : Nov 24, 2011, 12:50:50 PM
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
        function GetSelectedRadios() {
                typechosen = "none"
                if(document.getElementById('Traditional').checked)
                {   typechosen = "Traditional" }
                else if(document.getElementById('Normal').checked)
                {   typechosen = "Normal" }  
                document.PolicyEdit.SelectedType.value = typechosen
                paymenthosen = "none"
                if(document.getElementById('AEDO').checked)
                {   paymenthosen = "AEDO" }
                else if(document.getElementById('NAEDO').checked)
                {   paymenthosen = "NAEDO" }  
                else if(document.getElementById('TouchPay').checked)
                {   paymenthosen = "TouchPay" }  
                else if(document.getElementById('Cash').checked)
                {   paymenthosen = "Cash" }
                document.PolicyEdit.SelectedPayment.value = paymenthosen
                document.PolicyEdit.submit();
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
                }
                else{
                    var amount = document.PolicyEdit.TestAmount.value;
                    amount = (amount *10) + (key/100);
                    
                    var roundedamount = Math.round(amount*100)/100
                    var troundedamount = "" + roundedamount;
                }
                
                var pos = troundedamount.indexOf(".");
                //alert("pos: " + pos);
                var len = troundedamount.length;
                //alert("len: " + len);
                
                if ((key==0) && (pos != -1)){
                    troundedamount = troundedamount + "0";
                }
                if (pos == -1){
                    troundedamount = troundedamount + ".00";
                }
                    
                document.PolicyEdit.CoverAmount.value = troundedamount;
                document.PolicyEdit.TestAmount.value = troundedamount;
            }
            function validatefee(evt) {
                var theEvent = evt || window.event;
                var key = theEvent.keyCode || theEvent.which;
                key = String.fromCharCode( key );
                //alert(key);
                var regex = /[0-9]|\./;
                if( !regex.test(key) ) {
                    theEvent.returnValue = false;
                    if(theEvent.preventDefault) theEvent.preventDefault();
                    //alert('here 1');
                }
                else{
                    var fee = document.PolicyEdit.TestFee.value;
                    fee = (fee *10) + (key/100);
                    
                    var roundedfee = Math.round(fee*100)/100
                    var troundedfee = "" + roundedfee;
                }
                
                var pos = troundedfee.indexOf(".");
                //alert("pos: " + pos);
                var len = troundedfee.length;
                //alert("len: " + len);
                
                if ((key==0) && (pos != -1)){
                    troundedfee = troundedfee + "0";
                }
                if (pos == -1){
                    troundedfee = troundedfee + ".00";
                }
                    
                document.PolicyEdit.AdminFee.value = troundedfee;
                document.PolicyEdit.TestFee.value = troundedfee;
            }
            function copy() {
                document.PolicyEdit.CoverAmount.value = troundedamount;
                document.PolicyEdit.TestAmount.value = troundedamount;
                document.PolicyEdit.AdminFee.value = troundedfee;
                document.PolicyEdit.TestFee.value = troundedfee;
            }
            function clearfields() {
                document.PolicyEdit.CoverAmount.value = "0";
                document.PolicyEdit.TestAmount.value = "";
                document.PolicyEdit.AdminFee.value = "0";
                document.PolicyEdit.TestFee.value = "";
            }
            function getvalues()  
            {
                if(document.PolicyEdit.Type.value == "Traditional")
                {   document.getElementById('Traditional').checked = true }
                else if(document.PolicyEdit.Type.value == "Normal")
                {   document.getElementById('Normal').checked = true }
                if(document.PolicyEdit.Method.value == "TouchPay")
                {   document.getElementById('TouchPay').checked = true }
                else if(document.PolicyEdit.Method.value == "ADO")
                {   document.getElementById('ADO').checked = true }
                else if(document.PolicyEdit.Method.value == "Cash")
                {   document.getElementById('Cash').checked = true }
                document.PolicyEdit.CoverAmount.value = document.PolicyEdit.Cover.value;
                document.PolicyEdit.TestAmount.value = document.PolicyEdit.Cover.value;
                document.PolicyEdit.AdminFee.value = document.PolicyEdit.Fee.value;
                document.PolicyEdit.TestFee.value = document.PolicyEdit.Fee.value;            
            }
    </script>            
    
    <link rel="stylesheet" href="/PaymentSystemSwipe/<%=Beneficiary_Name%>/css/style.css" type="text/css"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/ico" href="/PaymentSystemSwipe/<%=Beneficiary_Name%>/img/TPIconTrans.ico"></link> 
        <link rel="shortcut icon" href="/PaymentSystemSwipe/<%=Beneficiary_Name%>/img/Icon.ico"></link>
        <title>Policy Information</title>
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
					<h3>Please enter the policy details</h3>
                                        
        <form name="PolicyEdit" method="post" action="../PolicyEdit">
            <center><table border="1"> 
                <tr>
                    <th>Policy Type</th>
                    <th>Payment Method</th>
                </tr>
                <tr>
                    <td ALIGN=LEFT><input type="radio" name="policytype" id="Traditional" value="Traditional">Traditional</td>
                    <td ALIGN=LEFT><input type="radio" name="paymentmethod" id="TouchPay"  value="TouchPay">TouchPay</td>
                </tr>
                <tr>
                    <td ALIGN=LEFT><input type="radio" name="policytype" id="Normal"  value="Normal">Normal</td>
                    <td ALIGN=LEFT><input type="radio" name="paymentmethod" id="AEDO" value="AEDO">AEDO</td>
                </tr>
                <tr>
                    <td ALIGN=LEFT></td>
                    <td ALIGN=LEFT><input type="radio" name="paymentmethod" id="NAEDO"  value="NAEDO">NAEDO</td>
                </tr>
                <tr>
                    <td ALIGN=LEFT></td>
                    <td ALIGN=LEFT><input type="radio" name="paymentmethod" id="Cash"  value="Cash">Cash</td>
                </tr>
            </table></center>
            <br>
            <center><table border="0"> 
                <tr>
                    <td ALIGN=RIGHT>Cover Amount: <input value ="0" type="text" name="CoverAmount" onkeyup="validate(event)"/></td>
                    <td ALIGN=RIGHT><center>Admin Fee <input value ="0" type="text" name="AdminFee" onkeyup="validatefee(event)"/></td>
                </tr>
            </table></center>

            
            <center><button type="button" onclick ="GetSelectedRadios();">Proceed</button></center>
            <center><button type="button" onclick ="clearfields()">Reset Fields</button></center>
            <center><button type="button" onclick ="document.location.href = '/PaymentSystemSwipe/secureUser/home.jsp'">Back to Main</button></center> <br />
            <center><input style="visibility:hidden" type="text" name="SelectedType"/><br/></center>
            <center><input style="visibility:hidden" type="text" name="SelectedPayment"/><br/></center>
            <c:choose>
                <c:when test="${empty param.bpcustid}">
                    <center><input style="visibility:hidden" type="text" name="BP_Cust_ID" value = ""/><br/></center>
                </c:when>
                <c:otherwise>
                    <center><input style="visibility:hidden" type="text" name="BP_Cust_ID" value = "<c:out value="${param.bpcustid}"/>"/><br/></center>
                </c:otherwise>
            </c:choose>        
            <c:choose>
                <c:when test="${empty param.pan}">
                    <center><input style="visibility:hidden" type="text" name="PAN" value = ""/><br/></center>
                </c:when>
                <c:otherwise>
                    <center><input style="visibility:hidden" type="text" name="PAN" value = "<c:out value="${param.pan}"/>"/><br/></center>
                </c:otherwise>
            </c:choose>     
            <c:choose>
                <c:when test="${empty param.type}">
                    <center><input style="visibility:hidden" type="text" name="Type" value = ""/><br/></center>
                </c:when>
                <c:otherwise>
                    <center><input style="visibility:hidden" type="text" name="Type" value = "<c:out value="${param.type}"/>"/><br/></center>
                </c:otherwise>
            </c:choose>     
            <c:choose>
                <c:when test="${empty param.method}">
                    <center><input style="visibility:hidden" type="text" name="Method" value = ""/><br/></center>
                </c:when>
                <c:otherwise>
                    <center><input style="visibility:hidden" type="text" name="Method" value = "<c:out value="${param.method}"/>"/><br/></center>
                </c:otherwise>
            </c:choose>     
            <c:choose>
                <c:when test="${empty param.cover}">
                    <center><input style="visibility:hidden" type="text" name="Cover" value = ""/><br/></center>
                </c:when>
                <c:otherwise>
                    <center><input style="visibility:hidden" type="text" name="Cover" value = "<c:out value="${param.cover}"/>"/><br/></center>
                </c:otherwise>
            </c:choose>     
            <c:choose>
                <c:when test="${empty param.fee}">
                    <center><input style="visibility:hidden" type="text" name="Fee" value = ""/><br/></center>
                </c:when>
                <c:otherwise>
                    <center><input style="visibility:hidden" type="text" name="Fee" value = "<c:out value="${param.fee}"/>"/><br/></center>
                </c:otherwise>
            </c:choose>     
            <center><input style="visibility:hidden" type="text" name="TestAmount" on="copy()" /><br /></center>
            <center><input style="visibility:hidden" type="text" name="TestFee" on="copy()" /><br /></center>
            <script>         
                getvalues();     
            </script> 
            
        </form>
                                        
					</div>
				</div>
			</div>
			<!-- Content end -->
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
