package com.task.servlets;

//Import required java libraries
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;

import com.task.classes.SearchForm;

//Extend HttpServlet class
//@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	//	doGet(request, response);
		PrintWriter printWriter = response.getWriter();
		//printWriter.print("Hello ");
		String getlimit = request.getParameter("limit");
		String textToSearch = request.getParameter("q");
		String getlength = request.getParameter("length");
		String metaData = request.getParameter("metaData");
		SearchForm form = null;
		if(!"".equals(getlimit) && !"".equals(getlength)){
		try {
			Integer.parseInt(getlimit);
			Integer.parseInt(getlength);
			form = new SearchForm(textToSearch,getlength,getlimit,metaData);
			} catch (NumberFormatException e) {
			printWriter.print("Try again. Limit and length should be Numbers");
		}
		}else{
			form = new SearchForm(textToSearch,getlength,getlimit, metaData);
			
		}
		
		response.setContentType("application/json");
		JSONObject jsonObject= form.readResult();
		
		
		// Get the printwriter object from response to write the required json object to the output stream      
		PrintWriter out = response.getWriter();
		// Assuming your json object is **jsonObject**, perform the following, it will return your json object  
		ObjectMapper mapper = new ObjectMapper();
		out.print(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject));
		out.flush();
		
	}
	
	
	
	
}