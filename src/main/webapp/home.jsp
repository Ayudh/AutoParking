<html>

<head>
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/css/materialize.min.css">
  <style>
    body {
      display: flex;
      min-height: 100vh;
      flex-direction: column;
    }

    main {
      flex: 1 0 auto;
    }

    body {
      background-image: url("images/background.jpg");
      background-repeat: no-repeat;
      background-size: cover;
    }

    .input-field input[type=date]:focus + label,
    .input-field input[type=text]:focus + label,
    .input-field input[type=email]:focus + label,
    .input-field input[type=password]:focus + label {
      color: #e91e63;
    }

    .input-field input[type=date]:focus,
    .input-field input[type=text]:focus,
    .input-field input[type=email]:focus,
    .input-field input[type=password]:focus {
      border-bottom: 2px solid #e91e63;
      box-shadow: none;
    }
  </style>
</head>

<body>
  <div class="section"></div>
  <main>
    <center>

      <div class="section teal-text">
          <h1>Auto Parking</h1>
      </div>

      <h5 class="white-text">
          <% if(request.getAttribute("message") != null)
          out.print(request.getAttribute("message"));
          else
          out.print("Welcome " + request.getSession().getAttribute("username").toString().toUpperCase());
          %>
      </h5>
      <div class="section"></div>

      <div class="container">
        <div class="z-depth-2 grey lighten-4 row" style="display: inline-block; padding: 32px 48px 0px 48px; border: 1px solid #EEE; width: 30%;">

          <form class="col s12" action="home" method="post">
            <div class='row'>
              <div class='col s12'>
              </div>
            </div>

            <div class="row left-align">
              <input id="choice_1" class="with-gap" name="option" type="radio" value="park" checked/>   
              <label for="choice_1">Park</label>
            </div>
            <div class="row left-align">
              <input id="choice_2" class="with-gap" name="option" type="radio" value="unpark"/>   
              <label for="choice_2">Unpark</label>
            </div>
            <div class="row left-align">
              <input id="choice_3" class="with-gap" name="option" type="radio" value="status"/>   
              <label for="choice_3">Status</label>
            </div>
                
            <div class='row'>
              <div class='input-field col s12'>
                <input class='validate' type='text' name='vehicleid' id='vehicleid' required/>
                <label for='vehicleid'>Enter Vehicle ID</label>
              </div>
            </div>

            <br />
            <center>
              <div class='row'>
                <button type="submit" class="col s12 btn btn-large waves-effect indigo">Submit</button>
              </div>
            </center>
          </form>
          <div class="row">
              <a href="logout"><button class="col s12 btn btn-large waves-effect indigo">Logout</button></a>
            </div>
        </div>
      </div>
    </center>

    <div class="section"></div>
    <div class="section"></div>
  </main>

  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.1/jquery.min.js"></script>
  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/js/materialize.min.js"></script>
</body>

</html>