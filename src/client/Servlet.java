package client;

import it.unibo.ai.ePolicy.GlobalOpt.Domain.Receptor;
import it.unibo.ai.ePolicy.GlobalOpt.WS.Services.GlobalOptWSSEI;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.InvokeBuilder;
import model.ParetoBuilder;
import model.Proxy;

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
	@Override
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
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		HttpSession session = request.getSession();
		Integer computations = (Integer) session.getAttribute("computations");
		if (computations == null)
			computations = 0;

		try {
			GlobalOptWSSEI proxy = Proxy.getInstance();

			// Wrapper.getInstance().compute(new InputBuilder(request) //
			// .setLocale(request.getParameter("locale")) //
			// .setConstraints(request.getParameter("constraints")) //
			// .setFunctions(request.getParameter("functions")) //
			// .setScenarios(request.getParameter("scenarios")));

			ParetoBuilder problem = new ParetoBuilder(request.getServletContext()) //
					.setLocale(request.getParameter("locale")) //
					.setConstraints(request.getParameter("constraints")) //
					.setFunctions(request.getParameter("functions")) //
					.setScenarios(request.getParameter("scenarios"));

			InvokeBuilder min = problem.minimize(Receptor.getReceptorList(problem.getLocale())[0]);
			InvokeBuilder max = problem.maximize(Receptor.getReceptorList(problem.getLocale())[0]);

			proxy.computePareto(problem.build());
			computations += 1;
			session.setAttribute("computations", computations);
			request.setAttribute("computations", computations);
			request.setAttribute("timestamp", "1h 23m 45s 678ms");

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/includes/content.jsp");
		dispatcher.forward(request, response);
	}
}
