<!-- 24240100006770 -->
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AJAX</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/css/materialize.min.css">
  <link rel="stylesheet" type="text/css" media="screen" href="" />
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <style>
    body {
      background-image: url("images/background.jpg");
      background-repeat: no-repeat;
      background-size: cover;
      color: white;
    }
    .left-div {
      height: 100%;
      width: 30%;
      left: 0;
      position: fixed;
      z-index: 1;
      top: 0;
      overflow-x: hidden;
      padding-top: 20px;
    }
    .right-div {
      height: 100%;
      width: 70%;
      right: 0;
      position: fixed;
      z-index: 1;
      top: 0;
      overflow-x: hidden;
      padding-top: 20px;
    }
    .glow {
      border-style: solid;
      border-color: rgb(81, 175, 81);
      -webkit-transition: border 0.1s linear, box-shadow 0.1s linear;
      -moz-transition: border 0.1s linear, box-shadow 0.1s linear;
      transition: border 0.1s linear, box-shadow 0.1s linear;
    }
  </style>
</head>
<body>
  <center>
    <h4 class="teal-text">Autoparking</h4>
  </center>
  <div class="left left-div valign-wrapper">
    <div class="z-depth-5 lighten-4 row" style="display: inline-block; width:60%;">
        <form class="col s12" id="my-form">
          <div class='row'>
            <div class='col s12'>
            </div>
          </div>
          <center>
            <div>
              <div class="row left-align">
                <input id="choice_1" class="with-gap" name="option" type="radio" value="park"
                <% if(request.getSession().getAttribute("userrole").toString().equals("1")) out.print("disabled"); %>/>   
                <label for="choice_1">Park</label>
              </div>
              <div class="row left-align">
                <input id="choice_2" class="with-gap" name="option" type="radio" value="unpark"
                <% if(request.getSession().getAttribute("userrole").toString().equals("0")) out.print("disabled"); %>/>   
                <label for="choice_2">Unpark</label>
              </div>
            </div>
          </center>
          <div class='row'>
            <div class='input-field col s12'>
              <input class='validate' type='text' name='vehicleid' id='vehicleid'/>
              <label for='vehicleid'>Enter Vehicle ID</label>
            </div>
          </div>
          <center>
            <div class='row'>
              <button type="submit" class="col s12 btn btn-large waves-effect indigo">Submit</button><br/>
              <button type="reset" onclick="location.href='logout'" class="col s12 btn btn-large waves-effect indigo" style="margin-top:20px;">Logout</button>
            </div>
          </center>
        </form>
        <div class="row">
        </div>
      </div>
  </div>

  <div class="right right-div valign-wrapper">
    <div class="z-depth-5 lighten-4 row" style="display: inline-block; width:60%; padding-left: 10px;">
        <table>
            <thead>
              <tr>
                  <th>Vehicle ID</th>
                  <th>Slot Number</th>
                  <th>In-time</th>
              </tr>
            </thead>
            <tbody id="table-body">
              
            </tbody>
            <% if(request.getSession().getAttribute("userrole").toString().equals("1") || request.getSession().getAttribute("userrole").toString().equals("0")) {
              out.print("No Permissions to view.");
              out.print("<script>$('#table-body').hide()</script>");
            }  %>
          </table>
      <div class="row">
      </div>
    </div>
  </div>

  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/js/materialize.min.js"></script>
  <script src="scripts/script.js"></script>
</body>
</html>