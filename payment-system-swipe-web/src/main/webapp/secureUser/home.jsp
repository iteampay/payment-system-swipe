
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page language="java" import="java.sql.*" %>
<%@ page import="za.co.itempay.paymentsystemswipe.backend.Utils" %>

<%
    String userId = request.getRemoteUser();

    System.out.println("userId = " + userId);

    String beneficiaryName = "";
    String beneficiaryWebSite = "";
    String beneficiarySlogan = "";
    String beneficiaryID = "";
    long memberCount = 0L;
    String activeMembers = "";
    String inactiveMembers = "";
    String totalAvailable = "";

    final String READ_BENEFICIARY_SQL =

            "  SELECT beneficiary_name, beneficiary_web_site, beneficiary_slogan,                            " +
            "         b.beneficiary_id                                                                       " +
            "    FROM users a, beneficiaries b                                                               " +
            "   WHERE a.beneficiary_id = b.beneficiary_id                                                    " +
            "     AND user_id = ?                                                                            ";

    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
        stmt = Utils.TGDBcon.prepareStatement( READ_BENEFICIARY_SQL );
        stmt.setString(1, userId);
        rs = stmt.executeQuery();

        if (rs.next()) {
            beneficiaryName = rs.getString("beneficiary_name");
            beneficiaryWebSite = rs.getString("beneficiary_web_site");
            beneficiarySlogan = rs.getString("beneficiary_slogan");
            beneficiaryID = rs.getString("beneficiary_no");
        } else {
            // TODO exception handling
        }
    } catch (SQLException ex) {
        // TODO exception handling
        // TODO logger
        System.out.println(ex.getMessage());
    } finally {
        try {rs.close();} catch (Exception e){}
        try {stmt.close();} catch (Exception e){}
    }

    final String MEMBER_COUNT_SQL =

            " SELECT count(*) AS memeber_count                                                                  " +
            "   FROM customers c, card d, card_beneficiaries b                                                  " +
            "  WHERE c.customer_id = b.customer_id                                                              " +
            "    AND b.pan = d.pan                                                                              " +
            "    AND d.card_status = 2                                                                          " +
            "    AND b.beneficiary_id = ?                                                                       ;

    rs = null;
    try {
        stmt = Utils.TGDBcon.prepareStatement( MEMBER_COUNT_SQL );
        stmt.setString(1, beneficiaryID);
        rs = stmt.executeQuery();

        if (rs.next()) {
            memberCount = rs.getLong( "member_count" );
        } else {
            // TODO exception handling
        }

        rs = stmt.executeQuery(
                "select count(Distinct(B.PAN)) from T_BP_Customers A, T_BP_Card B, T_BP_Card_Beneficiaries C, T_EasyPay_Messages D where A.BP_Cust_ID = B.BP_Cust_ID and B.PAN = C.PAN and D.PAN = B.PAN and C.Beneficiary_No = '" + beneficiaryNo + "' and File_Date_Time >= dateadd(mm, -3, getdate()) and File_Date_Time <= getdate()");
        while (rst.next()) {
            activeMembers = rst.getString(1);
        }

        inactiveMembers = String.valueOf(Integer.parseInt(memberCount) - Integer.parseInt(activeMembers));

        rst = stmt.executeQuery("select count(*) from T_BP_Customer_Dependants A, T_BP_Customers B, T_BP_Card C, T_BP_Card_Beneficiaries D where A.BP_Cust_ID = B.BP_Cust_ID and B.BP_Cust_ID = C.BP_Cust_ID and C.PAN = D.PAN and D.Beneficiary_No = '" + beneficiaryNo + "'");
        while (rst.next()) {
            totalDependants = rst.getString(1);
        }
        rst = stmt.executeQuery("select count(*) from dbo.T_EasyPay_No_Track_New where  Status='Available' and Beneficiary_no= '" + beneficiaryNo + "'");
        while (rst.next()) {
            totalAvailable = rst.getString(1);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }



%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="/payment-system-swipe-web/<%=beneficiaryName%>/css/style.css" type="text/css"/>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Payment Management System</title>
    <link rel="icon" type="image/ico" href="/payment-system-swipe-web/<%=beneficiaryName%>/img/TPIconTrans.ico"></link>
    <link rel="shortcut icon" href="/payment-system-swipe-web/<%=beneficiaryName%>/img/Icon.ico"></link>
</head>
<body
">
<div id="wrapper">

    <!-- Header -->
    <div id="header">

        <!-- Your website name  -->
        <h1><a href="<%=beneficiaryWebSite%>"><%=beneficiaryName%>
        </a></h1>
        <!-- Your website name end -->

        <!-- Your slogan -->
        <h2><%=beneficiarySlogan%>
        </h2>
        <!-- Your slogan end -->

        <div id="header-arrow"></div>
    </div>
    <!-- Header end -->

    <!-- Menu -->
    <ul id="menu" class="cleaning-box">
        <li class="first"><a href="/payment-system-swipe-web/secureUser/home.jsp" class="active">Home</a></li>
        <li><a href="/payment-system-swipe-web/secureAdmin/MemberDetailsID.jsp">Add new member</a></li>
        <li><a href="/payment-system-swipe-web/secureAdmin/Members.jsp">Current Members</a></li>
        <li><a href="/payment-system-swipe-web/secureUser/Reports.jsp">Reports</a></li>
        <li><a href="/payment-system-swipe-web/secureAdmin/MemberCashPayment.jsp">Process a Payment</a></li>
        <li><a href="/payment-system-swipe-web/logout.jsp?logoff=true">Logoff <%= request.getRemoteUser()%>
        </a></li>

    </ul>


    <!-- Menu end -->


    <hr class="noscreen"/>

    <div id="content-box">
        <div id="content-box-in">

            <a name="skip-menu"></a>
            <!-- Content -->
            <div class="content-box">
                <div class="content-box-in">
                    <h3>Please select an operation from the links above</h3>

                    <center>
                        <table border="1">
                            <tr>
                                <th>Total Members</th>
                                <th>Active Members</th>
                                <th>Inactive Members</th>
                                <th>Total Available Card</th>
                            </tr>
                            <tr>
                                <td ALIGN=RIGHT><%=memberCount%>
                                </td>
                                <td ALIGN=RIGHT><%=activeMembers%>
                                </td>
                                <td ALIGN=RIGHT><%=inactiveMembers%>
                                </td>
                                <%
                                    if (totalAvailable.equals("0")) {
                                %>
                                <td ALIGN=RIGHT><%=totalAvailable%>
                                </td>
                                <%
                                } else {
                                %>
                                <td ALIGN=RIGHT><a
                                        href="/payment-system-swipe-web/secureUser/AvailaibleCard.jsp"><%=totalAvailable%>
                                </a></td>
                                <%
                                    }
                                %>
                            </tr>
                        </table>
                    </center>
                    </br>

                    </br>
                    <!-- <center><button type="button" style=" background-color:#BDBFC2; color:#0B0501; height: 60px; width:185px" onclick ="document.location.href = '/PaymentSystemSwipe/Test'"><font size="4" color="3168B0"><B>Test</B></font></button></center> -->

                    <!--<a href="/PaymentSystemSwipe/secureAdmin/BankDetails.jsp?id=8206185023087">Test Bank Details</a>>
                    <center><input style="visibility:hidden" type="text" name="IP" value = "<-%IP%>"/><br/></center-->


                </div>
            </div>
        </div>
        <!-- Content left end -->
    </div>
    <hr class="noscreen"/>

    <!-- Footer -->
    <div id="footer">
        <p class="left">Powered by <a class="b" href="http://www.touchpay.co.za">TouchPay</a><img
                src="/payment-system-swipe-web/<%=beneficiaryName%>/img/TPLogoSmall.png"></p>
    </div>
    <!-- Footer end -->
</div>

</body>
</html>
