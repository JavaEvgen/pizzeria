package pizzicato_ibizza.control;

import java.io.IOException;
import java.util.ArrayList;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import pizzicato_ibizza.model.dao.TayteDAO;
import pizzicato_ibizza.model.Pizza;
import pizzicato_ibizza.model.Tayte;
 
@WebServlet("/TayteController")
 
public class TayteController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String LIST_USER = "ListaaTaytteetAdminilleServlet";
    private static String EDIT = "PaivitaTayteServlet";
    private TayteDAO dao;
   
   
 
    public TayteController() {
        super();
        dao = new TayteDAO();
    }
 
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                //Luodan uusi muuttuja käyttäjän toimenpidettä varten.
        String forward="";
        String action = request.getParameter("action");
        //kuunnellaan "action" vastausta jsp:sta ja suoritan alla mainitut fuktiot "actionia" riippujen
        //tässä hoidetaan sekä poisto että päivitys
        if (action.equalsIgnoreCase("delete")){
            int tayte_id = Integer.parseInt(request.getParameter("tayte_id"));
            dao.deleteTayte(tayte_id);
            forward = LIST_USER;
           
        }else if (action.equalsIgnoreCase("edit")){
                forward = EDIT;
                        int tayte_id = Integer.parseInt(request.getParameter("tayte_id"));
 
                        Tayte tayte = dao.getTayteById(tayte_id);
                request.setAttribute("tayte", tayte);
           
                 
        }else if(action.equalsIgnoreCase("status")){
               
                        int tayte_id = Integer.parseInt(request.getParameter("tayte_id"));
                        Tayte tayte = dao.getTayteById(tayte_id);
                        tayte.setTayte_id(tayte_id);
                boolean sta = Boolean.parseBoolean(request.getParameter("status"));
                if(sta == true){
                        sta = false;
                       
                }else if(sta == false){
                        sta = true;
                }
 
                        tayte.setStatus(sta);
                        dao.updateTayteStatus(tayte);
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