<%-- 
    Document   : index
    Created on : Nov 23, 2011, 11:47:11 AM
    Author     : Akhona
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Member</title>
        <link rel="icon" type="image/ico" href="../TPIconTrans.ico"></link> 
        <link rel="shortcut icon" href="../TPIconTrans.ico"></link>
    </head>
    <body>
  
        <center><img src="ABLogo.jpg" alt="Logo"/></center> 
        <form name="newmember" method="post" action="../Add_Member">
            <p><center>Please enter the Customer details</center></p>
         
         <br> <br> <br>
            <center><table border="0">
        
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
                    <td ALIGN=RIGHT>Membership Number: <input type="text" name="Member_No"></td>
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
            </table></center>
        

             <%-- <center><button type="button" onclick ="document.location.href = 'http://localhost:8084/Test1.jsp'">Next</button></center> <br /> --%>
            
             <center><input type="submit" value="Next" name="Add_Member"></center>
             <center><button type="button" onclick ="document.location.href = '/PaymentSystemSwipe/secureUser/home.jsp'">Back to Main</button></center> <br />

             <%--center><button type="button" onclick ="document.location.href = '/PaymentSystemSwipe/Test1.jsp'">Next</button></center> <br /--%>
        </form>
   
        
    </body>
</html>
