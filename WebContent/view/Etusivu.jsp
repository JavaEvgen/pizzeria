<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

    <!-- This code is only meant for previewing your Reflow design. -->

    <head>
	<link rel="stylesheet" href="../css/boilerplate.css">
	<link rel="stylesheet" href="../css/home.css">
	<link href='https://fonts.googleapis.com/css?family=Poiret+One' rel='stylesheet' type='text/css'>
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
  			 <li><a href="Etusivu.jsp" id="text6">Etusivu</a></li>
 		   	 <li><a href="../ListaaKaikilleServlet" id="text7">Menu</a></li>
			 <li><a href="Yhteystiedot.jsp" id="text7">Yhteystiedot</a></li>
  		 	 <li><a href="Login.jsp" id="text7">Kirjaudu / Rekisteröidy</a></li>
 		    </ul>
		</nav>
            <div id="box" class="clearfix">
            </div>
        </div>
        <img id="image1" src="../kuvat/pizza1.jpg" class="image" />
        <img id="image2" src="../kuvat/pizza3.jpg" class="image" />
        <div id="box1" class="clearfix">
            <p id="text4">
            Tervetuloa Pizzicatoon&#x21;<br /><br /><br />
            </p>
        </div>
        <div id="box2" class="clearfix">
        </div>
        <p id="text5">
        Yrityksen<br />Tiedot<br />Y-tunnus<br />Diipadaapaa
        </p>
    </div>
    </body>
</html>