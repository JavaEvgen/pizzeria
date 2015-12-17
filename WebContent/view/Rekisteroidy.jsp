<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<jsp:useBean id="errors4" scope="request" type="java.util.Map" class="java.util.HashMap" />
<html>

    <!-- This code is only meant for previewing your Reflow design. -->

    <head>
	<link rel="stylesheet" href="../css/boilerplate.css">
	<link rel="stylesheet" href="css/boilerplate.css">
	<link rel="stylesheet" href="../css/rekisteroidy.css">
	<link rel="stylesheet" href="css/rekisteroidy.css">
	<link href='https://fonts.googleapis.com/css?family=Poiret+One' rel='stylesheet' type='text/css'>
	<meta charset="utf-8">
	<meta name="viewport" content="initial-scale = 1.0,maximum-scale = 1.0">
    </head>
    <body>
	
    <div id="primaryContainer" class="primaryContainer clearfix">
    <div id="Navi" class="clearfix">
        <img id="image" src="kuvat/Logo.png" class="image" />
        <a id="text" href="view/Etusivu.jsp">
        Etusivu
        </a>
        <a id="text1" href="ListaaKaikilleServlet">
        Menu
        </a>
        <a id="text2" href="view/Yhteystiedot.jsp">
        Yhteystiedot
        </a>
        <a id="text3" href="view/Login.jsp">
        Login
        </a>
        <nav>
 			 <label for="drop" class="toggle">
 			 <img id="Navi1" src="kuvat/ddicon.png">
 			 </label>
 			 <input type="checkbox" id="drop"/>
 			<ul class="menu">
  			 <li><a href="view/Etusivu.jsp" id="text13">Etusivu</a></li>
 		   	 <li><a href="../ListaaKaikilleServlet" id="text13">Menu</a></li>
			 <li><a href="Yhteystiedot.jsp" id="text13">Yhteystiedot</a></li>
  		 	 <li><a href="Login.jsp" id="text14">Kirjaudu / Rekisteröidy</a></li>
 		    </ul>
		</nav>
        <div id="box" class="clearfix">
        </div>
        </div>
        <div id="box1" class="clearfix">
            <p id="text4">
            REKISTER&Ouml;IDY
            </p>
        </div>
        <div id="box2" class="clearfix">
        <form method="post" action="RekisterointiServlet">
            <label id="formgroup">
                <p id="text5">
                Sukunimi&#x3a;
                </p>
                <input id="textinput" type="text" value="" name="sukunimi"></input>
                                <p id="text5"><%
						if (errors4.containsKey("sukunimi")){
							out.println(errors4.get("sukunimi"));
						}
					%></p>
            </label>
            <label id="formgroup1">
                <p id="text6">
                Etunimi&#x3a;
                </p>
                <input id="textinput1" type="text" value="" name="etunimi"></input>
                                <p id="text6"><%
						if (errors4.containsKey("etunimi")){
							out.println(errors4.get("etunimi"));
						}
					%></p>
            </label>
            <label id="formgroup2">
                <p id="Puhelin_numero_">
                Puhelin numero&#x3a;
                </p>
                <input id="textinput2" type="text" value="" name="puhelin_nro"></input>
                                <p id="Puhelin_numero_"><%
						if (errors4.containsKey("puhelin")){
							out.println(errors4.get("puhelin"));
						}
					%></p>
            </label>
            <label id="formgroup3">
                <p id="text7">
                S&auml;hk&ouml;posti&#x3a;
                </p>
                <input id="textinput3" type="text" value="" name="email"></input>
                                <p id="text7"><%
						if (errors4.containsKey("email")){
							out.println(errors4.get("email"));
						}
					%></p>
            </label>
            <label id="formgroup4">
                <p id="text8">
                Katuosoite&#x3a;
                </p>
                <input id="textinput4" type="text" value="" name="katuosoite"></input>
                <p id="text8"><%
						if (errors4.containsKey("osoite")){
							out.println(errors4.get("osoite"));
						}
					%></p>
            </label>
            <label id="formgroup5">
                <p id="text9">
                Postinumero&#x3a;
                </p>
                <input id="textinput5" type="text" value="" name="postinumero"></input>
                <p id="text9"><%
						if (errors4.containsKey("posti")){
							out.println(errors4.get("posti"));
						}
					%></p>
            </label>
            <label id="formgroup6">
                <p id="text10">
                K&auml;ytt&auml;j&auml;tunnus&#x3a;
                </p>
                <input id="textinput6" type="text" value="" name="kayttajatunnus"></input>
                <p id="text10"><%
						if (errors4.containsKey("tunnus")){
							out.println(errors4.get("tunnus"));
						}
					%></p>
            </label>
            <label id="formgroup7">
                <p id="text11">
                Salasana&#x3a;
                </p>
                <input id="textinput7" type="password" value="" name="salasana"></input>
                <p id="text11"><%
						if (errors4.containsKey("salasana")){
							out.println(errors4.get("salasana"));
						}
					%></p>
            </label>
            <input id="input" type="submit" value="Rekister&ouml;idy"></input>
        </form>    
        </div>
        <div id="box3" class="clearfix">
        </div>
        <p id="text12">
        Yrityksen<br />Tiedot<br />Y-tunnus<br />Diipadaapaa
        </p>
    </div>
    </body>
</html>
