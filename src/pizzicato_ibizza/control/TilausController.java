package pizzicato_ibizza.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pizzicato_ibizza.model.Juoma;
import pizzicato_ibizza.model.Juomarivi;
import pizzicato_ibizza.model.Pizza;
import pizzicato_ibizza.model.Tilaus;
import pizzicato_ibizza.model.Tilausrivi;
import pizzicato_ibizza.model.dao.JuomaDAO;
import pizzicato_ibizza.model.dao.PizzaDAO;
import pizzicato_ibizza.model.dao.TilausDAO;

/**
 * Servlet implementation class TilausController
 */
@WebServlet("/TilausController")
public class TilausController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String LIST_PIZZA = "ListaaKaikilleServlet";
	private static String LIST_TILAUKSETKOK = "ListaaTilauksetKokilleServlet";
	private static PizzaDAO dao = new PizzaDAO();
	/*private static JuomaDAO juomadao = new JuomaDAO();*/

	public TilausController() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Tilaus tilaus = (Tilaus) session.getAttribute("tilaus");

		String forward = "";
		String action = request.getParameter("action"); // Action nappulalle
														// "LISÄÄ OSTOSKORIIN"

		if (action.equals("poista")) { // Tässä vaiheessa käyttäjä painaa Lisää
										// ostoskoriin nappulaa

			int index = Integer.parseInt(request.getParameter("index"));
			double price1 = tilaus.getTilaus_hinta();
			double price2 = tilaus.getTilausrivi(index).getHinta()
					* tilaus.getTilausrivi(index).getMaara();
			/*double juomaprice = tilaus.getJuomarivi(index).getHinta() * tilaus.getJuomarivi(index).getMaara();*/
			double price3 = price1 - price2;
			tilaus.removeTilausrivi(index);
			tilaus.setTilaus_hinta(price3);
			session.setAttribute("tilaus", tilaus);
			forward = LIST_PIZZA;

		} else if (action.equals("status")) {
			int tilaus_id = Integer.parseInt(request.getParameter("tilaus_id"));
			TilausDAO tilausdao = new TilausDAO();
			Tilaus tilaus1 = tilausdao.getTilausById(tilaus_id);

			if (tilaus1.getStatus().equals("tilattu")) {
				tilausdao.kokkiMuutaStatus(tilaus1);
				forward = LIST_TILAUKSETKOK;
			} else if (tilaus1.getStatus().equals("valmis")) {
				tilausdao.muutaStatus(tilaus1);
				forward = LIST_TILAUKSETKOK;
			}
		} else {

		}
		session.setAttribute("tilaus", tilaus);

		RequestDispatcher view = request.getRequestDispatcher(forward); 
		// Uudelleen ohjataan käyttäjä näkymään jossa on painanut nappia eli päivitetään ns sivu
		view.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Tilaus tilaus = (Tilaus) session.getAttribute("tilaus");

		Pizza pizza = new Pizza(); // luodaan paikka haetulle pizzalle
		/*Juoma juoma = new Juoma();*/

		Tilausrivi tilausrivi = new Tilausrivi(); 
		// Tilausrivi luodaan jotta sinne voidaan syöttää pizzoja
		/*Juomarivi juomarivi = new Juomarivi();*/

		int pizza_id = Integer.parseInt(request.getParameter("pizza_id")); 
		/*int juoma_id = Integer.parseInt(request.getParameter("juoma_id"));*/
		// tällä haetaan käyttäjän valitsema pizza sen ID numerolla
		pizza = dao.getPizzaById(pizza_id); 
		/*juoma = juomadao.getJuomaById(juoma_id);*/
		// getPizzaByID:llä haetaan valittu pizza_id

		tilausrivi.setPizza(pizza);
		/*juomarivi.setJuoma(juoma);*/

		int maara = Integer.parseInt(request.getParameter("maara"));
		tilausrivi.setMaara(maara);

		String koko = request.getParameter("koko");

		if (koko == null) {
			tilausrivi.setHinta(pizza.getHinta_norm());
			tilausrivi.setKoko(false);
		} else if (koko.equals("iso")) {
			tilausrivi.setHinta(pizza.getHinta_iso());
			tilausrivi.setKoko(true);
		} else {
			tilausrivi.setHinta(pizza.getHinta_norm());
			tilausrivi.setKoko(false);
		}

		tilaus.addTilausrivi(tilausrivi);
		
		/*int juomaMaara = Integer.parseInt(request.getParameter("maara"));
		juomarivi.setMaara(juomaMaara);
		String juomaKoko = request.getParameter(koko);
		double juomaHinta = Double.parseDouble(request.getParameter("hinta"));
		juomarivi.setHinta(juomaHinta);
		
		System.out.println(juomarivi);
		
		tilaus.addJuomarivi(juomarivi);*/
		

		session.setAttribute("tilaus", tilaus);

		response.sendRedirect("ListaaKaikilleServlet");

	}

}
