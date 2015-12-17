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
 * Servlet implementation class LisaaPizzaServlet
 */
@WebServlet("/LisaaPizzaServlet")
public class LisaaPizzaServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;
       
        /**
         * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
         */
        /**
         * Haetaan pizzan lisäykseen tarvittavia tietoja tietokannasta, sijoitetaan niitä olioihiin ja
         * lähetetään JSP:lle bean olioilla.
         */
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
               
        		PizzaDAO pizzadao = new PizzaDAO();
        		ArrayList<Pizza> pizzat = pizzadao.findAll();

        		// Talletetaan request-olion alle Pizzalista, jotta tiedot ovat käytössä jsp:llä
        		request.setAttribute("pizzat", pizzat);
            
                TayteDAO taytedao = new TayteDAO();
                ArrayList<Tayte> taytteet = taytedao.findAll();
 
                // Talletetaan request-olion alle Pizzalista, jotta tiedot ovat käytössä jsp:llä
                request.setAttribute("taytteet", taytteet);
                
                Pizza pizza = new Pizza();
                request.setAttribute("pizza", pizza);
                
               
                String jsp = "/view/UusiPizza.jsp";
                RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
                dispather.forward(request, response);
        }
 
        /**
         * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
         * Haetaan käyttäjältä tulevia tietoja, sijoitetaan niitä olioihiin ja lähetetään eteenpäin
         * DAO olioihiin
         */
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                TayteDAO taytedao = new TayteDAO();
                ArrayList<Tayte> taytteet = taytedao.findAll();
 
                // Talletetaan request-olion alle Pizzalista, jotta tiedot ovat käytössä jsp:llä
                request.setAttribute("taytteet", taytteet);
               
                String jsp = "/view/UusiPizza.jsp";
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
 
               
                Map<String, String> errors = validate(request);
                if (!errors.isEmpty())
                {
                        dispatcher.forward(request, response);
                } else {
               
                Pizza pizza = (Pizza) request.getAttribute("pizza");
               
                PizzaDAO pdao = new PizzaDAO();
                try {
                        pdao.addPizza(pizza);
                } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
 
 
                // Luodaan Pizzadao
               
                // Lisätään tuotteen tiedot tietokantaan
                // Pizza.setPizza_id(Integer.parseInt(idStr));
 
                //request.setAttribute("Pizza", Pizza);
 
                // uudelleenohjataan selain henkilolista-sivulle
                response.sendRedirect("ListaaPizzatAdminilleServlet");
                }
        }
        /**
         * Tässä tapahtuu validointia
         * @see Map<String, String> validate(HttpServletRequest request)
         */
        public static Map<String, String> validate(HttpServletRequest request){
                Pizza pizza = new Pizza();
                HashMap<String, String> errors = new HashMap<String, String>();
                request.setAttribute("errors", errors);
                request.setAttribute("pizza", pizza);
               
                // Pizza_id
                /*String idStr = request.getParameter("Pizza_id"); // Id
                if (idStr == null || idStr.trim().length() == 0)
                {
                        errors.put("idStr", "Anna tuotteen id.");
                } else {
                */
                       
               
               
               
                // Pizza_nimi
                String pizza_nimi = request.getParameter("pizza_nimi"); // Nimi
                if (pizza_nimi == null || pizza_nimi.trim().length() == 0)
                {
                        errors.put("pizza_nimi", "Anna pizzalle nimi.");
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
                        //double Pizza_hinta = Double.parseDouble(df.format(idStrPizza_hinta));
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
                } else if (!strhinta_iso.matches("\\d+[,.]\\d+")) {   
                	try{
                        double hinta_iso = Double.parseDouble(strhinta_iso.replaceAll(",", "."));
                        pizza.setHinta_iso(hinta_iso);
                	}catch(Exception e){
                		errors.put("strhinta_iso", "Anna täytteen hinta VAIN numeroina.");
                	}
                } else if (!strhinta_iso.matches("\\d{0,2}[,.]\\d+")) {
                        errors.put("strhinta_iso", "Hinta on liian suuri.");
               
                } else {
                        //double Pizza_hinta = Double.parseDouble(df.format(idStrPizza_hinta));
                        double hinta_iso = Double.parseDouble(strhinta_iso);
                        pizza.setHinta_iso(hinta_iso);
                }
                String strfantasia = request.getParameter("onko");
                int fantasia = Integer.parseInt(strfantasia);
                	
                if(fantasia == 0){
                	pizza.setFantasia(false);
                }else{
                	pizza.setFantasia(true);
                }
               //täytteet
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
