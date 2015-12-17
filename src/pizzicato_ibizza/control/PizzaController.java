package pizzicato_ibizza.control;

import java.io.IOException;
import java.util.ArrayList;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import pizzicato_ibizza.model.dao.PizzaDAO;
import pizzicato_ibizza.model.Tayte;
import pizzicato_ibizza.model.Pizza;
 
@WebServlet("/PizzaController")
 
public class PizzaController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String EDIT = "PaivitaPizzaServlet";
    private static String LIST_USER = "ListaaPizzatAdminilleServlet";
    private PizzaDAO dao;
   
   
 
    public PizzaController() {
        super();
        dao = new PizzaDAO();
    }
 
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                //Luodan uusi muuttuja käyttäjän toimenpidettä varten.
        String forward="";
        String action = request.getParameter("action");
        //kuunnellaan "action" vastausta jsp:sta ja suoritan alla mainitut fuktiot "actionia" riippujen
        //tässä hoidetaan sekä poisto että päivitys
        if (action.equalsIgnoreCase("delete")){
            int pizza_id = Integer.parseInt(request.getParameter("pizza_id"));
            dao.deletePizzanTayte(pizza_id);
            dao.deletePizza(pizza_id);
            forward = LIST_USER;
        } else if (action.equalsIgnoreCase("edit")){
                forward = EDIT;
                        int pizza_id = Integer.parseInt(request.getParameter("pizza_id"));
                        ArrayList<Tayte>tt = dao.getTaytteetByPizzaId(pizza_id);
                        request.setAttribute("tt", tt);

                       
                       
                        dao.deletePizzanTayte(pizza_id);
                        Pizza pizza = dao.getPizzaById(pizza_id);
                request.setAttribute("pizza", pizza);
       
             
        }else if(action.equalsIgnoreCase("status")){
               
                        int pizza_id = Integer.parseInt(request.getParameter("pizza_id"));
                        Pizza pizza = dao.getPizzaById(pizza_id);
 
                boolean sta = Boolean.parseBoolean(request.getParameter("status"));
                if(sta == true){
                        sta = false;
                       
                }else if(sta == false){
                        sta = true;
                }
 
                        pizza.setStatus(sta);
                        dao.updatePizzaStatus(pizza);
                        forward = LIST_USER;     
           
        } else {
            forward = LIST_USER;
        }
 
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}