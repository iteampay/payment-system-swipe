<%-- 
    Document   : Login
    Created on : Nov 24, 2011, 2:56:16 PM
    Author     : Akhona
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<script type="text/javascript">  
 //window.onload = submitform();   

function submitform()  
{  
  document.logon.submit();  
}  

</script> 
    <link rel="stylesheet" href="../css/style.css" type="text/css"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>

     <center><img src="ABLogo.jpg" alt="Logo"/></center>    
        <br>

        <form name="logon" method="post" action="MainScreen">
          <script>         
              submitform();     
          </script> 
            <%--p><center>Please enter logon details</center></p>
            <center>Username: <input type="Username" name="Username" /><br /></center>
            <center>Password: <input type="Password" name="Password" /><br /></center>

            <center><input type="submit" value="Proceed" name="MainScreen"></center--%>
            <%-- <center><button type="button" onclick ="document.location.href = 'http://localhost:8084/PaymentSystemSwipe/secureUser/home.jsp/'">Back to Main</button></center> <br /> --%>

        </form>
            
    <%-- <form method="POST" action="http://localhost:8084/PaymentSystemSwipe/secureUser/home.jsp">
   <center>Username: <input type="text" name="username" size="15" /> </center><br />
   <center>Password: <input type="password" name="password" size="15" /> </center><br />
  <div align="center">
    <p><input type="submit" value="Login" /></p>

  </div>
            <hr>

</form>--%>
        
    </body>
</html>
