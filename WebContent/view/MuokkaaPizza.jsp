<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="pizzicato_ibizza.model.Pizza"%>
<jsp:useBean id="pizza" type="pizzicato_ibizza.model.Pizza"	scope="request" />
<jsp:useBean id="errors" scope="request" type="java.util.Map" class="java.util.HashMap" />
<%@ page import="pizzicato_ibizza.model.Tayte"%>
<jsp:useBean id="taytteet" type="java.util.ArrayList<Tayte>"
	scope="request" />
<%@ page import="pizzicato_ibizza.model.Tayte"%>
<jsp:useBean id="tt" type="java.util.ArrayList<Tayte>"
	scope="request" />

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
				
<h1>Muokkaa Pizza</h1>
		<form method="post"  action="PaivitaPizzaServlet">
			<table class="lisaa-pizza">

				<tr style="visibility:hidden;">
					<td>ID:</td>
					<td><input type="text" 
					value="<%=pizza.getPizza_id()%>"
					name="pizza_id" size="50"  />
					</td>
				</tr>
				<tr>
					<td>Nimi:</td>
					<td><input type="text" 
					value="<%=pizza.getPizza_nimi()%>"
					name="pizza_nimi" size="50"  />
					<%
						if (errors.containsKey("pizza_nimi")){
							out.println(errors.get("pizza_nimi"));
						}
					%>
					</td>
				</tr>
				
				<tr>
					<td>Kuvaus:</td>
						<td><input type="text" 
						value="<%=pizza.getKuvaus()%>"
						name="kuvaus" size="50"  />
					
					</td>
				</tr>


				<tr>
					<td>Hinta normikokoisena:</td>
					<td><input type="text" 
					value="<%=pizza.getHinta_norm()%>"
					name="hinta_norm" size="50"  />
					<%
						if (errors.containsKey("strhinta_norm")){
							out.println(errors.get("strhinta_norm"));
						}
					%>
					</td>
				</tr>
				<tr>
					<td>Hinta isokokoisena:</td>
					<td><input type="text" 
					value="<%=pizza.getHinta_iso()%>"
					name="hinta_iso" size="50"  />
					<%
						if (errors.containsKey("strhinta_iso")){
							out.println(errors.get("strhinta_iso"));
						}
					%>
					</td>
				</tr>
			<table class="lisaa-tayte">
				<tr>
					<td>Sisältö:</td>
										<td><%
						if (errors.containsKey("taytteet")){
							out.println(errors.get("taytteet"));
						}
					%> </td>
					<%for(int i = 0; i < taytteet.size(); i++) {%>
					<%if(taytteet.get(i).getStatus()==true) {%>
					<tr style="width:10px;">
						<td><input type="checkbox" name="tayte" value="<%=taytteet.get(i).getTayte_id()%>"
					<%for(int in = 0; in < tt.size(); in++) {%>
					<%if(taytteet.get(i).getTayte_id()==tt.get(in).getTayte_id()){ %>
						checked
						/>
					<%}else{
						
						}%>	
						
						<% } %>
						
						
						</td>
						<td><%=taytteet.get(i).getTayte_nimi()%></td>
						<% } %>
						<% } %>
					</tr>	
				
			</table>
					<tr>

					<td>
						<input type="submit" name="submit-button" class="submit-button" value="Tallenna" />
					</td>
				</tr>	
			</table>
			</form>


		</div>
        </div>
    </div>
    </body>
</html>