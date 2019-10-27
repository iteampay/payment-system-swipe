<%-- 
    Document   : login
    Created on : 2012/01/09, 11:47:23
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%--html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="j_security_check" method="POST">
            Username:<input type="text" name="j_username"><br>
            Password:<input type="password" name="j_password">
            <input type="submit" value="Login">
        </form>
        
        
    </body>
</html>
<html--%>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="icon" type="image/ico" href="../TPIconTrans.ico"></link>
        <link rel="shortcut icon" href="../TPIconTrans.ico"></link>
    </head>
    <body  OnLoad="document.login.j_username.focus();">

     <center><img src="../assets/images/ab-logo.jpg" alt="Logo"/></center>
     <br>
        <form name="login" action="j_security_check" method="post">
            <p><center>Please enter logon details</center></p>
            <center>Username: <input type="text" name="j_username" /><br /></center>
            <center>Password: <input type="password" name="j_password" /><br /></center>

            <center><input type="submit" value="Proceed" name="MainScreen"></center>
        </form>
    </body>
</html>
