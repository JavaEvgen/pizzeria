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
 




import pizzicato_ibizza.model.Tayte;
import pizzicato_ibizza.model.Pizza;
import pizzicato_ibizza.model.dao.TayteDAO;
import pizzicato_ibizza.model.dao.PizzaDAO;
 
 
/**
 * Servlet implementation class PaivitapizzaServlet
 */
@WebServlet("/PaivitaPizzaServlet")
public class PaivitaPizzaServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;
 
        /**
         * @see HttpServlet#HttpServlet()
         */
        public PaivitaPizzaServlet() {
                super();
                // TODO Auto-generated constructor stub
        }
 
        /**
         * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
         *      response)
         */
        protected void doGet(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
               
                TayteDAO taytedao = new TayteDAO();
                ArrayList<Tayte> taytteet = taytedao.findAll();
 
                // Talletetaan request-olion alle pizzalista, jotta tiedot ovat käytössä jsp:llä
                request.setAttribute("taytteet", taytteet);
               
               
                String jsp = "/view/MuokkaaPizza.jsp";
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
 
                TayteDAO taytedao = new TayteDAO();
                ArrayList<Tayte> taytteet = taytedao.findAll();
 
                // Talletetaan request-olion alle pizzalista, jotta tiedot ovat käytössä jsp:llä
                request.setAttribute("taytteet", taytteet);
 
                        PizzaDAO dao = new PizzaDAO();
                        int pizza_id = Integer.parseInt(request.getParameter("pizza_id"));
                        ArrayList<Tayte>tt = dao.getTaytteetByPizzaId(pizza_id);
                        request.setAttribute("tt", tt);
               
                String jsp = "/view/MuokkaaPizza.jsp";
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
               
               
                Map<String, String> errors = validate(request);
                if (!errors.isEmpty())
                {
                        dispatcher.forward(request, response);
                } else {
               
                Pizza pizza = (Pizza) request.getAttribute("pizza");
                // Luodaan pizzadao
                PizzaDAO pizzadao = new PizzaDAO();
                // Lisätään tuotteen tiedot tietokantaan
                // pizza.setpizza_id(Integer.parseInt(idStr));
               
                //request.setAttribute("pizza", pizza);
               
                PizzaDAO pdao = new PizzaDAO();
                pdao.updatePizza(pizza);

 
                // uudelleenohjataan selain henkilolista-sivulle
                response.sendRedirect("ListaaPizzatAdminilleServlet");
                }
        }
       
        public static Map<String, String> validate(HttpServletRequest request){
                Pizza pizza = new Pizza();
                HashMap<String, String> errors = new HashMap<String, String>();
                request.setAttribute("errors", errors);
                request.setAttribute("pizza", pizza);
               
               
               
                pizza.setPizza_id(Integer.parseInt(request.getParameter("pizza_id")));
               
                // pizza_nimi
                String pizza_nimi = request.getParameter("pizza_nimi"); // Nimi
                if (pizza_nimi == null || pizza_nimi.trim().length() == 0)
                {
                        errors.put("pizza_nimi", "Anna tuotteen nimi.");
                } else {
                        pizza.setPizza_nimi(pizza_nimi);
                }
 
                // kuvaus
                String kuvaus = request.getParameter("kuvaus");
                pizza.setKuvaus(kuvaus);
               
                // normi hinta
                                String strhinta_norm = request.getParameter("hinta_norm");
                               
                                if (strhinta_norm == null || strhinta_norm.trim().length() == 0)
                                {
                                        errors.put("strhinta_norm", "Anna tuotteelle hinta.");
                                } else if (strhinta_norm.matches("-\\d+")){
                                        errors.put("strhinta_norm", "Anna tuotteelle positiivinen hinta.");
                                } else if (strhinta_norm.matches("\\D+")) {
                                        errors.put("strhinta_norm", "Anna tuotteen hinta numeroina.");
                                } else if (strhinta_norm.matches("\\d+,\\d+")) {                       
                                        double hinta_norm = Double.parseDouble(strhinta_norm.replaceAll(",", "."));
                                        pizza.setHinta_norm(hinta_norm);
                                }else if (!strhinta_norm.matches("\\d+[,.]\\d+")) {                    
                                	try{
                                        double hinta_norm = Double.parseDouble(strhinta_norm.replaceAll(",", "."));
                                        pizza.setHinta_norm(hinta_norm);
                                	}catch(Exception e){
                                		errors.put("strhinta_norm", "Anna täytteen hinta VAIN numeroina.");
                                	}
                                } else if (!strhinta_norm.matches("\\d{0,2}[,.]\\d+")) {
                                        errors.put("strhinta_norm", "Hinta on liian suuri.");
                                } else {
                                        //double pizza_hinta = Double.parseDouble(df.format(idStrpizza_hinta));
                                        double hinta_norm = new Double(strhinta_norm); 
                                        pizza.setHinta_norm(hinta_norm);
                                }
                                // iso hinta
                                String strhinta_iso = request.getParameter("hinta_iso");
                               
                                if (strhinta_iso == null || strhinta_iso.trim().length() == 0)
                                {
                                        errors.put("strhinta_iso", "Anna tuotteelle hinta.");
                                } else if (strhinta_iso.matches("-\\d+")){
                                        errors.put("strhinta_iso", "Anna tuotteelle positiivinen hinta.");
                                } else if (strhinta_iso.matches("\\D+")) {
                                        errors.put("strhinta_iso", "Anna tuotteen hinta numeroina.");
                                } else if (strhinta_iso.matches("\\d+,\\d+")) {                
                                        double hinta_iso = Double.parseDouble(strhinta_iso.replaceAll(",", "."));
                                        pizza.setHinta_iso(hinta_iso);
                                }else if (!strhinta_iso.matches("\\d+[,.]\\d+")) {                     
                                	try{
                                        double hinta_iso = Double.parseDouble(strhinta_iso.replaceAll(",", "."));
                                        pizza.setHinta_iso(hinta_iso);
                                	}catch(Exception e){
                                		errors.put("strhinta_iso", "Anna täytteen hinta VAIN numeroina.");
                                	}
                                } else if (!strhinta_iso.matches("\\d{0,2}[,.]\\d+")) {
                                        errors.put("strhinta_iso", "Hinta on liian suuri.");
                                } else {
                                        //double pizza_hinta = Double.parseDouble(df.format(idStrpizza_hinta));
                                        double hinta_iso = new Double(strhinta_iso);   
                                        pizza.setHinta_iso(hinta_iso);
                                }
                               
                                String[] sisallot = request.getParameterValues("tayte");
                                
                                if(sisallot == null){
                                	errors.put("taytteet", "Valitse pizalle täytteet");
                                }else{
                                
                                final int[] ints = new int[sisallot.length];
                                for (int i=0; i < sisallot.length; i++) {
                                    ints[i] = Integer.parseInt(sisallot[i]);
                                }
                               
                                    for(int t: ints){
                                            Tayte tayte = new Tayte();
                                            tayte.setTayte_id(t);
                                           
                                            pizza.tt.add(tayte);
                                           
                                                           
                                    }
 
                               
                                }
               
                return errors;
        }
}