package pizzicato_ibizza.control;

import java.io.IOException;
import java.util.ArrayList;
 




import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import javax.servlet.http.HttpSession;

import pizzicato_ibizza.model.Juoma;
import pizzicato_ibizza.model.Pizza;
import pizzicato_ibizza.model.Tilaus;
import pizzicato_ibizza.model.dao.JuomaDAO;
import pizzicato_ibizza.model.dao.PizzaDAO;
 
/**
 * Servlet implementation class ListaaPizzatServlet
 */
@WebServlet("/ListaaKaikilleServlet")
public class ListaaPizzatKaikilleServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;
       
 
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        	
        	
        	HttpSession session = request.getSession();
        	// sessio aloitetaan
        	Tilaus tilaus = (Tilaus) session.getAttribute("tilaus"); //haetaan tilaus sessiolta
        	if(tilaus==null){ //jos tilausta ei ole luotu niin se luodaan tässä vaiheessa
        		Tilaus tilaus1 = new Tilaus();
        		session.setAttribute("tilaus", tilaus1); //Tallennetaan tilaus sessioon
        		
        		
        	}
        	
        	
        	
                //Luodaan uusi pizzadao ja haetaan tuote titetokannasta kaikki tietod ja sijoitetaan niitä Array listiin
                PizzaDAO pizzadao = new PizzaDAO();
                ArrayList<Pizza> pizzat = pizzadao.findAllforAll();
                JuomaDAO juomadao = new JuomaDAO();
                ArrayList<Juoma> juomalista = juomadao.findAll();
               
 
                // Talletetaan request-olion alle Tuotelista, jotta tiedot ovat käytössä jsp:llä
                request.setAttribute("pizzat", pizzat);
                request.setAttribute("juomalista", juomalista);
               
                // lähetä selaimelta tullut pyyntö servletiltä edelleen  jsp:lle
                String jsp = "/view/Menu.jsp";
                RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
                dispather.forward(request, response);
        }
 
 
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                
        	
        	
        	
        	
        }
 
}
