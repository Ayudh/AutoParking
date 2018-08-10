<html>
  <head>
    <title>Home - Auto Parking System</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <style>
      input {
        margin: 10px;
      }
    </style>
  </head>
  <body style="text-align: center;">
    <form action="home" method="POST">
      <input type="radio" name="option" value="park" checked>Park<br>
      <input type="radio" name="option" value="unpark">Unpark<br>
      <input type="radio" name="option" value="status">Status of vehicle<br>
      <input type="radio" id="radio_erase" name="option" value="erase">Clear<br>
      Vehicle Number:<input type="text" name="vehicleid"><br>
      <input type="submit" value="Submit">
    </form>
    <div style="color:red">
    <% if(request.getAttribute("message") != null)
        out.print(request.getAttribute("message"));
    %>
    </div>
    <script>
      $(document).ready(() => {
        console.log("ready function");
        $('#radio_erase').click(() => {
          console.log("clicked");
        })
      });
    </script>
  </body>
</html>