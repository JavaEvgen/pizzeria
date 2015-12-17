package pizzicato_ibizza.control;

import java.io.IOException;




import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pizzicato_ibizza.model.Asiakas;
import pizzicato_ibizza.model.Pizza;
import pizzicato_ibizza.model.dao.AsiakasDAO;
import pizzicato_ibizza.model.dao.PizzaDAO;

/**
 * Servlet implementation class Rekisterointi
 */
@WebServlet("/RekisterointiServlet")
public class RekisterointiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RekisterointiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AsiakasDAO Asiakasdao = new AsiakasDAO();
		ArrayList<Asiakas> Asiakaat = Asiakasdao.findAll();

		// Talletetaan request-olion alle Asiakaslista, jotta tiedot ovat käytössä jsp:llä
		request.setAttribute("Asiakaat", Asiakaat);
        
        String jsp = "/view/Rekisteroidy.jsp";
        RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
        dispather.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String jsp = "/view/Rekisteroidy.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
        
        
        
        Map<String, String> errors4 = validate(request);
        if (!errors4.isEmpty())
        {
                dispatcher.forward(request, response);
        } else {
       
        Asiakas asiakas = (Asiakas) request.getAttribute("asiakas");
       
        AsiakasDAO dao = new AsiakasDAO();
        try {
                dao.addAsiakas(asiakas);
        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
		
        response.sendRedirect("ListaaKaikilleServlet");
        }
    }   
        
        
        public static Map<String, String> validate(HttpServletRequest request){
        	
        Asiakas asiakas = new Asiakas();
        HashMap<String, String> errors4 = new HashMap<String, String>();
        request.setAttribute("errors4", errors4);
        request.setAttribute("asiakas", asiakas);	
        
		String sukunimi = request.getParameter("sukunimi");
		if (sukunimi == null || sukunimi.trim().length() == 0)
        {
                errors4.put("sukunimi", "Anna sukunimi.");
        } else {
        		asiakas.setSukunimi(sukunimi);
        }
		
		String etunimi = request.getParameter("etunimi");
		if (etunimi == null || etunimi.trim().length() == 0)
        {
                errors4.put("etunimi", "Anna etunimi.");
        } else {
        		asiakas.setEtunimi(etunimi);
        }
		String puhelin = request.getParameter("puhelin_nro");
		if (puhelin == null || puhelin.trim().length() == 0)
        {
                errors4.put("puhelin", "Anna puhelin.");
        } else {
        		asiakas.setPuhelin_nro(puhelin);
        }
		
		String email = request.getParameter("email");
		if (email == null || email.trim().length() == 0)
        {
                errors4.put("email", "Anna email.");
        } else {
        		asiakas.setEmail(email);
        }
		
		
		String katuosoite = request.getParameter("katuosoite");
		if (katuosoite == null || katuosoite.trim().length() == 0)
        {
                errors4.put("osoite", "Anna osoite.");
        } else {
        		asiakas.setKatuosoite(katuosoite);
        }
		
		String postinumero = request.getParameter("postinumero");
		if (postinumero == null || postinumero.trim().length() == 0)
        {
                errors4.put("posti", "Anna postinumero.");
        } else {
        		asiakas.setPostinumero(postinumero);
        }
		
		
		
		String kayttajatunnus = request.getParameter("kayttajatunnus");
		
		if (kayttajatunnus == null || kayttajatunnus.trim().length() == 0)
        {
                errors4.put("tunnus", "Anna käyttäjätunnus.");
        } else {
        	asiakas.setKayttajatunnus(kayttajatunnus);
        }
		
		
		String salasana = request.getParameter("salasana");
		
		if (salasana == null || salasana.trim().length() == 0)
        {
                errors4.put("salasana", "Anna salasana.");
        } else {
        	asiakas.setSalasana(salasana);
        }
		
		
		
		return errors4;
	}

}
