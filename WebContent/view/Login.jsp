<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

    <!-- This code is only meant for previewing your Reflow design. -->

    <head>
	<link rel="stylesheet" href="css/boilerplate.css">
	<link rel="stylesheet" href="css/login.css">
	<link rel="stylesheet" href="../css/boilerplate.css">
	<link rel="stylesheet" href="../css/login.css">
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
  			 <li><a href="Etusivu.jsp" id="text11">Etusivu</a></li>
 		   	 <li><a href="../ListaaKaikilleServlet" id="text11">Menu</a></li>
			 <li><a href="Yhteystiedot.jsp" id="text11">Yhteystiedot</a></li>
  		 	 <li><a href="Login.jsp" id="text12">Kirjaudu / Rekisteröidy</a></li>
 		    </ul>
		</nav>
        <div id="box" class="clearfix">
        </div>
        </div>
       <div id="box1" class="clearfix">
            <p id="text4">
            KIRJAUDU&#x2f;REKISTER&Ouml;IDY
            </p>
        </div>
        <div id="box2" class="clearfix">
            <p id="text5">
            KIRJAUDU
            </p>
            <form method="post" action="../LoginServlet">
            <label id="formgroup">
                <p id="text6">
                K&auml;ytt&auml;j&auml;tunnus&#x3a;
                </p>
                <input id="textinput" type="text" value="" name="user"></input>
            </label>
            <label id="formgroup1">
                <p id="text7">
                Salasana&#x3a;
                </p>
                <input id="textinput1" type="password" value="" name="password"></input>
            </label>
            
            <input id="input" type="submit" value="Kirjaudu"></input>
            </form>
        </div>
        <div id="box3" class="clearfix">
            <p id="text8">
            REKISTER&Ouml;IDY
            </p>
            <p id="text9">
            Tilataksesi tuotteita Pizzicatosta Sinun tarvitsee rekister&ouml;ity&auml; palveluun&#x21;
            </p>
            <a href="../RekisterointiServlet">
                <input id="input1" type="button" value="Rekister&ouml;idy"></input>
            </a>
        </div>
        <div id="box4" class="clearfix">
        </div>
        <p id="text10">
        Yrityksen<br />Tiedot<br />Y-tunnus<br />Diipadaapaa
        </p>
    </div>
    
    </body>
</html>
