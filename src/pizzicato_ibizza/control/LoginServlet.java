package pizzicato_ibizza.control;

import pizzicato_ibizza.model.Asiakas;
import pizzicato_ibizza.model.dao.AsiakasDAO;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String jsp = "/view/Login.jsp"; 
		RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
		dispather.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		int asiakas_id=0;
		String etunimi=null;
		String sukunimi=null;
		String email=null;
		String puhelin_nro=null;
		String katuosoite=null;
		String postinumero=null;
		String kayttajatunnus=null;
		String salasana=null;

		
		
		try{
			Asiakas asiakas = new Asiakas(asiakas_id, etunimi, sukunimi,
					email, puhelin_nro, katuosoite,
					postinumero, kayttajatunnus, salasana);
			asiakas.setKayttajatunnus(request.getParameter("user"));
			asiakas.setSalasana(request.getParameter("password"));
			
			asiakas = AsiakasDAO.login(asiakas);
			
			if(asiakas.isValid()){
				HttpSession session = request.getSession(true);
				session.setAttribute("asiakas", asiakas);
				if(asiakas.getKayttajatunnus().equals("admin")){
				response.sendRedirect("ListaaPizzatAdminilleServlet");
				}
				if(asiakas.getKayttajatunnus().equals("kokki")){
				response.sendRedirect("ListaaTilauksetKokilleServlet");
				
				}else{
				response.sendRedirect("ListaaKaikilleServlet");
				}
			}else{
				response.sendRedirect("view/Login.jsp");
			}
		}catch(Throwable theException){
			System.out.println(theException);
		}
		
		
	}
}