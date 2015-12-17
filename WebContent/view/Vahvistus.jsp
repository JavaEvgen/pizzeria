<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="pizzicato_ibizza.model.Tilausrivi"%>
        
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
	<link rel="stylesheet" href="css/menu.css">
	<link rel="stylesheet" href="../css/menu.css">
	<link href='https://fonts.googleapis.com/css?family=Poiret+One' rel='stylesheet' type='text/css'>
	<meta charset="utf-8">
	<meta name="viewport" content="initial-scale = 1.0,maximum-scale = 1.0">
<title>Insert title here</title>
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
        <div id="box" class="clearfix">
        </div>
        </div>
        <div id="box1" class="clearfix">
        <a id="text1" >Tilauksesi on vastaanotettu onnistuneesti<br><br><br><br><br><br>Tilauksesi sisältää seuraavat tuotteet:</a>

      
           
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
        <div id="tulokset">
     
		</div>
        </div>
              
            
         
          		</div>
          
           
 

        <div id="box4" class="clearfix">
        </div>
<script type="text/JavaScript">

setTimeout("location.href = 'ListaaKaikilleServlet';",10000);

</script>
</body>
</html>