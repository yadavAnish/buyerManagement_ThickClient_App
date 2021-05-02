package com;
import com.buyer;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class buyersAPI
 */
@WebServlet("/buyersAPI")
public class buyersAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	buyer buyerObj=new buyer();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public buyersAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

		protected void doPost(HttpServletRequest request, HttpServletResponse response) 
				 throws ServletException, IOException 
				{ 
				 String output = buyerObj.insertBuyer(request.getParameter("name"), 
				 request.getParameter("email"), 
				request.getParameter("phone")); 
		response.getWriter().write(output); 
				}
	 
	 private static Map getParasMap(HttpServletRequest request) 
		{ 
		 Map<String, String> map = new HashMap<String, String>(); 
		try
		 { 
		 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
		 String queryString = scanner.hasNext() ? 
		 scanner.useDelimiter("\\A").next() : ""; 
		 scanner.close(); 
		 String[] params = queryString.split("&"); 
		 for (String param : params) 
		 { 
			 String[] p = param.split("=");
			 map.put(p[0], p[1]); 
			 } 
			 } 
			catch (Exception e) 
			 { 
			 } 
			return map; 
			}
	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	 protected void doPut(HttpServletRequest request, HttpServletResponse response) 
			 throws ServletException, IOException 
			{ 
			 Map paras = getParasMap(request); 
			 String output = buyerObj.updateBuyer(paras.get("hidItemIDSave").toString(), 
			 paras.get("name").toString(), 
			 paras.get("email").toString(), 
			paras.get("phone").toString());
	response.getWriter().write(output); 
			} 
	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	 protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
			 throws ServletException, IOException 
			{ 
			 Map paras = getParasMap(request); 
			 String output = buyerObj.deleteBuyer(paras.get("id").toString()); 
		response.getWriter().write(output); 
			}

}
