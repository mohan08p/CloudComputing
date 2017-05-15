<!DOCTYPE html>
<html lang="en">
<head>
  <title>Cloud Computing Mini Project</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style>
    /* Remove the navbar's default rounded borders and increase the bottom margin */
    .navbar {
      margin-bottom: 50px;
      border-radius: 0;
    }

    /* Remove the jumbotron's default bottom margin */
     .jumbotron {
      margin-bottom: 0;
    }

    /* Add a gray background color and some padding to the footer */
    footer {
      background-color: #f2f2f2;
      padding: 25px;
    }
  </style>
</head>
<body style="background-color:#f2f2f2">

<div class="jumbotron" style="background-color:rgb(34,34,34); color:white">
  <div class="container text-center">
    <h2>Cloud Computing Mini Project</h2>
    <h5><i> - Rohit Sachdev, Kunal Tolani, Nirbhay Pherwani</i></h5>
  </div>
</div>
<!--
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Logo</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Home</a></li>
        <li><a href="#">Products</a></li>
        <li><a href="#">Deals</a></li>
        <li><a href="#">Stores</a></li>
        <li><a href="#">Contact</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#"><span class="glyphicon glyphicon-user"></span> Your Account</a></li>
        <li><a href="#"><span class="glyphicon glyphicon-shopping-cart"></span> Cart</a></li>
      </ul>
    </div>
  </div>
</nav>
-->
<br><br>
<div class="container">
  <div class="row">
    <div class="col-sm-4">
      <div class="panel panel-primary">
        <div class="panel-heading">Raspberry pi-hole</div>
        <div class="panel-body"><img src="pihole.jpg" class="img-responsive" style="width:100%" alt="Image"></div>
        <div class="panel-footer">A black hole for Internet advertisements</div>
        <a href="http://10.42.0.47/admin"><button type="button" class="btn btn-primary form-control">View Pi-Hole Dashboard</button></a>
      </div>
    </div>

    <div class="col-sm-4">
      <div class="panel panel-primary">
        <div class="panel-heading">Weaved + Docker</div>
        <div class="panel-body"><img src="DW.png" class="img-responsive" style="width:100%" alt="Image"></div>
        <div class="panel-footer">Docker allows to create micro-services or full fledged linux distributions to run in containers.

Weave is a tool that manages virtual networking for Docker containers deployed across multiple hosts. We are using 2 main features here;

    The ability to put containers on various hosts and attach them to the same subnet (virtual layer-2 broadcast domain effectively)
    Automatic path discovery for containers connected to the Weave network.</div>
      </div>
    </div>
    <div class="col-sm-4">
      <div class="panel panel-primary">
        <div class="panel-heading">TorrentBox</div>
        <div class="panel-body"><img src="torrentbox.png" class="img-responsive" style="width:100%" alt="Image"></div>
        <div class="panel-footer">Torrentbox manages all your torrents, run 24 hours and cost you about 10$ a year to operate. The Raspberry Pi Torrent box can run on the same Pi that is working as your low powered NAS or Web Server.</div>
        <a href="http://10.42.0.47:9091/"><button type="button" class="btn btn-primary form-control">View TorrentBox</button></a>

      </div>
    </div>
  </div>
</div><br>

<div class="container">
  <div class="row">
    <div class="col-sm-4">
      <div class="panel panel-primary">
        <div class="panel-heading">Tonido</div>
        <div class="panel-body"><img src="tonido.png" class="img-responsive" style="width:100%" alt="Image"></div>
        <div class="panel-footer">Turn your computer into your own personal cloud server. Sync and remotely access files via web browser, mobile devices. Ideal as a personal cloud server or a home cloud server. Your files reside on your computer not on a third party server. </div>
        <a href="http://10.42.0.47:10001"><button type="button" class="btn btn-primary form-control">View Tonido</button></a>
      </div>

    </div>
    <div class="col-sm-4">
      <div class="panel panel-primary">
        <div class="panel-heading">Samba NAS </div>
        <div class="panel-body"><img src="samba.jpg" class="img-responsive" style="width:100%" alt="Image"></div>
        <div class="panel-footer">Mix together one Raspberry Pi and a sprinkle of cheap external hard drives and you have the recipe for an ultra-low-power and always-on network storage device.</div>
        <a href="smb://raspberrypi/shared"><button type="button" class="btn btn-primary form-control">View Rpi Shared NAS (Local)</button></a>
      </div>
    </div>

    
  </div>
</div><br><br>


</body>
</html>
