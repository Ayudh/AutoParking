<html>
  <head>
    <title>Home - Auto Parking System</title>
    <style>
      input {
        margin: 10px;
      }
    </style>
  </head>
  <body style="text-align: center;">
    <form action="home" method="POST">
      <input type="radio" name="option" value="park">Park<br>
      <input type="radio" name="option" value="unpark">Unpark<br>
      <input type="radio" name="option" value="status">Status of vehicle<br>
      Vehicle Number:<input type="text" name="vehicleid"><br>
      <input type="submit" value="Submit">
    </form>
    <div style="color:red">
    <% if(request.getAttribute("message") != null)
        out.print(request.getAttribute("message"));
    %>
    </div>
  </body>
</html>