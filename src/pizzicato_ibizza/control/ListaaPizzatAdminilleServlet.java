package pizzicato_ibizza.control;
 
import java.io.IOException;
import java.util.ArrayList;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import pizzicato_ibizza.model.Pizza;
import pizzicato_ibizza.model.Tayte;
import pizzicato_ibizza.model.dao.PizzaDAO;
import pizzicato_ibizza.model.dao.TayteDAO;
 
/**
 * Servlet implementation class ListaaPizzatServlet
 */
@WebServlet("/ListaaPizzatAdminilleServlet")
public class ListaaPizzatAdminilleServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;
       
 
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                //Luodaan uuse tuotedao ja haetaan tuote titetokannasta kaikki tietod ja sijoitetaan niitä Array listiin
                PizzaDAO pizzadao = new PizzaDAO();
                ArrayList<Pizza> pizzat = pizzadao.findAll();
                TayteDAO taytedao = new TayteDAO();
                ArrayList<Tayte> taytteet = taytedao.findAll();
 
                // Talletetaan request-olion alle Tuotelista, jotta tiedot ovat käytössä jsp:llä
                request.setAttribute("pizzat", pizzat);
                request.setAttribute("taytteet", taytteet);
               
                // lähetä selaimelta tullut pyyntö servletiltä edelleen  jsp:lle
                String jsp = "/view/MenuAdmin.jsp";
                RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
                dispather.forward(request, response);
               
        }
 
 
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                // TODO Auto-generated method stub
        }
 
}