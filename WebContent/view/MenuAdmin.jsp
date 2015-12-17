<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="pizzicato_ibizza.model.Pizza"%>
<%@ page import="pizzicato_ibizza.model.Tayte"%>
<jsp:useBean id="pizzat" type="java.util.ArrayList<Pizza>" scope="request" />
<jsp:useBean id="taytteet" type="java.util.ArrayList<Tayte>" scope="request" />
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
        <a href="ListaaTaytteetAdminilleServlet"><input type="button" value="T‰ytteet"></input></a>
        <a href="ListaaTilauksetServlet"><input type="button" value="Tilaukset"></input></a>
       
                                <h1>Pizzat</h1>
                               
                                <form method="get" action="LisaaPizzaServlet">
                        <button type="submit">Lis‰‰ Pizza</button>
                        </form>
        <table border="1">
                <tr>
                        <td>Id</td>
                        <td>Nimi</td>
                        <td>Kuvaus</td>
                        <td>T‰ytteet</td>
                        <td>Normi/Iso</td>
                        <td></td>
                        <td></td>
                        <td>Onko listalla?</td>
                        <td>Status</td>
 
                </tr>
                        <%for(int i = 0; i < pizzat.size(); i++) {%>
 
                       
                        <tr>
                                <td><%=i+1%></td>
                                <td><%=pizzat.get(i).getPizza_nimi()%></td>
                               	<td><%=pizzat.get(i).getKuvaus()%></td>
                               
                                <td><%=pizzat.get(i).getTaytteet()%></td>

                                
                                <td><%=nf.format(pizzat.get(i).getHinta_norm())%> / <%=nf.format(pizzat.get(i).getHinta_iso())%></td>
                       
                                <td><a href="PizzaController?action=edit&pizza_id=<%=pizzat.get(i).getPizza_id()%>"><input type="button" value="P‰ivit‰"></input></a></td>
               					<td><a href="PizzaController?action=delete&pizza_id=<%=pizzat.get(i).getPizza_id()%>"><input type="button" value="Poista" onclick="return confirm('Haluatko varmasti poista?')"></input></a></td>
 
                                <%if(pizzat.get(i).getStatus()==true) {%>
                				<td><a href="PizzaController?action=status&pizza_id=<%=pizzat.get(i).getPizza_id()%>&status=<%=pizzat.get(i).getStatus()%>"><input type="button" value="Piilota" id="myButton" style="color:#009900"></input></a></td>
 
                                <%}else{ %>
                                <td><a href="PizzaController?action=status&pizza_id=<%=pizzat.get(i).getPizza_id()%>&status=<%=pizzat.get(i).getStatus()%>"><input type="button" value="N‰yt‰" id="myButton" style="color:#C80000"></input></a></td>
 
                                       
                                <%} %>
                                <td><%=pizzat.get(i).getStatus()%></td>
             
               
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