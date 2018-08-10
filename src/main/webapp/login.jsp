<html>
<head>
<title>Auto Parking - Login</title>
</head>
<body>
<form action="login" method="post">
    User Name:    <input type="text" name="username">
    Password:    <input type="password" name="password">
    <input type="submit" value="submit">
</form>
<div style="color:red">
<% if(request.getAttribute("message") != null)
    out.print(request.getAttribute("message"));
%>
</div>
</body>
</html>