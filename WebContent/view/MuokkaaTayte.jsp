<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="pizzicato_ibizza.model.Tayte"%>
<jsp:useBean id="tayte" type="pizzicato_ibizza.model.Tayte"     scope="request" />
<jsp:useBean id="errors1" scope="request" type="java.util.Map" class="java.util.HashMap" />
 
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
                               
<h1>Muokkaa täyte</h1>
                <form method="post"  action="PaivitaTayteServlet">
                        <table class="lisaa-pizza">
 
                                <tr style="visibility:hidden;">
                                        <td>ID:</td>
                                        <td><input type="text"
                                        value="<%=tayte.getTayte_id()%>"
                                        name="tayte_id" size="50"  />
                                        </td>
                                </tr>
                                <tr>
                                        <td>Nimi:</td>
                                        <td><input type="text"
                                        value="<%=tayte.getTayte_nimi()%>"
                                        name="tayte_nimi" size="50"  />
                                        <%
                                                if (errors1.containsKey("tayte_nimi")){
                                                        out.println(errors1.get("tayte_nimi"));
                                                }
                                        %>
                                        </td>

                                <tr>
                                        <td>Hinta/kg:</td>
                                        <td><input type="text"
                                        value="<%=tayte.getHinta()%>"
                                        name="hinta" size="50"  />
									<%
										if (errors1.containsKey("strhinta")){
											out.println(errors1.get("strhinta"));
										}
						
									%> 
                                        </td>
                                </tr>

 
                                <tr>
                                        <td><a href="ListaaTaytteetAdminilleServlet"><input type="button" value="Peruuta"></input></a></td>
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