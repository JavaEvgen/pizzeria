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

import pizzicato_ibizza.model.Pizza;
import pizzicato_ibizza.model.Tilaus;
import pizzicato_ibizza.model.dao.PizzaDAO;
import pizzicato_ibizza.model.dao.TilausDAO;
 
/**
 * Servlet implementation class ListaaPizzatServlet
 */
@WebServlet("/TilaustenSeruranta")
public class TilaustenSerurantaServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;
       
 
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

                //Luodaan uuse pizzadao ja haetaan tuote titetokannasta kaikki tietod ja sijoitetaan niit� Array listiin
                TilausDAO tilausdao = new TilausDAO();
                ArrayList<Tilaus> tilaukset = tilausdao.findAll();
               
 
                // Talletetaan request-olion alle Tuotelista, jotta tiedot ovat k�yt�ss� jsp:ll�
                request.setAttribute("tilaukset", tilaukset);
                
                ArrayList<Tilaus> tilaukset2 = tilausdao.findAll();
                
                
                // Talletetaan request-olion alle Tuotelista, jotta tiedot ovat k�yt�ss� jsp:ll�
                request.setAttribute("tilaukset2", tilaukset2);
                
                ArrayList<Tilaus> tilaukset3 = tilausdao.findAllForKuski();
                
                
                // Talletetaan request-olion alle Tuotelista, jotta tiedot ovat k�yt�ss� jsp:ll�
                request.setAttribute("tilaukset3", tilaukset3);
               
                // l�het� selaimelta tullut pyynt� servletilt� edelleen  jsp:lle
                String jsp = "/view/TilaustenSeuranta.jsp";
                RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
                dispather.forward(request, response);
        }
 
 
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                
        	
        	
        	
        	
        }
 
}