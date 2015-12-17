package pizzicato_ibizza.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import pizzicato_ibizza.model.Tayte;
import pizzicato_ibizza.model.Pizza;
import pizzicato_ibizza.model.dao.TayteDAO;
import pizzicato_ibizza.model.dao.PizzaDAO;
 
 
/**
 * Servlet implementation class PaivitapizzaServlet
 */
@WebServlet("/PaivitaTayteServlet")
public class PaivitaTayteServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;
 
        /**
         * @see HttpServlet#HttpServlet()
         */
        public PaivitaTayteServlet() {
                super();
                // TODO Auto-generated constructor stub
        }
 
        /**
         * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
         *      response)
         */
        protected void doGet(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
                               
                String jsp = "/view/MuokkaaTayte.jsp";
                RequestDispatcher dispather = getServletContext().getRequestDispatcher(
                                jsp);
                dispather.forward(request, response);
        }
 
        /**
         * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
         *      response)
         */
        protected void doPost(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
 
               
                String jsp = "/view/MuokkaaTayte.jsp";
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
               
               
                Map<String, String> errors1 = validate(request);
                if (!errors1.isEmpty())
                {
                        dispatcher.forward(request, response);
                } else {
               
                Tayte tayte = (Tayte) request.getAttribute("tayte");
                // Luodaan pizzadao
                TayteDAO taytedao = new TayteDAO();
                // Lis‰t‰‰n tuotteen tiedot tietokantaan
                // pizza.setpizza_id(Integer.parseInt(idStr));
               
                //request.setAttribute("pizza", pizza);
               
 
                taytedao.updateTayte(tayte);
 
                // uudelleenohjataan selain henkilolista-sivulle
                response.sendRedirect("ListaaTaytteetAdminilleServlet");
                }
        }
       
        public static Map<String, String> validate(HttpServletRequest request){
                Tayte tayte = new Tayte();
                HashMap<String, String> errors1 = new HashMap<String, String>();
                request.setAttribute("errors1", errors1);
                request.setAttribute("tayte", tayte);
               
               
               
                tayte.setTayte_id(Integer.parseInt(request.getParameter("tayte_id")));
               
                // pizza_nimi
                String tayte_nimi = request.getParameter("tayte_nimi"); // Nimi
                if (tayte_nimi == null || tayte_nimi.trim().length() == 0)
                {
                        errors1.put("tayte_nimi", "Anna taytteen nimi.");
                } else {
                        tayte.setTayte_nimi(tayte_nimi);
                }
 
                //hinta
               
                                String strhinta = request.getParameter("hinta");
                               
                                if (strhinta == null || strhinta.trim().length() == 0)
                                {
                                        errors1.put("strhinta", "Anna t‰ytteen hinta.");
                                } else if (strhinta.matches("-\\d+")){
                                        errors1.put("strhinta", "Anna t‰ytteelle positiivinen hinta.");
                                } else if (strhinta.matches("\\D+")) {
                                        errors1.put("strhinta", "Anna t‰ytteen hinta numeroina.");
                                } else if (strhinta.matches("\\d+,\\d+")) {                    
                                        double hinta = Double.parseDouble(strhinta.replaceAll(",", "."));
                                        tayte.setHinta(hinta);
                                }else if (!strhinta.matches("\\d+[,.]\\d+")) {  
                                			try {
												
                                                double hinta = Double.parseDouble(strhinta.replaceAll(",", "."));
                                                tayte.setHinta(hinta);
											} catch (Exception e) {
												errors1.put("strhinta", "Anna t‰ytteen hinta VAIN numeroina.");
											}
                                } else if (!strhinta.matches("\\d{0,2}[,.]\\d+")) {
                                        errors1.put("strhinta", "Hinta on liian suuri.");
                                } else {
                                        //double pizza_hinta = Double.parseDouble(df.format(idStrpizza_hinta));
                                        double hinta = new Double(strhinta);   
                                        tayte.setHinta(hinta);
                                }
 
               
                return errors1;
        }
}