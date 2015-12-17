<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="pizzicato_ibizza.model.Tayte"%>
<jsp:useBean id="taytteet" type="java.util.ArrayList<Tayte>"
	scope="request" />
	<%@ page import="java.text.NumberFormat" %>
<%
    NumberFormat nf = NumberFormat.getInstance();
    nf.setMaximumFractionDigits(2);
    nf.setMinimumFractionDigits(2);
%>

<html>

    <!-- This code is only meant for previewing your Reflow design. -->

    <head>
	<link rel="stylesheet" href="css/boilerplate.css">
	<link rel="stylesheet" href="css/menu-admin.css">
	<meta charset="utf-8">
	<meta name="viewport" content="initial-scale = 1.0,maximum-scale = 1.0">
    </head>
    <body>

    <div id="primaryContainer" class="primaryContainer clearfix">
    <h1 id="admin">Admin</h1>
        <div id="box" class="clearfix">
        </div>
        <div id="box1" class="clearfix">
        <div id="pizzamenu">
        
        <a style="float:left;" href="ListaaPizzatAdminilleServlet"><input type="button" value="Pizzat"></input></a>
        <a href="ListaaTaytteetAdminilleServlet"><input type="button" value="Täytteet"></input></a>
        <a style="float:left;" href="ListaaTilauksetServlet"><input type="button" value="Tilaukset"></input></a>
                
				<h1>Täytteet</h1>
				
				<form method="get" action="LisaaTayteServlet">
    			<button type="submit">Lisää Täyte</button>
    			</form>
	<table border="1">
		<tr>
			<td>Nimi</td>
			<td>Kilohinta</td>
			<td></td>
			<td></td>
			<td>Onko saatavilla?</td>
			<td>Status</td>

		</tr>
			<%for(int i = 0; i < taytteet.size(); i++) {%>
			

			<tr>
				<td><%=taytteet.get(i).getTayte_nimi()%></td>
				<td><%=nf.format(taytteet.get(i).getHinta())%></td>
				<td><a href="TayteController?action=edit&tayte_id=<%=taytteet.get(i).getTayte_id()%>"><input type="button" value="Päivitä"></input></a></td>
                <td><a href="TayteController?action=delete&tayte_id=<%=taytteet.get(i).getTayte_id()%>"><input type="button" value="Poista"onclick="return confirm('Haluatko varmasti poista?')"></input></a></td>

				<%if(taytteet.get(i).getStatus()==true) {%>
                <td><a href="TayteController?action=status&tayte_id=<%=taytteet.get(i).getTayte_id()%>&status=<%=taytteet.get(i).getStatus()%>"><input type="button" value="Saatavilla" id="myButton" style="color:#009900"></input></a></td>

				<%}else{ %>
				<td><a href="TayteController?action=status&tayte_id=<%=taytteet.get(i).getTayte_id()%>&status=<%=taytteet.get(i).getStatus()%>"><input type="button" value="Ei saatavilla" id="myButton" style="color:#C80000"></input></a></td>

					
				<%} %>
  				<td><%=taytteet.get(i).getStatus()%></td>
               
                
			</tr>
			<% } %>
		</table>
		</div>
        </div>
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