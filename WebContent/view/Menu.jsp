<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="pizzicato_ibizza.model.Pizza"%>
<jsp:useBean id="pizzat" type="java.util.ArrayList<Pizza>"
	scope="request" />

<%@ page import="pizzicato_ibizza.model.Juoma"%>
<jsp:useBean id="juomalista" type="java.util.ArrayList<Juoma>"
	scope="request" />

<%@ page import="pizzicato_ibizza.model.Tilausrivi"%>
<jsp:useBean id="tilaus" type="pizzicato_ibizza.model.Tilaus"
	scope="session" />


<%@ page import="java.text.NumberFormat"%>
<%
	NumberFormat nf = NumberFormat.getInstance();
    nf.setMaximumFractionDigits(2);
    nf.setMinimumFractionDigits(2);
%>
<html>

<!-- This code is only meant for previewing your Reflow design. -->

<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<link rel="stylesheet" href="css/boilerplate.css">
<link rel="stylesheet" href="css/menu.css">
<link rel="stylesheet" href="../css/menu.css">
<link href='https://fonts.googleapis.com/css?family=Poiret+One'
	rel='stylesheet' type='text/css'>
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
         <table>
         <thead>
		<tr>
			<th></th>
			<th>Nimi</th>
			<th>Norm / Iso</th>
			<th>Koko</th>
			<th>Kpl</th>

		</tr>
		</thead>
			    <%for(int i = 0; i < pizzat.size(); i++) {%>
                        <%if(pizzat.get(i).getStatus()==true) {%>
                        <tr>
                                <td><%=i+1%></td>
                                <td style="padding-bottom: 20px"><%=pizzat.get(i).getPizza_nimi()%><br />
								<%=pizzat.get(i).getTaytteet()%></td>
                        		<td><%=nf.format(pizzat.get(i).getHinta_norm())%> / <%=nf.format(pizzat.get(i).getHinta_iso())%></td>
                                <form method="post" action="TilausController">
                              	<td>
                              	<div class="styled-select">
                              	<select name="koko">
                              	<option value="norm">Normaali</option>
                              	<option value="iso">Iso</option>
                              	</select>
                              	</div>
                              	</td>
                                <td>
                                <div class="styled-select1">
                                <select name="maara">
                                <option value ="1">1</option>
                                <option value ="2">2</option>
                                <option value ="3">3</option>
                                <option value ="4">4</option>
                                <option value ="5">5</option>
                                </select>
                                </div>
                                </td>

                                <input type="hidden" name="pizza_id" value="<%=pizzat.get(i).getPizza_id()%>"/>
                                <td><input type="image" src="kuvat/Drawing.png" alt="Submit" width="48" height="48" id="image1"></td>


                                </form>
                                		
                        </tr>

                        <% } %>
                        <% } %>

           </table> 

           </div>

            </div>
			<div id="box2" class="clearfix">
            <p id="text4">
            Ostoskori
            </p>
            <div id="tulokset1">
           <table>
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
           <td><a href="TilausController?action=poista&index=<%=i%>"><button type="button" id="input1">Poista</button></a>
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
             <%if (tilaus.getTilausriviLkm()<1) {%>
              <a href="ListaaKaikilleServlet"><button id="input" type="button" onClick="return confirm('Ostoskorisi on tyhjä. Laita sinne pizza niin pääset tilaamaan.')">Siirry tilaamaan</button>
            
             <%} else{%>
             
            
             <a href="TilausServlet"><button id="input" type="button">Siirry tilaamaan</button></a>
             <% }%>
        </div>
        </div>
        <div id="box4" class="clearfix">
        </div>
        <p id="text5">
        Yrityksen<br />Tiedot<br />Y-tunnus<br />Diipadaapaa
        </p>
    </div>
<script>
$('#button1').click(function(){$('#button2').click()});
</script>
    </body>
</html>
