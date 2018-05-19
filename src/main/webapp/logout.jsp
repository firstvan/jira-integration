<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Kijelentkezett felhasználó</title>
</head>
<body>
<%@ page session="true"%>

'<%=request.getRemoteUser()%>' nevű felhasználó sikeresen kijelentkezett.

<% session.invalidate(); %>

<br/><br/>
<a href="/order-manager-web/login.xhtml">Bejelentkezés</a>
</body>
</html>
