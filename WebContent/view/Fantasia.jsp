<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="pizzicato_ibizza.model.Pizza"%>
<jsp:useBean id="pizza" type="pizzicato_ibizza.model.Pizza"	scope="request" />
		<%@ page import="pizzicato_ibizza.model.Tayte"%>
<jsp:useBean id="taytteet" type="java.util.ArrayList<Tayte>"
	scope="request" />
	        <jsp:useBean id="tilaus" type="pizzicato_ibizza.model.Tilaus"
		scope="session" />
		
<%@ page import="java.text.NumberFormat" %>
<%
    NumberFormat nf = NumberFormat.getInstance();
    nf.setMaximumFractionDigits(2);
    nf.setMinimumFractionDigits(2);
%>
<html>

    <!-- This code is only meant for previewing your Reflow design. -->

    <head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<link rel="stylesheet" href="css/boilerplate.css">
	<link rel="stylesheet" href="css/menu.css">
	<link rel="stylesheet" href="../css/menu.css">
	<link href='https://fonts.googleapis.com/css?family=Poiret+One' rel='stylesheet' type='text/css'>
	<meta charset="utf-8">
	<meta name="viewport" content="initial-scale = 1.0,maximum-scale = 1.0">
    </head>
    <body>

	

    <div id="primaryContainer" class="primaryContainer clearfix">
    <div id="Navi" class="clearfix">
        <img id="image" src="kuvat/pizza2.jpg" class="image" />
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
        <div id="box" class="clearfix">
        </div>
        </div>
        <div id="box1" class="clearfix">
        <div id="tulokset">
        
        <h1>Valitse täytteet Fantasiapizzaasi</h1>
		<form method="post" action="FantasiaServlet">
			
        
        <table class="lisaa-tayte">
				<tr style="visibility:hidden;">
					<td>ID:</td>
					<td><input type="text" 
					value="<%=pizza.getPizza_id()%>"
					name="pizza_id" size="50"  />
					</td>
				</tr>
				<tr>
					<td>Sisältö:</td>
					
					<%for(int i = 0; i < taytteet.size(); i++) {%>
					<%if(taytteet.get(i).getStatus()==true) {%>
					<tr style="width:10px;">
						<td><input type="checkbox" name="tayte" value="<%=taytteet.get(i).getTayte_id()%>"/><td>
						<td><%=taytteet.get(i).getTayte_nimi()%></td>
					<tr>	
					
					<% } %>
					<% } %>
<tr>
					
					<td>
						<input type="submit" name="submit-button" class="submit-button" value="Lisää Ostoskoriin" />
						
					</td>
				</tr>	
					
				</tr>
			</table>
			</form>
        </div>
        </div>
        </div>
        
    </body>
</html>