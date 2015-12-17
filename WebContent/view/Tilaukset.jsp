<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="pizzicato_ibizza.model.Tilaus"%>
<jsp:useBean id="tilaukset" type="java.util.ArrayList<Tilaus>"
        scope="request" />
        
<%@ page import="java.text.NumberFormat" %>
<%
    NumberFormat nf = NumberFormat.getInstance();
    nf.setMaximumFractionDigits(2);
    nf.setMinimumFractionDigits(2);
%>

<html>

    <head>
        <link rel="stylesheet" href="../css/boilerplate.css">
        <link rel="stylesheet" href="../css/menu-admin.css">
        <meta charset="utf-8">
        <meta name="viewport" content="initial-scale = 1.0,maximum-scale = 1.0">
    </head>
    <body>
 
    <div id="primaryContainer" class="primaryContainer clearfix">
    	<h1 id="admin">Admin</h1>
        <div id="box" class="clearfix">
        </div>
        <a style="float:left;" href="ListaaPizzatAdminilleServlet"><input type="button" value="Pizzat"></input></a>
        <a href="ListaaTaytteetAdminilleServlet"><input type="button" value="Täytteet"></input></a>
        <a href="ListaaTilauksetServlet"><input type="button" value="Tilaukset"></input></a>
        <div id="box1" class="clearfix">
        <div id="tulokset">
        <h1>Tilaukset</h1>
        
        <table border="1">
        <tr>
        <td>Id</td>
        <td>pvm</td>
        <td>Hinta</td>
        <td>Pizza(t)</td>
        <td>Tilaajan yhteystiedot<td>
        <td>Toimitustapa</td>
        <td>status</td>
        </tr>
        <%for(int i = 0; i < tilaukset.size(); i++) {%>
        <tr>
        <td><%=tilaukset.get(i).getTilaus_id()%></td>
        <td><%=tilaukset.get(i).getTilaus_pvm()%></td>
        <td><%=nf.format(tilaukset.get(i).getTilaus_hinta1())%></td>
        <td><%=tilaukset.get(i).getPizzatMaar()%></td>
        <td>	<%=tilaukset.get(i).getAsiakas()%> 
        		<%=tilaukset.get(i).getEtunimi()%> 
        		<%=tilaukset.get(i).getSukunimi()%> 
        		<%=tilaukset.get(i).getToimitusosoite()%> 
        		<%=tilaukset.get(i).getPuhelin()%>
        </td>
        <td></td>
        <%if (tilaukset.get(i).isToimitustapa()==true){ %>

        <td>Kotiinkuljetus</td>
        <% }else{ %>
       
        <td>Nouto</td>
        <% } %>
        <td><%=tilaukset.get(i).getStatus()%></td>
        </tr>
        <% } %>
        
        </table>
		
		</div>
        </div>
   
    </div>
    </body>
</html>