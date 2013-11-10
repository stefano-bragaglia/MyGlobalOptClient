package client;

import it.unibo.ai.ePolicy.GlobalOpt.IO.Output.GOParetoOutput;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.InputBuilder;
import model.Wrapper;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/servlet")
public class Servlet extends HttpServlet {

	/**
	 * The generated serial ID.
	 */
	private static final long serialVersionUID = 721321134685482010L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		GOParetoOutput output = Wrapper.getInstance().compute( //
				new InputBuilder(request.getServletContext()) //
						.setLocale(request.getParameter("locale")) //
						.setConstraints(request.getParameter("constraints")) //
						.setFunctions(request.getParameter("functions")) //
						.setScenarios(request.getParameter("scenarios")) //
						.build());
		request.setAttribute("output", output);
		// request.setAttribute("computations",
		// Wrapper.getInstance().getComputations());
		// request.setAttribute("timestamp",
		// Wrapper.getInstance().getTimestamp());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}
}
