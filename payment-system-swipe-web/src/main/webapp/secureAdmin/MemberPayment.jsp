<%-- 
    Document   : index
    Created on : 2011/11/08, 07:31:16
    Author     : Administrator
--%>

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
<!DOCTYPE html>
<html>
    <link rel="stylesheet" href="/PaymentSystemSwipe/<%=Beneficiary_Name%>/css/style.css" type="text/css"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment Page</title>
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
                    document.payment.POSAmount.value = document.payment.TestAmount.value;
                }
                else{
                    var amount = document.payment.TestAmount.value;
                    amount = (amount *10) + (key/100);
                    
                    fee = amount * .05;
                    
                    var roundedamount = Math.round(amount*100)/100
                    var troundedamount = "" + roundedamount;

                    var roundedfee = Math.round(fee*100)/100
                    troundedfee = "" + roundedfee;
                    
                    var totalamount = roundedamount + roundedfee;
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
                    
                document.payment.POSAmount.value = troundedamount;
                document.payment.TestAmount.value = troundedamount;
                document.payment.TotalAmount.value = ttotalamount;
            }
            function copy() {
                document.payment.POSAmount.value = troundedamount;
                document.payment.TestAmount.value = troundedamount;
                document.payment.TotalAmount.value = ttotalamount;
            }
            function clearfields() {
                document.payment.POSAmount.value = "0";
                document.payment.tranRef.value = "";
                document.payment.TestAmount.value = "";
                document.payment.TotalAmount.value = "";
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
                    document.payment.submit();
            }
            function check(){if(!agree){showWin();}}
            window.onfocus=function(){if(win&&!win.closed){win.focus();}}
            
            function check2(fee){
                if (confirm('Do you accept a transaction fee of R' + troundedfee + '?')){
                    document.payment.submit();
                    //window.location.href = 'http://someurl.com';
                } else {
                    window.location.href = '../NotAccepted.html';
                    return false;
                }
            }            
        </script>
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
					<h3>Please enter the transaction details</h3>
                                        
                                        
        <form name="payment" method="post" action="../Payment">
            <center>Amount: <input value ="0" type="text" name="POSAmount" onkeyup="validate(event)" /><br /></center>
            <center>Reference Number: <input type="text" name="tranRef" /><br /></center>

            <center><button type="button" onclick ="clearfields()">Reset Fields</button></center> <br />
            <center><button type="button" onclick ="check2(fee)">Proceed</button></center>
            <center><button type="button" onclick ="document.location.href = '/PaymentSystemSwipe/secureUser/home.jsp'">Back to Main</button></center> <br />

            <center><input style="visibility:hidden" type="text" name="TestAmount" on="copy()" /><br /></center>
            <center><input style="visibility:hidden" type="text" name="TotalAmount" on="copy()" /><br /></center>
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
