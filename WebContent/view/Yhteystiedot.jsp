<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

    <!-- This code is only meant for previewing your Reflow design. -->

    <head>
	<link rel="stylesheet" href="../css/boilerplate.css">
	<link rel="stylesheet" href="../css/yhteystiedot.css">
	<link href='https://fonts.googleapis.com/css?family=Poiret+One' rel='stylesheet' type='text/css'>
	<script src="https://maps.googleapis.com/maps/api/js"></script>
	<script>
      function initialize() {
        var mapCanvas = document.getElementById('map');
        var mapOptions = {
          center: new google.maps.LatLng(60.170833, 24.9375),
          zoom: 15,
          mapTypeId: google.maps.MapTypeId.ROADMAP
        }
        var map = new google.maps.Map(mapCanvas, mapOptions)
      }
      google.maps.event.addDomListener(window, 'load', initialize);
    </script>
	<meta charset="utf-8">
	<meta name="viewport" content="initial-scale = 1.0,maximum-scale = 1.0">
    </head>
    <body>

    <div id="primaryContainer" class="primaryContainer clearfix">
     <div id="Navi" class="clearfix">
        <img id="image" src="../kuvat/Logo.png" class="image" />
        <a id="text" href="Etusivu.jsp">
        Etusivu
        </a>
        <a id="text1" href="../ListaaKaikilleServlet">
        Menu
        </a>
        <a id="text2" href="Yhteystiedot.jsp">
        Yhteystiedot
        </a>
        <a id="text3" href="Login.jsp">
        Login
        </a>
        <nav>
 			 <label for="drop" class="toggle">
 			 <img id="Navi1" src="../kuvat/ddicon.png">
 			 </label>
 			 <input type="checkbox" id="drop"/>
 			<ul class="menu">
  			 <li><a href="Etusivu.jsp" id="text7">Etusivu</a></li>
 		   	 <li><a href="../ListaaKaikilleServlet" id="text7">Menu</a></li>
			 <li><a href="Yhteystiedot.jsp" id="text8">Yhteystiedot</a></li>
  		 	 <li><a href="Login.jsp" id="text7">Kirjaudu / Rekisteröidy</a></li>
 		    </ul>
		</nav>
        <div id="box" class="clearfix">
        </div>
        </div>
         <div id="box1" class="clearfix">
         <div id="map"></div>
        </div>
        <div id="box2" class="clearfix">
            <p id="text4">
            Yhteystiedot<br /><br />Osoite&#x3a; Diipadaapakatu 1, 00500 Helsinki<br />Puh&#x3a; 040 1234 4567<br />
            </p>
        </div>
        <div id="box3" class="clearfix">
        </div>
        <p id="text5">
        Yrityksen<br />Tiedot<br />Y-tunnus<br />Diipadaapaa
        </p>
    </div>
    
    </body>
</html>