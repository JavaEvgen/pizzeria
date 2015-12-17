package pizzicato_ibizza.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pizzicato_ibizza.model.Pizza;
import pizzicato_ibizza.model.Tilaus;
import pizzicato_ibizza.model.dao.PizzaDAO;
import pizzicato_ibizza.model.dao.TilausDAO;

/**
 * Servlet implementation class TilausServlet
 */
@WebServlet("/TilausServlet")
public class TilausServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TilausServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String jsp = "/view/Tilaus.jsp";
         RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
         dispather.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
        String jsp = "/view/Tilaus.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
		
        Map<String, String> errors3 = validate(request);
        if (!errors3.isEmpty())
        {
                dispatcher.forward(request, response);
        } else {
       
        	Tilaus tilaus = (Tilaus) session.getAttribute("tilaus");
       
        TilausDAO tdao = new TilausDAO();
        try {
                tdao.addTilaus(tilaus);
        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }

        response.sendRedirect("VahvistusServlet"); 
        }
        
	}
		
        public static Map<String, String> validate(HttpServletRequest request){
        	HttpSession session = request.getSession();
        	Tilaus tilaus = (Tilaus) session.getAttribute("tilaus");
            HashMap<String, String> errors3 = new HashMap<String, String>();
            request.setAttribute("errors3", errors3);
            request.setAttribute("tilaus", tilaus);
        	
		
		String etunimi=request.getParameter("etunimi");
		if (etunimi == null || etunimi.trim().length() == 0)
        {
                errors3.put("etunimi", "Anna etunimi.");
        } else {
        		tilaus.setEtunimi(etunimi);
        }
		

		String sukunimi=request.getParameter("sukunimi");
		if (sukunimi == null || sukunimi.trim().length() == 0)
        {
                errors3.put("sukunimi", "Anna sukunimi.");
        } else {
        		tilaus.setSukunimi(sukunimi);
        }

		
		String regex1 = "^\\+(?:[0-9] ?){12}$"; //+358 nrolle
		String regex2 = "^\\d{10}$";			  //ilman suuntanro
		
		
		
		
		String puh=request.getParameter("puhelin");
		if (!puh.matches(regex1) && (!puh.matches(regex2)))
        {
                errors3.put("puh", "Tarkista puhelinnumero.");
                
             
        }else {
        		tilaus.setPuhelin(puh);
        }
		
		String tilausosoite=request.getParameter("osoite");
		if (tilausosoite == null || tilausosoite.trim().length() == 0)
        {
                errors3.put("osoite", "Anna osoite.");
        } else {
        		tilaus.setToimitusosoite(tilausosoite);
        }

		 String posti = request.getParameter("postinro");
		//String postinumero=request.getParameter("postinro");
		if (posti == null || posti.trim().length() < 5 || posti.trim().length() >5)
        {
                errors3.put("posti", "Anna postinumero, jonka pituus on 5 merkkiä.");
        	
        } else if (posti.matches("\\D+")){
            errors3.put("posti", "Anna postinumero numeroina.");
            
        }else {
        		tilaus.setPostinumero(posti);
        }
		
		
		String kaupunki=request.getParameter("kaupunki");
		if (kaupunki == null || kaupunki.trim().length() == 0)
        {
                errors3.put("kaupunki", "Anna kaupunki.");
        } else {
        		tilaus.setKaupunki(kaupunki);
        }
		
		Boolean toimitustapa=Boolean.parseBoolean(request.getParameter("toimitustapa"));
		tilaus.setToimitustapa(toimitustapa);
		
		
		return errors3;
        }

		
		//ajaa kaikki submitatut tiedot ja sen jälkeen uudelleen ohjaa ListaakaikilleServlettiin

		
	}


