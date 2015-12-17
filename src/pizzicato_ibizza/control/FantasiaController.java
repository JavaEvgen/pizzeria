package pizzicato_ibizza.control;

import java.io.IOException;

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
 * Servlet implementation class FantasiaController
 */
@WebServlet("/FantasiaController")
public class FantasiaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String ListaaPizzat = "FantasiaServlet";
    private PizzaDAO dao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FantasiaController() {
        super();
        dao = new PizzaDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward="";
		String action = request.getParameter("action");
		
		if(action.equals("fantasia")){
			
			forward = ListaaPizzat;
			
		 int pizza_id=(Integer.parseInt(request.getParameter("pizza_id")));
		 Pizza pizza = dao.getPizzaById(pizza_id);
		 
		 
		 request.setAttribute("pizza", pizza);
		 
		 System.out.println(pizza);
		 
	}else{
		 
	}
	 			
	 			RequestDispatcher view = request.getRequestDispatcher(forward);
	 	        view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
