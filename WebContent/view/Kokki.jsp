<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="pizzicato_ibizza.model.Tilaus"%>
<jsp:useBean id="tilaukset" type="java.util.ArrayList<Tilaus>"
        scope="request" />
<%@ page import="pizzicato_ibizza.model.Tilausrivi"%>

        
<%@ page import="java.text.NumberFormat" %>
<%
    NumberFormat nf = NumberFormat.getInstance();
    nf.setMaximumFractionDigits(2);
    nf.setMinimumFractionDigits(2);
%>
 <%java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); %>

<html>

    <head>
        <link rel="stylesheet" href="../css/boilerplate.css">
        <link rel="stylesheet" href="../css/menu-admin.css">
        <meta charset="utf-8">
        <meta name="viewport" content="initial-scale = 1.0,maximum-scale = 1.0">
    </head>
    <body>
 
    <div id="primaryContainer" class="primaryContainer clearfix">
    	<h1 id="admin">Kokki</h1>
        <div id="box" class="clearfix">
        </div>
        <div id="box1" class="clearfix">
        <div id="tulokset">
        <h1>Tilaukset</h1>
        
        <table border="1">
        
         <tr>
	        <td>Id</td>
	        <td>pvm</td>
	        <td>toimitustapa</td>
	        <td>status</td>
	        <td></td>
        </tr>
        <%for(int i = 0; i < tilaukset.size(); i++) {%>
        <%if(tilaukset.get(i).getStatus().equals("tilattu")||tilaukset.get(i).getStatus().equals("valmis") ) {%>
        <tr>
        
        <td><%=tilaukset.get(i).getTilaus_id()%></td>
        <td><%=df.format(tilaukset.get(i).getTilaus_pvm())%></td>

        <%if (tilaukset.get(i).isToimitustapa()==true){ %>
        <td>Kotiinkuljetus</td>
        <% }else{ %>
       
        <td>Nouto</td>
        <% } %>
        <td><%=tilaukset.get(i).getStatus()%></td>
        <%if(tilaukset.get(i).getStatus().equals("tilattu")) {%>
                				<td><a href="TilausController?action=status&tilaus_id=<%=tilaukset.get(i).getTilaus_id()%>"><input type="button" value="Laita valmiiksi" id="myButton"></input></a></td>
 
                                <%}else if(tilaukset.get(i).getStatus().equals("valmis")){ %>
                                <td><a href="TilausController?action=status&tilaus_id=<%=tilaukset.get(i).getTilaus_id()%>"><input type="button" value="Peruuta" id="myButton"></input></a></td>
 
                                       
                                <%} %>
       
        <%for(int j = 0; j < tilaukset.get(i).getTilausriviLkm(); j++) {%>
        </tr>
        <td></td>
        <td><%=tilaukset.get(i).getTilausrivi(j).getPizza().getPizza_nimi()%></td>
        <td>x<%=tilaukset.get(i).getTilausrivi(j).getMaara()%></td>
        <% } %>
        
        </tr>
        <% } %>
        <% } %>
        

        
        </table>
		
		</div>
        </div>
   
    </div>
    </body>
</html>