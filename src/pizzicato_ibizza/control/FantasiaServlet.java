package pizzicato_ibizza.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pizzicato_ibizza.model.Pizza;
import pizzicato_ibizza.model.Tayte;
import pizzicato_ibizza.model.Tilaus;
import pizzicato_ibizza.model.Tilausrivi;
import pizzicato_ibizza.model.dao.PizzaDAO;
import pizzicato_ibizza.model.dao.TayteDAO;

/**
 * Servlet implementation class FantasiaServlet
 */
@WebServlet("/FantasiaServlet")
public class FantasiaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FantasiaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		TayteDAO taytedao = new TayteDAO();
        ArrayList<Tayte> taytteet = taytedao.findAll();
        
        request.setAttribute("taytteet", taytteet);
        
 
        

		
		
	    String jsp = "/view/Fantasia.jsp";
	    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
	    dispatcher.forward(request, response);
		

        
       

        // Talletetaan request-olion alle Pizzalista, jotta tiedot ovat käytössä jsp:llä

	
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Tilaus tilaus = (Tilaus) session.getAttribute("tilaus");
		if(tilaus==null){ //jos tilausta ei ole luotu niin se luodaan tässä vaiheessa
    		Tilaus tilaus1 = new Tilaus();
    		session.setAttribute("tilaus", tilaus1); //Tallennetaan tilaus sessioon
    		
    		
    	}
		
		PizzaDAO dao = new PizzaDAO();
		int pizza_id=(Integer.parseInt(request.getParameter("pizza_id")));
		
		Pizza pizza = dao.getPizzaById(pizza_id);

		Tilausrivi tilausrivi = new Tilausrivi();
		System.out.println(pizza);
		
		tilausrivi.setPizza(pizza);
		String[] sisallot = request.getParameterValues("tayte");
		 
        
        
        final int[] ints = new int[sisallot.length];
        for (int i=0; i < sisallot.length; i++) {
            ints[i] = Integer.parseInt(sisallot[i]);
        }
       
            for(int t: ints){
                    Tayte tayte = new Tayte();
                    tayte.setTayte_id(t);
                   
                    tilausrivi.tayt.add(tayte);
                   
                                   
            }
            
            System.out.println(tilausrivi);
 			tilaus.addTilausrivi(tilausrivi);

 			session.setAttribute("tilaus", tilaus);
 			
 			response.sendRedirect("ListaaKaikilleServlet");
            
            


	}
	
	
	
}