<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="pizzicato_ibizza.model.Tilausrivi"%>
<jsp:useBean id="errors3" scope="request" type="java.util.Map" class="java.util.HashMap" />
        
<jsp:useBean id="tilaus" type="pizzicato_ibizza.model.Tilaus"
		scope="session" />
		
<%@ page import="java.text.NumberFormat" %>
<%
    NumberFormat nf = NumberFormat.getInstance();
    nf.setMaximumFractionDigits(2);
    nf.setMinimumFractionDigits(2);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/boilerplate.css">
	<link rel="stylesheet" href="css/tilaus.css">
	<link rel="stylesheet" href="../css/tilaus.css">
	<link href='https://fonts.googleapis.com/css?family=Poiret+One' rel='stylesheet' type='text/css'>
	<meta charset="utf-8">
	<meta name="viewport" content="initial-scale = 1.0,maximum-scale = 1.0">
<title>Tilaus</title>
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
  			 <li><a href="view/Etusivu.jsp" id="text7">Etusivu</a></li>
 		   	 <li><a href="ListaaKaikilleServlet" id="text9">Menu</a></li>
			 <li><a href="view/Yhteystiedot.jsp" id="text7">Yhteystiedot</a></li>
  		 	 <li><a href="view/Login.jsp" id="text7">Kirjaudu / Rekisteröidy</a></li>
 		    </ul>
		</nav>
        <div id="box" class="clearfix">
        </div>
        </div>
        <div id="box1" class="clearfix">
        <div id="tulokset">
      <form method="post" action="TilausServlet">
           
            </label>
            <label id="formgroup">

                <p id="etunimi">
                Etunimi&#x3a;
                </p>
                <input id="textinput" type="text" value="<%=tilaus.getEtunimi() %>" name="etunimi"></input>
                <%
						if (errors3.containsKey("etunimi")){
							out.println(errors3.get("etunimi"));
						}
					%> 
            </label>
            <label id="formgroup">
                <p id="sukunimi">
                Sukunimi&#x3a;
                </p>
                <input id="textinput" type="text" value="<%=tilaus.getSukunimi() %>" name="sukunimi"></input>
                                <%
						if (errors3.containsKey("sukunimi")){
							out.println(errors3.get("sukunimi"));
						}
					%>
            </label>
            <label id="formgroup">
                <p id="Puhelin_numero_">
                Puhelinnumero&#x3a;
                </p>
                <input id="textinput" type="text" value="<%=tilaus.getPuhelin() %>" name="puhelin"></input>
                                <%
						if (errors3.containsKey("puh")){
							out.println(errors3.get("puh"));
						}
					%>
            </label>
            <label id="formgroup">
                <p id="Toimitusosoite">
                Toimitusosoite&#x3a;
                </p>
                <input id="textinput" type="text" value="<%=tilaus.getToimitusosoite() %>" name="osoite"></input>
                                <%
						if (errors3.containsKey("osoite")){
							out.println(errors3.get("osoite"));
						}
					%>
            </label>
            <label id="formgroup">
                <p id="Postinumero">
                Postinumero&#x3a;
                </p>
                <input id="textinput" type="text" value="<%=tilaus.getPostinumero() %>" name ="postinro"></input>
                                <%
						if (errors3.containsKey("posti")){
							out.println(errors3.get("posti"));
						}
					%>
            </label>
            <label id="formgroup">
                <p id="Kaupunki">
                Kaupunki&#x3a;
                </p>
                <input id="textinput" type="text" value="<%=tilaus.getKaupunki() %>" name="kaupunki"></input>
                                <%
						if (errors3.containsKey("kaupunki")){
							out.println(errors3.get("kaupunki"));
						}
					%>
            </label>
      
           <div id="radio">
		   <input type="radio" value="0" name="toimitustapa" style="float:left; margin-top: 5px;"/>
		   <p style="float: left; margin-left: 1%;">
           Nouto
		   </p><br />
           <input type="radio" value="1" name="toimitustapa" style="float:left; margin-top: 5px;"/>
           <p style="float: left; margin-left: 1%;">
           Kotiinkuljetus
           </p>
		</div>

    		<div>
            <input id="input" type="submit" value="Tilaa" onclick="return confirm('Vahvista tilaus')"></input>
            <%--Submit painikkeella ohjaa formin actioniin ja siitä TilausServlettiin--%>
            </div>
        </form>
		</div>
        </div>
              			<div id="box2" class="clearfix">
            <p id="text4">
            Ostoskori
            </p>
            <div id="tulokset1">
           <table id="table1">
           <%for (int i=0; i<tilaus.getTilausriviLkm();i++){%>
           
           <tr>
           <td><p><%=tilaus.getTilausrivi(i).getMaara() %>x</p></td>
           <td><p><%=tilaus.getTilausrivi(i).getPizza().getPizza_nimi() %>
           <%if(tilaus.getTilausrivi(i).getKoko()==false) {%>
           (norm)
          	<% }else{ %>
           (iso)
        	
           <%}%>
           </p></td> 
           <td><p><%=nf.format(tilaus.getTilausrivi(i).getHinta()) %></p></td>

           </td>
           </tr>
           <% }%>
           </table>
            <%if(tilaus.getTilaus_hinta()!=0){ %>
            <hr>
			<p id="text6">
            Hinta yhteensä <%=nf.format(tilaus.getTilaus_hinta()) %>
            </p>
            	<%} %>
        </div>    	
		</div>
        <div id="box4" class="clearfix">
        </div>
        <p id="text5">
        Yrityksen<br />Tiedot<br />Y-tunnus<br />Diipadaapaa
        </p>
    </div>
<script type="text/javascript">
function clicked() {
    if (confirm('Do you wanna to submit?')) {
        yourformelement.submit();
    } else {
        return false;
    }
 }</script>
</body>
</html>
